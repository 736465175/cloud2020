package com.lizhiqiang.springcloud.alibaba.service.impl;

import com.lizhiqiang.springcloud.alibaba.dao.AccountDao;
import com.lizhiqiang.springcloud.alibaba.service.AccountService;
import com.lizhiqiang.springcloud.entities.Account;
import com.lizhiqiang.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public CommonResult<Account> decrease(Long userId, BigDecimal money) {
        log.info("------->nacos-seata-storage-service 扣减余额开始");
//        TODO 模拟超时异常，全局事务回滚？
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accountDao.decrease(userId, money);
        log.info("------->nacos-seata-storage-service 扣减余额结束");
        return new CommonResult<>(200, "减余额成功");
    }
}
