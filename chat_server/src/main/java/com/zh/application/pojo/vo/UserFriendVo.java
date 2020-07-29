package com.zh.application.pojo.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 好友vo
 */
@Setter
@Getter
public class UserFriendVo {

    /**
     * 好友uid
     */
    @ApiModelProperty(value = "好友uid")
    private Long uid;

    /**
     * 被邀请方用户id
     */
    @ApiModelProperty(value = "被邀请方用户id")
    private String username;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "邀请消息")
    private String nickname;
}
