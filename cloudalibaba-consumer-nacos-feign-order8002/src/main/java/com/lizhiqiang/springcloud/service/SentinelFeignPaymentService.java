package com.lizhiqiang.springcloud.service;

import com.lizhiqiang.springcloud.entities.CommonResult;
import com.lizhiqiang.springcloud.entities.Payment;
import com.lizhiqiang.springcloud.service.impl.SentinelFeignPaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//这种方式不能把 exception带给全局兜底方法
@FeignClient(value = "${service-url.nacos-user-service}", fallback = SentinelFeignPaymentFallbackService.class)
public interface SentinelFeignPaymentService {

    @GetMapping("/nacos/sentinel/paymentSQL/{id}")
    CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
