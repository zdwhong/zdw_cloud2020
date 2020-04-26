package com.zdw.springcloud.dao;

import com.zdw.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    /**
     * 新增
     * @param payment
     */
    int create(Payment payment);

    /**
     * 根据Id查询
     * @param id
     */
    Payment getPaymentById(@Param("id") Long id);
}
