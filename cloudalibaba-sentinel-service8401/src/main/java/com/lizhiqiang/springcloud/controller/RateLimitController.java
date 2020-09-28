package com.lizhiqiang.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lizhiqiang.springcloud.entities.CommonResult;
import com.lizhiqiang.springcloud.entities.Payment;
import com.lizhiqiang.springcloud.myhandler.CustomerBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/sentinel/byResource")
    @SentinelResource(value = "byResource", blockHandler = "byResourceHandle")
    public CommonResult<Payment> byResource(){
        return new CommonResult<>(200, "按资源名限流测试OK", new Payment(2020L, "serial001"));
    }

    public CommonResult byResourceHandle(BlockException blockException){
        return new CommonResult<>(444, blockException.getClass().getCanonicalName() + "\t 服务不可用");
    }


    @GetMapping("/sentinel/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class, blockHandler = "handlerBlockException2")
    public CommonResult<Payment> customerBlockHandler(){
        return new CommonResult<>(200, "按客户自定义兜底方法测试", new Payment(2020L, "serial003"));
    }
}
