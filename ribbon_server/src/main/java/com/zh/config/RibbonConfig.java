package com.zh.config;

import com.netflix.loadbalancer.*;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RibbonConfig {

    //配置负载均衡实现RestTemplate
    @Bean
    @LoadBalanced //Ribbon
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    /**
     * ribbon的负载均衡策略，默认是轮询
     * @return
     */
    @Bean
    public IRule getIRule(){
        //随机策略: 该策略实现了从服务实例清单中随机选择一个服务实例，作为请求服务对象。
//        return new RandomRule(); //随机策略
        //轮询策略: 该策略实现了按照线性轮询的方式一次轮询服务清单上的每个服务实例
//        return new RoundRobinRule(); //轮询策略
         // 该策略具备重试机制的实例选择功能，在给定时间内能够得到选择到具体的服务实例就返回，当超过时间还有没选到就返回null，参数maxRetryMillis控制这个超时时间。
//        return new RetryRule();
        //权重策略： 该策略是对RoundRobinRule的扩展，增加了根据实例的响应时间来计算权重，并从权重中选择对应的实例
        return new WeightedResponseTimeRule();
    }


}
