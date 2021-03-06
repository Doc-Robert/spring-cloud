package com.geek.springcloud.controller;

import com.geek.springcloud.entities.CommonResult;
import com.geek.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author Robert
 * @create 2021/6/15 19:29
 * @Version 1.0
 * @Description: 订单模块 controller
 */

@RestController
@Slf4j
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001"; //eureka 单机 配置

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";//eureka 集群配置

    @Resource
    private RestTemplate restTemplate;


    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        //(url, requestMap, ResponseBean.class)
        //REST请求地址、请求参数、HTTP响应转换被转换成的对象类型。
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

}