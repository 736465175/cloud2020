package com.lizhiqiang.springcloud.controller;

import com.lizhiqiang.springcloud.entities.CommonResult;
import com.lizhiqiang.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class NacosPaymentController {

    @Value("${server.port}")
    private String serverPort;

    private final static HashMap<Long, Payment> paymentHashMap = new HashMap<>();
    static {
        paymentHashMap.put(1L, new Payment(1L, "111111111111111111111"));
        paymentHashMap.put(2L, new Payment(2L, "222222222222222222222"));
        paymentHashMap.put(3L, new Payment(3L, "333333333333333333333"));
    }

    @GetMapping("/nacos/sentinel/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = paymentHashMap.get(id);
        return new CommonResult<>(200, "from Map,test sentinel整合ribbon,serverPort:" + serverPort, payment);
    }

}
