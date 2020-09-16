package com.lizhiqiang.springcloud.service;

import com.lizhiqiang.springcloud.entities.CommonResult;
import com.lizhiqiang.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//绑定微服务
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    //测试openfeign超时，模拟长流程调用
    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();
}
