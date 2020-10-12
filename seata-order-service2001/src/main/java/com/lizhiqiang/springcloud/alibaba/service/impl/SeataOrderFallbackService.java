package com.lizhiqiang.springcloud.alibaba.service.impl;

import com.lizhiqiang.springcloud.alibaba.service.AccountService;
import com.lizhiqiang.springcloud.alibaba.service.StorageService;
import com.lizhiqiang.springcloud.entities.Account;
import com.lizhiqiang.springcloud.entities.CommonResult;
import com.lizhiqiang.springcloud.entities.Storage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SeataOrderFallbackService implements AccountService, StorageService {

    @Override
    public CommonResult<Account> decrease(Long userId, BigDecimal money) {
        return new CommonResult<>(401, "抱歉，扣除余额失败，请稍后再试!!!");
    }

    @Override
    public CommonResult<Storage> decrease(Long productId, Integer count) {
        return new CommonResult<>(402, "抱歉，删减库存失败，请稍后再试!!!");
    }
}
