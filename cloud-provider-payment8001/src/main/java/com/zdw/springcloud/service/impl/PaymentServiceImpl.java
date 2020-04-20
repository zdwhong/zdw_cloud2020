package com.zdw.springcloud.service.impl;

import com.zdw.springcloud.dao.PaymentDao;
import com.zdw.springcloud.entities.Payment;
import com.zdw.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        int row = paymentDao.create(payment);
        return row;
    }

    @Override
    public Payment getPaymentById(Long id) {
        Payment payment = paymentDao.getPaymentById(id);
        return payment;
    }
}
