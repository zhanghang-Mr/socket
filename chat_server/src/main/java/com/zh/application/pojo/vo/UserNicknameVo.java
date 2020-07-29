package com.zh.application.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 修改昵称vo
 */
@Setter
@Getter
public class UserNicknameVo {
    /**
     * 用户主键
     */
    @NotNull
    @ApiModelProperty(value = "业务主键", required = true)
    private Long id;

    /**
     * 昵称名称
     */
    @NotBlank
    @ApiModelProperty(value = "业务主键", required = true)
    private String nickname;
}
