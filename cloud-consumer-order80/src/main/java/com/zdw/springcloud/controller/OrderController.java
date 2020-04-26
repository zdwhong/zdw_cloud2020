package com.zdw.springcloud.controller;

import com.zdw.springcloud.entities.CommonResult;
import com.zdw.springcloud.entities.Payment;
import com.zdw.springcloud.lb.MyLoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    //支付服务的地址
    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource  //这是Java里面的注解
    RestTemplate restTemplate;

    @Resource
    DiscoveryClient discoveryClient;

    /**
     * 自定义负载均衡规则
     */
    @Resource
    private MyLoadBalancer myLoadBalancer;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/create/postForEntity")
    public CommonResult<Payment> createByPostForEntity(Payment payment){
        //她比postForObject 后面多了一个 getBody()
        return restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class).getBody();
    }

    @GetMapping("consumer/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id){
        log.info("====从80去查询了支付模块....");
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getForEntity(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id, CommonResult.class);
        if(forEntity.getStatusCode().is2xxSuccessful()){//说明是成功的
            return forEntity.getBody();
        }else{
            return new CommonResult(444,"操作失败了...");
        }
    }


    /**
     * 路由规则: 轮询
     * http://localhost/consumer/payment/payment/lb
     */
    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = myLoadBalancer.instances(instances);
        //获取服务器端uri
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }
}
