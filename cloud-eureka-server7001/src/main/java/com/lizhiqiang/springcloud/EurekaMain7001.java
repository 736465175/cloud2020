package com.lizhiqiang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务注册中心主启动类
 * @author Lizhiqiang
 * @date 2020/8/31 16:25
 */
@SpringBootApplication
@EnableEurekaServer //表明是注册中心，不是客户
public class EurekaMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7001.class, args);
    }
}
