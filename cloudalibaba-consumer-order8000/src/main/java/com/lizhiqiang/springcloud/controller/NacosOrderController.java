package com.lizhiqiang.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class NacosOrderController {

    @Value("${service-url.nacos-user-service}")
    private String serviceId;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/nacos/{id}")
    public String getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(serviceId + "/payment/nacos/" + id, String.class);
    }

}
