package com.zh.application.service.impl;

import com.zh.application.Utils.CombineFiles;
import com.zh.application.config.WebSocketServer;
import com.zh.application.enums.BussTypeEnum;
import com.zh.application.mapper.*;
import com.zh.application.pojo.*;
import com.zh.application.pojo.vo.*;
import com.zh.application.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class BaseUserServiceImpl implements BaseUserService {

    @Resource
    private UserFriendMapper userFriendMapper;

    @Resource
    private UserMapper userMapper;

    @Autowired
    private UserFriendMapperExt userFriendMapperExt;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private UserGroupMapper userGroupMapper;

    @Override
    public boolean addFriend(FriendVo bean) {
        //1, 判断是否已经有发送过的未处理添加消息
//        UserFriendExample friendExample = new UserFriendExample();
//        UserFriendExample.Criteria friendCriteria = friendExample.createCriteria();
//        friendCriteria.andToUserIdEqualTo(bean.getToUserId());
//        friendCriteria.andAcceptUserIdEqualTo(bean.getAcceptUserId());
//        friendCriteria.andIsAcceptEqualTo(null);
//        List<UserFriend> userFriends = userFriendMapper.selectByExample(friendExample);
        if(userFriendMapperExt.getIsAccept(bean.getToUserId(),bean.getAcceptUserId()) > 0){
            return false;
        }
        UserFriend friend = new UserFriend();
        CombineFiles.copyByName(bean,friend);
        userFriendMapper.insertSelective(friend);
        return true;
    }

    @Override
    public List<UserFriendVo> getFriendByUid(Long uid) {

        return userFriendMapperExt.getFriendByUid(uid);
    }

    @Override
    public User getUserByUsername(String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if(users.isEmpty()){
            return null;
        }
        return users.get(0);
    }

    @Override
    public List<InviteMessageVo> getInviteMessage(Long uid) {
        return userFriendMapperExt.getInviteMessage(uid);
    }

    @Override
    public boolean dispose(DisposeVo bean) {
        Long retval = 0L;
        //判断业务类型
        switch (BussTypeEnum.getEnumByKey(bean.getBussTypeCode())){
            case FRIEND:  //处理添加好友消息
                UserFriend friend = new UserFriend();
                friend.setId(bean.getBussId());
                friend.setIsAccept(bean.getIsAccept());
                retval += userFriendMapper.updateByPrimaryKeySelective(friend);
                break;
        }
        return retval > 0 ? true : false;
    }

    @Override
    public boolean updateNickname(UserNicknameVo bean) {
        User user = new User();
        user.setId(bean.getId());
        user.setNickname(bean.getNickname());
        return userMapper.updateByPrimaryKeySelective(user) > 0 ;
    }

    @Override
    public List<MessageVo> getFriendMessage(Long toUid, Long goUid) {
        return WebSocketServer.getFriendMessage(toUid,goUid);
    }

    @Override
    public boolean addGroup(Group bean) {
        bean.setGroupBuildTime(new Date());
        return groupMapper.insertSelective(bean) > 0;
    }

    @Override
    public boolean addInviteGroup(UserGroup bean) {
        //判断当前用户是否已经存在当前群
        UserGroupExample groupExample = new UserGroupExample();
        UserGroupExample.Criteria groupCriteria = groupExample.createCriteria();
        groupCriteria.andGroupIdEqualTo(bean.getGroupId());
        groupCriteria.andUserIdEqualTo(bean.getUserId());
        if(!userGroupMapper.selectByExample(groupExample).isEmpty()){
            return false;
        }
        return userGroupMapper.insertSelective(bean) > 0;
    }
}
