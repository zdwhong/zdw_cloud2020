package com.zdw.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义负载均衡路由规则类
 **/
@Configuration
public class MyRibbonRule {
    @Bean
    public IRule myRule() {
        // 定义为随机
        return new RandomRule();
    }
}
