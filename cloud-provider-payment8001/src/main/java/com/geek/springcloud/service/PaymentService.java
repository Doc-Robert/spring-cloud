package com.geek.springcloud.service;

import com.geek.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Robert
 * @create 2021/6/15 15:46
 * @Version 1.0
 * @Description:
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
