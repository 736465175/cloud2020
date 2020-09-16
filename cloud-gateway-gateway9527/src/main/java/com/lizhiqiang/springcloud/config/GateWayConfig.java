package com.lizhiqiang.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Administrator
 * @Date: 2020/9/14  0014 18:22
 * @Description: 
**/
@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder builder = routeLocatorBuilder.routes();
//        https://news.sina.com.cn/china/
        builder.route("path_route_lizhiqiang1",
                r -> r.path("/china")
                        .uri("https://news.sina.com.cn/china/")).build();
        return builder.build();
    }


}
