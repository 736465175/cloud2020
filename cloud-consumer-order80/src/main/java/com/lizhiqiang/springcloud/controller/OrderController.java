package com.lizhiqiang.springcloud.controller;

import com.lizhiqiang.springcloud.entities.CommonResult;
import com.lizhiqiang.springcloud.entities.Payment;
import com.lizhiqiang.springcloud.lib.IMyLoaderBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    //单机版可以写死
//    private static final String paymentUrl="http://localhost:8001";

    /**
     * 集群版（Eureka/provider），只关注微服务名称
     */
    private static final String paymentUrl="http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    //自定义负载均衡算法
    @Resource
    private IMyLoaderBalancer myLoaderBalancer;

    @Resource
    private DiscoveryClient discoveryClient;    // 暴露自己的服务的信息


    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
//        return restTemplate.postForObject(paymentUrl + "/payment/create", payment, CommonResult.class);
        return restTemplate.postForEntity(paymentUrl + "/payment/create", payment, CommonResult.class).getBody();
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
//        return restTemplate.getForObject(paymentUrl + "/payment/get/" + id , CommonResult.class);
        log.info("使用getForEntity");
        return restTemplate.getForEntity(paymentUrl + "/payment/get/" + id , CommonResult.class).getBody();
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB(){
        //微服务名称 对应的实例列表
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if(instances == null || instances.size() <=0){
            return null;
        }

        ServiceInstance serviceInstance = myLoaderBalancer.instance(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }

}
