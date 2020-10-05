package com.lizhiqiang.springcloud.alibaba.service;

import com.lizhiqiang.springcloud.entities.CommonResult;
import com.lizhiqiang.springcloud.entities.Storage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(value = "${service-url.nacos-seata-storage-service}", fallback = SeataOrderFallbackService.class)
@FeignClient(value = "${service-url.nacos-seata-storage-service}")
public interface StorageService {

    @PostMapping("/seata/storage/decrease")
    CommonResult<Storage> decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
