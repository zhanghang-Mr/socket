package com.zh.application.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
public class Group {
    /**
     * 
     */
    private Long id;

    /**
     * 群名称
     */
    private String groupName;

    /**
     * 群建立者用户id
     */
    private Long groupBuildId;

    /**
     * 群创建时间
     */
    private Date groupBuildTime;

    /**
     * 群简介
     */
    private String groupSummary;

}