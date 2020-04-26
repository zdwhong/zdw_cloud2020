package com.zdw.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderConfig {

    /**
     * 注册RestTemplate
     * @return
     */
    @Bean
    //@LoadBalanced  //注释掉，使用自定义的Ribbon负载均衡算法
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
