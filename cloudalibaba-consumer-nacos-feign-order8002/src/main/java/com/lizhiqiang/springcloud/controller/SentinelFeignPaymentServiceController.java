package com.lizhiqiang.springcloud.controller;

import com.lizhiqiang.springcloud.entities.CommonResult;
import com.lizhiqiang.springcloud.entities.Payment;
import com.lizhiqiang.springcloud.service.SentinelFeignPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class SentinelFeignPaymentServiceController {

    //================================openFeign 实例客户端
    @Resource
    private SentinelFeignPaymentService feignPaymentService;

    @GetMapping(value = "/consumer/payment/sentinelfeign/{id}")
//    @SentinelResource(value = "fallback", fallbackClass = SentinelFeignPaymentFallbackService.class) //没配置熔断兜底方法
//    @SentinelResource(value = "fallback", fallback = "handlerFallBack") //fallback只负责业务异常
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler") //blockHandler只负责sentinel控制台限流配置违规
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "handlerFallBack")
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "handlerFallBack"
//            , exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> sentinelFeign(@PathVariable("id") Long id){
        CommonResult<Payment> commonResult = feignPaymentService.paymentSQL(id);
        if(id == 4){
            throw new IllegalArgumentException("IllegalArgumentException 非法参数异常。。。");
        }else if(commonResult == null || commonResult.getData() == null){
            throw new NullPointerException("NullPointerException 该 ID没有对应值");
        }
        log.info("测试 sentinel openfeign成功 ");
        return commonResult;
    }

}
