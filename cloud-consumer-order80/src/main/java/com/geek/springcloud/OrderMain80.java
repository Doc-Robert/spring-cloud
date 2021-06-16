package com.geek.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author Robert
 * @create 2021/6/15 19:25
 * @Version 1.0
 * @Description: order consumer main 订单模块
 */

@SpringBootApplication
@EnableEurekaClient //开启 eureka client 客户端
public class OrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
