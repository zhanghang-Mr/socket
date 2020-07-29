package com.zh.application.pojo.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 被邀请信息
 */
@Setter
@Getter
public class InviteMessageVo {

    /**
     * 好友邀请表主键id
     */
    @ApiModelProperty(value = "好友邀请表主键id")
    private Long friendId;

    /**
     * 邀请人账号
     */
    @ApiModelProperty(value = "邀请人账号")
    private String username;

    /**
     * 邀请人昵称
     */
    @ApiModelProperty(value = "邀请人昵称")
    private String nickname;

    /**
     * 邀请信息
     */
    @ApiModelProperty(value = "邀请信息")
    private String message;
}
