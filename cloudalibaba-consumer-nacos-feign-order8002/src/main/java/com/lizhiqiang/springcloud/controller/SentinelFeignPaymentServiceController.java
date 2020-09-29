package com.lizhiqiang.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
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



//    没配置blockHandler，fallback；降级时走默认全局熔断兜底方法，sentinel控制台限流配置违规时页面报异常，客户端运行错误走页面报错
//    @SentinelResource(value = "sentinelfeign")

//    没配置blockHandler，配置fallback；降级时走默认全局熔断兜底方法，sentinel控制台限流配置违规时走fallback，客户端运行错误走fallback兜底
//    @SentinelResource(value = "sentinelfeign", fallback = "handlerFallBack")

//    没配置fallback，配置blockHandler；降级时走默认全局熔断兜底方法，sentinel控制台限流配置违规时走blockHandler，客户端运行错误走页面报错
//    @SentinelResource(value = "sentinelfeign", blockHandler = "blockHandler")

//    配置fallback，配置blockHandler；降级时走默认全局熔断兜底方法，sentinel控制台限流配置违规时走blockHandler，客户端运行错误走fallback兜底
//    @SentinelResource(value = "sentinelfeign", blockHandler = "blockHandler", fallback = "handlerFallBack")

//      配置fallback，blockHandler，exceptionsToIgnore；降级时走默认全局熔断兜底方法(IllegalArgumentException除外)，
//      sentinel控制台限流配置违规时走blockHandler，客户端运行错误走fallback兜底(IllegalArgumentException除外)
    @SentinelResource(value = "sentinelfeign", fallback = "handlerFallBack", blockHandler = "blockHandler",
            exceptionsToIgnore = {IllegalArgumentException.class})
    @GetMapping(value = "/consumer/payment/sentinelfeign/{id}")
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

    //私有的 fallback
    public CommonResult<Payment> handlerFallBack(Long id, Throwable e){
        Payment payment = new Payment(id, null);
        return new CommonResult<>(444, "私有的 兜底异常handlerFallBack ，错误内容：" + e.getMessage(), payment);
    }

    //私有的blockHandler
    public CommonResult<Payment> blockHandler(Long id, BlockException e){
        Payment payment = new Payment(id, null);
        return new CommonResult<>(445, "私有的 blockHandler-sentinel限流 ，错误内容：" + e.toString(), payment);
    }
}
