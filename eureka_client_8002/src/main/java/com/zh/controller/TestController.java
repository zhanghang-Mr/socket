package com.zh.controller;


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
    public String getTestRibbon(){
        return "------8002-client---------";
    }

}
