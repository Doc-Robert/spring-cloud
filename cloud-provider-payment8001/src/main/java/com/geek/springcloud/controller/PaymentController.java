package com.geek.springcloud.controller;

import com.geek.springcloud.entities.CommonResult;
import com.geek.springcloud.entities.Payment;
import com.geek.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Robert
 * @create 2021/6/15 15:50
 * @Version 1.0
 * @Description: 支付模块 controller
 */

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}") //用value 读取到yml中的server.port
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient; //服务注册发现

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        //需要添加 @RequestBody 否则其他服务访问会传入空值
        int result = paymentService.create(payment);
        log.info("-----插入结果："+result);
        if (result>0){
            return new CommonResult(200,"插入数据库成功,serverPort:"+serverPort,result);
        }else {
            return new CommonResult(403,"插入失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("-----查询结果："+payment);
        if (payment!=null){
            return new CommonResult(200,"查询成功,serverPort"+serverPort,payment);
        }else {
            return new CommonResult(403,"查询失败，没有对应记录，查询id："+id,null);
        }
    }
    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            //打印discoveryClient 服务发现中 的所有微服务
            log.info("-----element:"+element);

        }
        //获取具体的微服务的实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            //获取到具体微服务的 id 地址 端口 +uri
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

}
