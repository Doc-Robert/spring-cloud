package com.geek.springcloud.service.impl;

import com.geek.springcloud.dao.PaymentDao;
import com.geek.springcloud.entities.Payment;
import com.geek.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Robert
 * @create 2021/6/15 15:47
 * @Version 1.0
 * @Description:
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
