package com.lizhiqiang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosSentinelOrderMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(NacosSentinelOrderMain8001.class, args);
    }
}
