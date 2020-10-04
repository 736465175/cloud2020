package com.lizhiqiang.springcloud.alibaba.service.impl;

import com.lizhiqiang.springcloud.alibaba.dao.StorageDao;
import com.lizhiqiang.springcloud.alibaba.service.StorageService;
import com.lizhiqiang.springcloud.entities.CommonResult;
import com.lizhiqiang.springcloud.entities.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public CommonResult<Storage> decrease(Long productId, Integer count) {
        log.info("------->nacos-seata-storage-service 扣减库存开始");
        storageDao.decrease(productId, count);
        log.info("------->nacos-seata-storage-service 扣减库存结束");
        return new CommonResult<>(200, "减库存成功");
    }
}
