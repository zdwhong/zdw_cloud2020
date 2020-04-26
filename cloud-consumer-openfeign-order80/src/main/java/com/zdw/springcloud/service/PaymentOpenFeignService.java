package com.zdw.springcloud.service;

import com.zdw.springcloud.entities.CommonResult;
import com.zdw.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * OpenFeign的业务逻辑接口类，里面的方法和8001的paymentController的方法以及路径都是一样的
 */
@Component
@FeignClient(name = "CLOUD-PAYMENT-SERVICE") //指定要从哪个服务调用
public interface PaymentOpenFeignService {
    @PostMapping(value = "payment/create")
    public CommonResult create(@RequestBody Payment payment);

    @GetMapping(value = "payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB();

    /**
     * 模拟feign超时
     */
    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();
}
