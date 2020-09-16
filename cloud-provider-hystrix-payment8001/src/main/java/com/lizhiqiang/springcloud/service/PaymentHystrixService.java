package com.lizhiqiang.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentHystrixService {

    //正常访问OK的方法
    public String paymentInfo_OK(Integer id){
        return "线程池 ：" + Thread.currentThread().getName()
                + "  paymentInfo_OK ，id : " + id + "\t" + UUID.randomUUID().toString();
    }


    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler" ,commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    //异常访问的方法
    public String paymentInfo_TimeOut(Integer id){
        //假设等待时间3秒内正常，我们将其设置为5秒
//        int i = 1/0;
        int time = 1500;
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池 ：" + Thread.currentThread().getName()
                + "  paymentInfo_TimeOut ，id : " + id + "\t" +  time + "微秒" + "\t" + UUID.randomUUID().toString();
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池 ：" + Thread.currentThread().getName()
                + " 8001 系统报错或系统忙，请稍后再试 ，id : " + id + "\t" + UUID.randomUUID().toString();
    }

    //################################上面是测试服务降级###################################################
    //################################下面是服务熔断###################################################
    //参数来自 com.netflix.hystrix.HystrixCommandProperties
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //断路器开启
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if(id < 0){
            throw new RuntimeException("**********id 不能是负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    private String paymentCircuitBreaker_fallback(Integer id){
        return " id 不能为负数，请稍后再试！！！ id = " + id;
    }


}
