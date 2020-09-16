package com.lizhiqiang.springcloud.service;

import com.lizhiqiang.springcloud.service.impl.PaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//绑定微服务接口，以及服务降级处理类
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-SERVICE", fallback = PaymentFallbackService.class)
public interface PaymentFeignService {

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    //测试Hystrix超时 服务降级，模拟长流程调用
    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
