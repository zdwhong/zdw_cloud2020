package com.zdw.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient  //用于注册服务到Consul注册中心
public class PaymentConsulMain8006 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentConsulMain8006.class,args);
    }
}
