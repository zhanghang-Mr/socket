package com.zh.application.enums;

public enum BussTypeEnum {
  FRIEND("friend", "好友");

  private String key;
  private String desc;

  private BussTypeEnum(String key, String desc) {
    this.key = key;
    this.desc = desc;
  }

  /**
   * 
   * 通过key查找AssessTypeEnum
   */
  public static BussTypeEnum getEnumByKey(String key) {
    for (BussTypeEnum item : BussTypeEnum.values()) {
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
