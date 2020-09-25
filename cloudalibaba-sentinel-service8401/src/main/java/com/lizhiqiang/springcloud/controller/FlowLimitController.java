package com.lizhiqiang.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/sentinel/testA")
    public String testA(){
        return "nacos registry sentinel FlowLimitController , serverPort:" + serverPort + "\t  testA";
    }

    @GetMapping(value = "/sentinel/testB")
    public String testB(){
        return "nacos registry sentinel FlowLimitController , serverPort:" + serverPort + "\t  testB";
    }

    @GetMapping(value = "/sentinel/testD")
    public String testD(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("测试 testD RT");
        return "nacos registry sentinel FlowLimitController , serverPort:" + serverPort + "\t  测试 testD RT";
    }

    //说明在 Sentinel管理页面，新增热点规则 里面的资源名，要么=@GetMapping(value = "/sentinel/testHotKey")里的value值
    //要么=@SentinelResource(value = "testHotKey"..  里的value值
    @GetMapping(value = "/sentinel/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2){
        return "----------testHotKey ";
    }

    //testHotKey 接口的兜底方法，允许添加参数 blockException
    public String deal_testHotKey(String p1, String p2, BlockException blockException){
        return "----------deal_testHotKey "; //Sentinel系统默认的提示： Blocked by Sentinel (flow limiting)
    }

}
