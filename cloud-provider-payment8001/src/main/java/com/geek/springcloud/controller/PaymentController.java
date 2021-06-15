package com.geek.springcloud.controller;

import com.geek.springcloud.entities.CommonResult;
import com.geek.springcloud.entities.Payment;
import com.geek.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Robert
 * @create 2021/6/15 15:50
 * @Version 1.0
 * @Description:
 */

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        //需要添加 @RequestBody 否则其他服务访问会传入空置
        int result = paymentService.create(payment);
        log.info("-----插入结果："+result);
        if (result>0){
            return new CommonResult(200,"插入数据库成功",result);
        }else {
            return new CommonResult(403,"插入失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("-----查询结果："+payment);
        if (payment!=null){
            return new CommonResult(200,"查询成功",payment);
        }else {
            return new CommonResult(403,"查询失败，没有对应记录，查询id："+id,null);
        }
    }

}
