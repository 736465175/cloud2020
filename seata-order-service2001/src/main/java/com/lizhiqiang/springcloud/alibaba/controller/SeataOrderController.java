package com.lizhiqiang.springcloud.alibaba.controller;

import com.lizhiqiang.springcloud.alibaba.service.OrderService;
import com.lizhiqiang.springcloud.entities.CommonResult;
import com.lizhiqiang.springcloud.entities.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SeataOrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/seata/order/create")
    public CommonResult<Order> create(Order order){
        return orderService.create(order);
    }


}
