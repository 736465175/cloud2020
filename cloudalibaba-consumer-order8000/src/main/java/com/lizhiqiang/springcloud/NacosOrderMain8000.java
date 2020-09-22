package com.lizhiqiang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosOrderMain8000 {
    public static void main(String[] args) {
        SpringApplication.run(NacosOrderMain8000.class, args);
    }
}
