package com.lizhiqiang.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lizhiqiang.springcloud.entities.CommonResult;
import com.lizhiqiang.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {

    @Value("${service-url.nacos-user-service}")
    private String serviceId;

    @Resource
    private RestTemplate restTemplate;


    @GetMapping(value = "/consumer/payment/fallback/{id}")
//    @SentinelResource(value = "fallback") //没配置熔断兜底方法
//    @SentinelResource(value = "fallback", fallback = "handlerFallBack") //fallback只负责业务异常
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler") //blockHandler只负责sentinel控制台限流配置违规
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "handlerFallBack")
    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "handlerFallBack"
            , exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult fallBack(@PathVariable("id") Long id){
        CommonResult commonResult = restTemplate.getForObject(serviceId + "/nacos/sentinel/paymentSQL/" + id, CommonResult.class, id);
        if(id == 4){
            throw new IllegalArgumentException("IllegalArgumentException 非法参数异常。。。");
        }else if(commonResult == null || commonResult.getData() == null){
            throw new NullPointerException("NullPointerException 该 ID没有对应值");
        }
        return commonResult;
    }

    //fallback
    public CommonResult<Payment> handlerFallBack(Long id, Throwable e){
        Payment payment = new Payment(id, null);
        return new CommonResult<>(444, "兜底异常handlerFallBack ，错误内容：" + e.getMessage(), payment);
    }
    //blockHandler
    public CommonResult<Payment> blockHandler(Long id, BlockException e){
        Payment payment = new Payment(id, null);
        return new CommonResult<>(445, "blockHandler-sentinel限流 ，错误内容：" + e.toString(), payment);
    }

}
