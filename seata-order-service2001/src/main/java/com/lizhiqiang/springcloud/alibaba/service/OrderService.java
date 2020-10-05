package com.lizhiqiang.springcloud.alibaba.service;

import com.lizhiqiang.springcloud.entities.CommonResult;
import com.lizhiqiang.springcloud.entities.Order;

public interface OrderService {
    //1.新建订单
    CommonResult<Order> create(Order order);
    //2.修改订单状态，不作为独立方法
//    int update(Long userId, Long productId);
}
