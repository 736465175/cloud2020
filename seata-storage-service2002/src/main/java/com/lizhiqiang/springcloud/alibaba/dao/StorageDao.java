package com.lizhiqiang.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Param;

//主启动类有包扫描注解(@MapperScan)作用等同于@Mapper
//@Mapper
public interface StorageDao {
    void decrease(@Param("productId") Long productId,@Param("countA") Integer countA);
}
