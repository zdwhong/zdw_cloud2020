package com.zdw.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data  //get/set以及toString方法
@NoArgsConstructor  //无参构造
@AllArgsConstructor //有参构造
public class Payment implements Serializable {
    private Long id;
    /**
     * 支付流水号
     */
    private String serial;
}
