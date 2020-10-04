package com.lizhiqiang.springcloud.alibaba.controller;

import com.lizhiqiang.springcloud.alibaba.service.AccountService;
import com.lizhiqiang.springcloud.entities.Account;
import com.lizhiqiang.springcloud.entities.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
public class SeataAccountController {

    @Resource
    private AccountService accountService;

    @PostMapping("/seata/account/decrease")
    public CommonResult<Account> decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){
        return accountService.decrease(userId, money);
    }
}
