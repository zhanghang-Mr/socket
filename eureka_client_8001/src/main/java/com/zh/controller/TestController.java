package com.zh.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
     * 服务提供者
     */
    @RestController
    @RequestMapping("/test")
    public class TestController {

        @GetMapping
        @HystrixCommand  //被监控的注解
        public String getTestRibbon(){
            return "------8001-client---------";
        }

}
