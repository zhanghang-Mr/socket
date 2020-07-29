package com.zh.application.pojo;

public class UserFriend {
    /**
     * 好友表主键
     */
    private Long id;

    /**
     * 添加方用户id
     */
    private Long toUserId;

    /**
     * 被邀请方用户id
     */
    private Long acceptUserId;

    /**
     * 是否有效
     */
    private Byte isEffective;

    /**
     * 是否接受
     */
    private Byte isAccept;

    /**
     * 添加方添加消息
     */
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public Long getAcceptUserId() {
        return acceptUserId;
    }

    public void setAcceptUserId(Long acceptUserId) {
        this.acceptUserId = acceptUserId;
    }

    public Byte getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(Byte isEffective) {
        this.isEffective = isEffective;
    }

    public Byte getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(Byte isAccept) {
        this.isAccept = isAccept;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}