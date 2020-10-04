package com.lizhiqiang.springcloud.alibaba.service;

import com.lizhiqiang.springcloud.entities.Account;
import com.lizhiqiang.springcloud.entities.CommonResult;

import java.math.BigDecimal;

public interface AccountService {

    CommonResult<Account> decrease(Long userId, BigDecimal money);
}
