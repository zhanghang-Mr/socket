package com.zh.application.pojo.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 消息对象vo
 */
@Setter
@Getter
public class MessageVo {

    /**
     * 发送方uid
     */
    private Long id;

    /**
     * 发送发用户昵称
     */
    private String nickname;

    /**
     * 被邀请方企业id
     */
    private Long inviteUid;

    /**
     * 业务外键id
     */
    private Long bussId;

    /**
     * 业务类型编码
     */
    private String bussTypeCode;

    /**
     * 消息
     */
    private String message;

    /**
     * 发送消息时间
     */
    private String sendDate;
}
