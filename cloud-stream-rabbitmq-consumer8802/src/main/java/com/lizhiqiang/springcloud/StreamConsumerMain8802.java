package com.lizhiqiang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient E版本开始可用通过配置，省略掉该注解
public class StreamConsumerMain8802 {

    public static void main(String[] args) {
        SpringApplication.run(StreamConsumerMain8802.class, args);
    }
}