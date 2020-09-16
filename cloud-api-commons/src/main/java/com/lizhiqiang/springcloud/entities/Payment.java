package com.lizhiqiang.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Payment 主实体， Json封装体CommonResult<T>
 * @author Lizhiqiang
 * @date 2020/8/28 17:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
