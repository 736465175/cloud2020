package com.lizhiqiang.springcloud.controller;

import com.lizhiqiang.springcloud.service.PaymentFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class OrderFeignHystrixController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        log.info("调用 PaymentFeignService 的OpenFeign 接口");
        return paymentFeignService.paymentInfo_OK(id);
    }

    //测试Hystrix超时 服务降级，模拟长流程调用
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod" ,commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
//    })
    @HystrixCommand
    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    public String paymentFeignTimeout(@PathVariable("id") Integer id){
        //openFeign - ribbon 的调用，客户端一般默认等待1秒钟
        return paymentFeignService.paymentInfo_TimeOut(id);
    }

    private String paymentTimeOutFallbackMethod(Integer id){
        return "线程池 ：" + Thread.currentThread().getName()
                + " 我是消费者80，对方支付系统繁忙，请10秒后再尝试；或者自己出错，请检查自己 ，id : "
                + id + "\t" + UUID.randomUUID().toString();
    }

    //下面是全局fallback方法
    public String paymentGlobalFallbackMethod(){
        return "Global 异常处理信息，请稍后再试！";
    }


}
