package com.oes.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    //<bean id="" class="">
    @Bean
    @LoadBalanced  //规定默认的负载均衡机制（轮询）
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
