package com.geek.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author Robert
 * @create 2021/6/16 10:26
 * @Version 1.0
 * @Description: Eureka 服务注册中心
 */

@SpringBootApplication
@EnableEurekaServer //该注解 代表本包为 Eureka服务注册中心 server 集群
public class EurekaMain7002 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7002.class , args);
    }
}
