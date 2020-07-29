package com.zh.application.controller;

import com.alibaba.fastjson.JSON;
import com.zh.application.mapper.UserFriendMapperExt;
import com.zh.application.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    UserFriendMapperExt userFriendMapperExt;

    @GetMapping("/chat/get1")
    public String get1(){
        return "hello-chat1";
    }

    @GetMapping("/test/get2")
    public String get2(){
        return "hello-chat2";
    }

    @GetMapping("/user/get3")
    public String get3(){
        User userBuId = userFriendMapperExt.getUserBuId(1L);
        System.out.println(JSON.toJSONString(userBuId));
        return "hello-user3";
    }
}
