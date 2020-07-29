package com.zh.application.pojo.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 添加好友vo
 */
@Setter
@Getter
public class FriendVo {

    /**
     * 邀请方用户id
     */
    @NotNull
    @ApiModelProperty(value = "邀请方用户id", required = true)
    private Long toUserId;

    /**
     * 被邀请方用户id
     */
    @NotNull
    @ApiModelProperty(value = "被邀请方用户id", required = true)
    private Long acceptUserId;

    /**
     * 邀请消息
     */
    @ApiModelProperty(value = "邀请消息", required = true)
    private String message;
}
