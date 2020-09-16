package com.lizhiqiang.springcloud.filter;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("LZQ   come in MyLogGateWayFilter " + UUID.randomUUID().toString());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if(uname == null){
            log.warn("*********用户名为null，非法用户");
            //返回点东西给前端判断
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    //加载过滤器的顺序，数字越小优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
