package com.zh.application.pojo.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户注册对象
 */
@Setter
@Getter
public class UserRegisterVo {

    /**
     * 账号
     */
    @NotBlank
    @ApiModelProperty(value = "账号", required = true)
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 、密码
     */
    @NotBlank
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
