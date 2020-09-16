package com.lizhiqiang.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeignConfig {

    @Bean
    public Logger.Level openFeignLogLevel(){
        return Logger.Level.FULL; //最全日志
    }

}
