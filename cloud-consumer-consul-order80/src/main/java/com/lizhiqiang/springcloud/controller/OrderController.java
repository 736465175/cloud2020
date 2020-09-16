package com.lizhiqiang.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    /**
     * 集群版（Eureka/provider），只关注微服务名称
     */
    private static final String paymentUrl="http://cloud-payment-consul-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String paymentInfo(){
        return restTemplate.getForObject(paymentUrl + "/payment/consul", String.class);
    }

}
