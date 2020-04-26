package com.zdw.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentConsulController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "payment/consul")
    public String paymentConsul() {
        log.info("*****8006服务被调用了....");
        return "SpringCloud with consul：" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
