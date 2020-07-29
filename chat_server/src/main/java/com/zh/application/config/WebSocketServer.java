package com.zh.application.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zh.application.enums.SendTypeEnum;
import com.zh.application.mapper.UserFriendMapperExt;
import com.zh.application.mapper.UserMapper;
import com.zh.application.pojo.User;
import com.zh.application.pojo.UserGroup;
import com.zh.application.pojo.vo.FriendVo;
import com.zh.application.pojo.vo.MessageVo;
import com.zh.application.service.BaseUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ContextLoader;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/websocket/{userId}")
@Component
public class WebSocketServer {
    static Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private volatile static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static ConcurrentHashMap<Long, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收userId*/
    private Long userId= 0L;

    /**
     * 消息key头 连接信息 ，
     * key规则：
     *  好友间聊天， uid 的在前面 。
     *   例如 1,5， 则key为 "friend-message1-5"
     */
    private static String groupMessageHead = "group-message";
    private static String friendMessageHead = "friend-message";
    private static String messageConnect = "-"; //key连接

    /**
     * 消息的存储
     */
    public static ConcurrentHashMap<String, List<MessageVo>> messageMap = new ConcurrentHashMap<>();

    /**
     * 因为当前类使用了@Component 注解，所以无法直接注入mapper或者service，
     * 需要定义静态变量，在WebSocketConfig 配置类中注入进去
     */
    public static UserMapper userMapper;

    public static BaseUserService baseUserService;

    public static UserFriendMapperExt userFriendMapperExt;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public static void main(String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println("---date---:"+formatter.format(new Date()));
    }
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Long userId) {
        this.session = session;
        this.userId=userId;
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            webSocketMap.put(userId,this);
            //加入set中
        }else{
            webSocketMap.put(userId,this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }

        log.info("用户连接:"+userId+",当前在线人数为:" + getOnlineCount());
        System.out.println("=====user====:"+JSON.toJSONString(userMapper.selectByPrimaryKey(userId)));
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("用户:"+userId+",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }
        log.info("用户退出:"+userId+",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("用户消息:"+userId+",报文:"+message);
        // 将 json转为对象
        MessageVo messageVo = JSON.parseObject(message, MessageVo.class);
        System.out.println("----vo---:"+ JSON.toJSONString(messageVo));
        //判断消息类型
        switch (SendTypeEnum.getEnumByKey(messageVo.getBussTypeCode())){
            case GROUP:   //邀请入群消息
                if(setGroup(messageVo))
                    sendMessage(messageVo);
                break;
            case ADD_FRIEND: // 添加好友消息
                setAddFriend(messageVo);
                sendMessage(messageVo);
                break;
            case GROUP_CHAT: //发送群消息
                sendGroupMessage(messageVo);
                break;
            case FRIEND_CHAT: //发送好友消息
                //存储在消息中
                setFriendMessage(messageVo);
                sendMessage(messageVo);
                break;
        }

//        MessageVo stu=(MessageVo)JSONObject.toBean(message, MessageVo.class);
        //可以群发消息
        //消息保存到数据库、redis
//        StringUtils.isEmpty()
//        System.out.println("--在线人信息--"+webSocketMap.toString());
//        if(!StringUtils.isEmpty(message)){
//            try {
//                //解析发送的报文
//                JSONObject jsonObject = JSON.parseObject(message);
//                //追加发送人(防止串改)
//                jsonObject.put("fromUserId",this.userId);
//                String toUserId=jsonObject.getString("toUserId");
//                System.out.println("---toUserId---:"+toUserId);
//                System.out.println("---userId---:"+userId);
//
//                //传送给对应toUserId用户的websocket
//                if(!StringUtils.isEmpty(toUserId)&&webSocketMap.containsKey(toUserId)){
//                    System.out.println("---userId-xinxi---:"+toUserId+"::"+webSocketMap.get(toUserId));
//                    webSocketMap.get(toUserId).sendMessage(jsonObject.toJSONString());
//                }else{
//                    log.error("请求的userId:"+toUserId+"不在该服务器上");
//                    //否则不在这个服务器上，发送到mysql或者redis
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:"+this.userId+",原因:"+error.getMessage());
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送自定义消息
     * */
    public static void sendInfo(String message,@PathParam("userId") String userId) throws IOException {
        log.info("发送消息到:"+userId+"，报文:"+message);
        if(StringUtils.isEmpty(userId)&&webSocketMap.containsKey(userId)){
            webSocketMap.get(userId).sendMessage(message);
        }else{
            log.error("用户"+userId+",不在线！");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    /**
     * 好友聊天信息
     */
    private void sendMessage(MessageVo bean) throws IOException {
        bean.setSendDate(formatter.format(new Date()));
        bean.setNickname(userMapper.selectByPrimaryKey(bean.getId()).getNickname());
        String message = JSON.toJSONString(bean);
        if(!StringUtils.isEmpty(bean.getInviteUid()) && webSocketMap.containsKey(bean.getInviteUid())){
            webSocketMap.get(bean.getInviteUid()).sendMessage(message);
        }else{
            log.error("用户"+bean.getInviteUid()+",不在线！");
        }
    }

    /**
     * 发送群消息
     */
    private void sendGroupMessage(MessageVo bean) throws IOException {
        // 定义key
        String key = groupMessageHead+bean.getBussId();

        // 查询所有群成员
        List<Long> group = userFriendMapperExt.getGroupByGroupId(bean.getBussId());
        for (Long k: webSocketMap.keySet()) {
            if(group.contains(k)){
                webSocketMap.get(bean.getInviteUid()).sendMessage(JSON.toJSONString(bean));
            }
        }
        if(messageMap.containsKey(key)){
            List<MessageVo> list = messageMap.get(key);
            list.add(bean);
            messageMap.put(key,list);
        }
    }

    /**
     * 存储消息
     * @param bean
     */
    private void setFriendMessage(MessageVo bean){
        List<MessageVo> list = new ArrayList<>();
        // 定义key
        String key = "";
        if(bean.getInviteUid() > bean.getId()) // 判断 连个好友间的用户id，小的在前面
            key = friendMessageHead + bean.getId() +  messageConnect + bean.getInviteUid();
        else
            key = friendMessageHead + bean.getInviteUid() +  messageConnect + bean.getId();
        //1, 先判断两个好友间是否已经有消息了
        if(messageMap.containsKey(key)){
            //存在
            list =  messageMap.get(key);
            list.add(bean);
        }else{
            //不存在
            list.add(bean);
        }
        messageMap.put(key,list);
    }

    /**
     * 获取聊天记录
     * @return
     */
    public static List<MessageVo> getFriendMessage(Long toUid, Long goUid){
        // 定义key
        String key = "";
        if(toUid > goUid) // 判断 连个好友间的用户id，小的在前面
            key = friendMessageHead + goUid +  messageConnect + toUid;
        else
            key = friendMessageHead + toUid +  messageConnect + goUid;
        return messageMap.get(key);
    }

    /**
     * 添加好友邀请
     */
    public void setAddFriend(MessageVo bean){
        FriendVo friendVo = new FriendVo();
        friendVo.setToUserId(bean.getId());
        friendVo.setAcceptUserId(bean.getInviteUid());
        friendVo.setMessage(bean.getMessage());
        baseUserService.addFriend(friendVo);
    }

    /**
     * 邀请入群消息
     */
    private boolean setGroup(MessageVo bean){
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupId(bean.getBussId());
        userGroup.setUserId(bean.getInviteUid());
        return baseUserService.addInviteGroup(userGroup);
    }
}
