package com.zh.application.mapper;

import com.zh.application.pojo.User;
import com.zh.application.pojo.vo.InviteMessageVo;
import com.zh.application.pojo.vo.UserFriendVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFriendMapperExt {


    /**
     * *根据用户id 获取被邀请信息
     * @param uid
     * @return
     */
    List<InviteMessageVo> getInviteMessage(Long uid);

    /**
     *
     * @param id
     * @return
     */
    User getUserBuId(Long id);

    /**
     * 根据用户id 获取好友select user_id
     * from user_group
     * where  group_id = 1 and is_accept = 1
     * @param uid
     * @return
     */
    List<UserFriendVo> getFriendByUid(Long uid);

    /**
     * 根据邀请人uid 和 被邀请人uid 查询 是否已经邀请过
     */
    Byte getIsAccept(@Param("toUserId") Long toUserId,@Param("acceptUserId") Long acceptUserId);

    /**
     * 根据群id 查询群成员
     * @param id
     * @return
     */
    List<Long> getGroupByGroupId(Long id);
}