package com.lizhiqiang.springcloud.alibaba.service;

import com.lizhiqiang.springcloud.alibaba.service.impl.SeataOrderFallbackService;
import com.lizhiqiang.springcloud.entities.Account;
import com.lizhiqiang.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "${service-url.nacos-seata-account-service}", fallback = SeataOrderFallbackService.class)
//@FeignClient(value = "${service-url.nacos-seata-account-service}")
public interface AccountService {

    @PostMapping("/seata/account/decrease")
    CommonResult<Account> decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
