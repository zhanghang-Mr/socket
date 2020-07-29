package com.zh.application.controller;


import com.alibaba.fastjson.JSON;
import com.zh.application.mapper.UserMapper;
import com.zh.application.mapper.UserMapperExt;
import com.zh.application.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/get1")
    public String get1(){
        return "hello";
    }

    @GetMapping("/r/get1")
    public String get2(){
        return "hello";
    }


    @GetMapping("/user")
    public String  contextLoads() {
        User user = new User();
        user.setNickname("小黑");
        user.setUsername("xiaoh");
        user.setPassword("123456");
        userMapper.insertSelective(user);
        return "add-user";
    }

    @Autowired
    UserMapperExt userMapperExt;
    @GetMapping("/test1")
    public String get3(){
        User user = userMapperExt.queryUserByUsername("zhangsan");
        System.out.println("--user--:"+ JSON.toJSONString(user));
        return "hello";
    }


}
