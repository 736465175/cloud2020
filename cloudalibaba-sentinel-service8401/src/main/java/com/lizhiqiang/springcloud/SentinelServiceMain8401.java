package com.lizhiqiang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient //yml配置了连接到nacos注册中心，相当于开启了
public class SentinelServiceMain8401 {
    public static void main(String[] args) {
        SpringApplication.run(SentinelServiceMain8401.class, args);
    }
}
