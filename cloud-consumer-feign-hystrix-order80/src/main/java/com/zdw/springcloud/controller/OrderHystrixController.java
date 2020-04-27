package com.zdw.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zdw.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "globalFallbackMethod")
public class OrderHystrixController {
    @Autowired
    PaymentHystrixService paymentHystrixService;

    /**
     * 正常访问
     * http://localhost/consumer/payment/hystrix/ok/1
     */
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    //@HystrixCommand
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        //int i = 1/0;
        return paymentHystrixService.paymentInfo_OK(id);
    }

    /**
     * 超时访问
     * http://localhost/consumer/payment/hystrix/timeout/1
     */
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    /*@HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2500")
    })*/
    //@HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        //int age = 10/0;
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }
    //兜底的方法，该方法的参数个数和类型等一定要和主方法的一样的
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80，对方支付系统繁忙！请10秒种后再试或者自己运行出错请检查自己，o(╥﹏╥)o";
    }

    //默认的全局兜底的方法，该方法不能有任何参数
    public String globalFallbackMethod() {
        return "Global异常处理信息,请稍后重试.o(╥﹏╥)o";
    }
}
