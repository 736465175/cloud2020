package com.lizhiqiang.springcloud.alibaba.service;

import com.lizhiqiang.springcloud.entities.CommonResult;
import com.lizhiqiang.springcloud.entities.Storage;

public interface StorageService {

    CommonResult<Storage> decrease(Long productId,Integer count);
}
