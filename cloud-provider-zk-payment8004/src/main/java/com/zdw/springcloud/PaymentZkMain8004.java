package com.zdw.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient  //这个注解一定要加，不能忘记
public class PaymentZkMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentZkMain8004.class,args);
    }
}
