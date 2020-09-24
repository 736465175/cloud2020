package com.lizhiqiang.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/sentinel/testA")
    public String testA(){
        return "nacos registry sentinel FlowLimitController , serverPort:" + serverPort + "\t  testA";
    }

    @GetMapping(value = "/sentinel/testB")
    public String testB(){
        return "nacos registry sentinel FlowLimitController , serverPort:" + serverPort + "\t  testB";
    }

}
