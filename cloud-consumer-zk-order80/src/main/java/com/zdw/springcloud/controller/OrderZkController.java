package com.zdw.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderZkController {

    //cloud-provider-zk-payment 是支付服务者注册到zookeeper的名称
    public static final String INVOKE_URL = "http://cloud-provider-zk-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    public String paymentInfo() {
        //通过RestTemplate调用 cloud-provider-zk-payment 的服务
        return restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
    }
}
