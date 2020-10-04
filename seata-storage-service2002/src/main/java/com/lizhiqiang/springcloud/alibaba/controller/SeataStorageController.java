package com.lizhiqiang.springcloud.alibaba.controller;

import com.lizhiqiang.springcloud.alibaba.service.StorageService;
import com.lizhiqiang.springcloud.entities.CommonResult;
import com.lizhiqiang.springcloud.entities.Storage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SeataStorageController {

    @Resource
    private StorageService storageService;

    @PostMapping("/seata/storage/decrease")
    public CommonResult<Storage> decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count){
        return storageService.decrease(productId, count);
    }
}
