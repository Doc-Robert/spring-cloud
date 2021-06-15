package com.geek.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Robert
 * @create 2021/6/15 15:25
 * @Version 1.0
 * @Description: payment model
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    //继承序列化
    private Long id;

    private String serial;
}
