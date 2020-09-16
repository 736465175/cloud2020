package com.lizhiqiang.springcloud.service.impl;

import com.lizhiqiang.springcloud.service.PaymentFeignService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentFallbackService implements PaymentFeignService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "---PaymentFallbackService---paymentInfo_OK 宕机了" + "\t" + UUID.randomUUID().toString();
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "---PaymentFallbackService---paymentInfo_TimeOut " + "\t" + UUID.randomUUID().toString();
    }

}
