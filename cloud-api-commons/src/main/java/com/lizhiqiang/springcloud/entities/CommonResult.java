package com.lizhiqiang.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> implements Serializable {

    //404 / not_found  前端妹子只看code，200就继续处理，其他就报错
    private Integer     code;
    private String      message;
    private T           data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
