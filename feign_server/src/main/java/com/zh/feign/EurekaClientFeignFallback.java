package com.zh.feign;

import org.springframework.stereotype.Component;

@Component
public class EurekaClientFeignFallback implements EurekaClientFeign {


//    @Override
//    public EurekaClientFeign create(Throwable cause) {
//        return new EurekaClientFeign() {
//            @Override
//            public String getTestRibbon() {
//                return "========进入 hystrix=================";
//            }
//        };
//    }

    @Override
    public String getTestRibbon() {
        return "========进入 hystrix=================";
    }
}
