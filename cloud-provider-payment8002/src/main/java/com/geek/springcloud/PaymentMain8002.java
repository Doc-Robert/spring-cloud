package com.geek.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author Robert
 * @create 2021/6/15 15:10
 * @Version 1.0
 * @Description: main program class 支付模块
 */

@SpringBootApplication
@EnableEurekaClient//开启eureka client 客户端 入驻 server
public class PaymentMain8002 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8002.class,args);
    }
}
