package com.lizhiqiang.springcloud.service.impl;

import com.lizhiqiang.springcloud.entities.CommonResult;
import com.lizhiqiang.springcloud.entities.Payment;
import com.lizhiqiang.springcloud.service.SentinelFeignPaymentService;
import org.springframework.stereotype.Service;

@Service
public class SentinelFeignPaymentFallbackService implements SentinelFeignPaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "FeignClient服务降级全局返回，SentinelFeignPaymentFallbackService",
                new Payment(id, "errorSerial"));
    }
}
