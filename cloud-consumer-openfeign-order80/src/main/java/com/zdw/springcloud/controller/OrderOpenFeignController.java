package com.zdw.springcloud.controller;

import com.zdw.springcloud.entities.CommonResult;
import com.zdw.springcloud.entities.Payment;
import com.zdw.springcloud.service.PaymentOpenFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderOpenFeignController {

    @Resource
    PaymentOpenFeignService paymentOpenFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        log.info("===OpenFeign80服务被调用了，会去调用8001和8002的集群服务");
        return paymentOpenFeignService.get(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout() {
        // openfeign-ribbon, 客户端一般默认等待1秒钟
        return paymentOpenFeignService.paymentFeignTimeout();
    }

}
