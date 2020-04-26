package com.zdw.springcloud;

import com.zdw.rule.MyRibbonRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient  //这是一个Eureka的client端
@EnableDiscoveryClient  //服务发现开启
//在启动该微服务的时候就能去加载我们的自定义Ribbon配置类，从而使配置生效
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MyRibbonRule.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
