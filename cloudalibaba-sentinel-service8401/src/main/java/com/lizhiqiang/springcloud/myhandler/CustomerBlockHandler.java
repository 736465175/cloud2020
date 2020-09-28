package com.lizhiqiang.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lizhiqiang.springcloud.entities.CommonResult;

public class CustomerBlockHandler {

    //必须是静态方法？？
    public static CommonResult handlerBlockException(BlockException blockException){
        return new CommonResult<>(444, "按客户自定义global 兜底 handlerBlockException1");
    }

    //必须是静态方法？？
    public static CommonResult handlerBlockException2(BlockException blockException){
        return new CommonResult<>(444, "按客户自定义global 兜底 handlerBlockException2");
    }
}
