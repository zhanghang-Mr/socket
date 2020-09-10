package com.zh.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 测试Ribbon
 * 调用方
 */
@RestController
@RequestMapping("/ribbon")
public class TestConsumerRibbonController {
    //因为要实现负载均衡，所以使用服务名调用
    private static final String REST_URL_PREFIX= "http://EUREKA-CLIENT";
    @Autowired
    RestTemplate restTemplate;

    //远程调用
    @GetMapping
    public String getTestRibbon(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/test",String.class);
    }
}
