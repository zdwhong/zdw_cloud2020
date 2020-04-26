package com.zdw.springcloud.controller;

import com.zdw.springcloud.entities.CommonResult;
import com.zdw.springcloud.entities.Payment;
import com.zdw.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")//从配置文件中获取server.port的值
    String serverPort;

    @PostMapping(value = "payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        if(result>0){
            return new CommonResult(200,"++插入数据库成功111,serverPort:" + serverPort, result);
        }
        return new CommonResult(444,"--插入数据库失败,serverPort:" + serverPort, null);
    }

    @GetMapping(value = "payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果: " + payment);
        //int a = 10/0;
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, payment);
        }
        return new CommonResult(444, "没有对应记录,查询ID:" + id, null);
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            // 暂停3秒钟
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
