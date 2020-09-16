package com.lizhiqiang.springcloud.dao;

import com.lizhiqiang.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int create(Payment payment);

    //@Param注解的作用是给参数命名，参数命名后就能根据名字得到参数值，正确的将参数传入sql语句中
    Payment getPaymentById(@Param("id") Long id);
}
