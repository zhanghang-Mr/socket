package com.zh.controller;


import com.zh.feign.EurekaClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
public class FeignController {

    @Autowired
    EurekaClientFeign eurekaClientFeign;

    @GetMapping
    public String getTestFeign(){
        return eurekaClientFeign.getTestRibbon();
    }

}
