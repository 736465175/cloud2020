package com.lizhiqiang.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

//主启动类有包扫描注解(@MapperScan)作用等同于@Mapper
//@Mapper
public interface AccountDao {
    void decrease(@Param("userId") Long userId,@Param("money") BigDecimal money);
}
