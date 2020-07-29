package com.zh.application.enums;


/**
 * 发送消息类型
 */
public enum SendTypeEnum {
  ADD_FRIEND("friend", "添加好友消息"),
  FRIEND_CHAT("friend_chat","好友聊天"),
  GROUP_CHAT("group_chat","群聊天"),
  GROUP("group","邀请入群消息");


  private String key;
  private String desc;

  private SendTypeEnum(String key, String desc) {
    this.key = key;
    this.desc = desc;
  }

  /**
   * 
   * 通过key查找AssessTypeEnum
   */
  public static SendTypeEnum getEnumByKey(String key) {
    for (SendTypeEnum item : SendTypeEnum.values()) {
      if (key.equals(item.key))
        return item;
    }
    return null;
  }

  public String getKey() {
    return key;
  }

  public String getDesc() {
    return desc;
  }
}
