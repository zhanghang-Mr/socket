package com.zh.application.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 处理消息vo
 */
@Setter
@Getter
public class DisposeVo {

    /**
     * 业务主键
     */
    @NotNull
    @ApiModelProperty(value = "业务主键", required = true)
    private Long bussId;

    /**
     * 业务类型编码
     */
    @NotNull
    @ApiModelProperty(value = "业务类型编码", required = true)
    private String bussTypeCode;

    /**
     * 是否接受
     */
    @NotNull
    @ApiModelProperty(value = "是否接受", required = true)
    private Byte isAccept;

    /**
     * 拒绝理由
     */
    @ApiModelProperty(value = "拒绝理由")
    private String refuse;
}
