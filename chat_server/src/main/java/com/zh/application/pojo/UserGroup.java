package com.zh.application.pojo;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserGroup {
    /**
     * 
     */
    private Long id;

    /**
     * 邀请人uid
     */
    private Long inviteUserId;

    /**
     * 群id
     */
    private Long groupId;

    /**
     * 被邀请人用户id
     */
    private Long userId;

    /**
     * 是否接受
     */
    private Byte isAccept;


}