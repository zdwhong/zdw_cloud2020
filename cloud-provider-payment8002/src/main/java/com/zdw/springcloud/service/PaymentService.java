package com.zdw.springcloud.service;

import com.zdw.springcloud.entities.Payment;

public interface PaymentService {
    /**
     * 新增
     *
     * @param payment
     * @return
     */
    int create(Payment payment);

    /**
     * 根据Id查询
     *
     * @param id
     * @return
     */
    Payment getPaymentById(Long id);
}
