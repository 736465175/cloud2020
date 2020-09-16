package com.lizhiqiang.springcloud.controller;

import com.lizhiqiang.springcloud.entities.CommonResult;
import com.lizhiqiang.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.lizhiqiang.springcloud.service.PaymentFeignService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        log.info("调用 PaymentFeignService 的OpenFeign 接口");
        return paymentFeignService.getPaymentById(id);
    }

    //测试openfeign超时，模拟长流程调用
    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //openFeign - ribbon 的调用，客户端一般默认等待1秒钟
        return paymentFeignService.paymentFeignTimeout();
    }
}
