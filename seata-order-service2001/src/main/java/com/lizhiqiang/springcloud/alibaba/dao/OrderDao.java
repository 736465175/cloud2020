package com.lizhiqiang.springcloud.alibaba.dao;

import com.lizhiqiang.springcloud.entities.Order;
import org.apache.ibatis.annotations.Param;

//主启动类有包扫描注解(@MapperScan)作用等同于@Mapper
//@Mapper
public interface OrderDao {
    //1.新建订单
    void create(Order order);
    //2.修改订单状态
    int update(@Param("userId") Long userId, @Param("productId") Long productId);
}
