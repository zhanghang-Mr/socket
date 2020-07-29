package com.zh.application.service;

import com.zh.application.pojo.Group;
import com.zh.application.pojo.User;
import com.zh.application.pojo.UserGroup;
import com.zh.application.pojo.vo.*;

import java.util.List;

public interface BaseUserService {

    /**
     * 添加好友
     * @param bean
     * @return
     */
    boolean addFriend(FriendVo bean);

    /**
     * 根据用户id 获取好友
     * @param uid
     * @return
     */
    List<UserFriendVo> getFriendByUid(Long uid);

    /**
     * 根据账号查询用户
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 根据用户id 获取被邀请信息
     * @param uid
     * @return
     */
    List<InviteMessageVo>  getInviteMessage(Long uid);

    /**
     * 处理消息
     * @param bean
     * @return
     */
    boolean dispose(DisposeVo bean);

    /**
     * 修改昵称
     * @param bean
     * @return
     */
    boolean updateNickname(UserNicknameVo bean);

    /**
     * 获取聊天记录
     * @param toUid
     * @param goUid
     * @return
     */
    List<MessageVo> getFriendMessage(Long toUid, Long goUid);

    /**
     * 新建群
     * @param bean
     * @return
     */
    boolean addGroup(Group bean);

    /**
     *
     */
    boolean addInviteGroup(UserGroup bean);
}
