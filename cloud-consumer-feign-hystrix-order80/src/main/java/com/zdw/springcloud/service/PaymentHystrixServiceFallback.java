package com.zdw.springcloud.service;


import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixServiceFallback implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "我是paymentInfo_OK的兜底方法，服务降级的时候由我来处理...";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "我是paymentInfo_TimeOut的兜底方法，服务降级的时候由我来处理...";
    }
}
