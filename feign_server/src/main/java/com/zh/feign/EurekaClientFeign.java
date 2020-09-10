package com.zh.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value="EUREKA-CLIENT",fallback= EurekaClientFeignFallback.class)
public interface EurekaClientFeign {

    @GetMapping("/test")
    public String getTestRibbon();

}
