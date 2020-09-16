package com.lizhiqiang.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyselfRule {

    @Bean
    public IRule myRule(){
        //定义为随机算法，默认为RoundRobbinRule轮询
        return new RandomRule();
    }
}
