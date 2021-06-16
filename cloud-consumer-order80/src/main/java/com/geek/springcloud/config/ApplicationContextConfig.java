package com.geek.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Robert
 * @create 2021/6/15 19:31
 * @Version 1.0
 * @Description:
 */

@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced//使用@LoadBalanced注解赋予RestTemplate负载均衡的能力 不然访问80的服务会找不到应该隐射的端口8001，8002
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
