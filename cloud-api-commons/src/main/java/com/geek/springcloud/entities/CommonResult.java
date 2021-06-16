package com.geek.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Robert
 * @create 2021/6/15 15:28
 * @Version 1.0
 * @Description: JSON封装体CommonResult
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code; // Data code; success or fail

    private String message; // Description

    //采用 泛型以确定传回的字符串 为其实体类型
    private T Data; //具体信息

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
