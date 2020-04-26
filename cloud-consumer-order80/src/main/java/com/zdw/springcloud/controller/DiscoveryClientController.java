package com.zdw.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class DiscoveryClientController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/discovery/info")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for(String element : services){
            System.out.println("******   "+element);
        }
        //通过服务别名获取服务的实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance : instances){
            System.out.println("服务主机："+instance.getHost()+"，服务端口："+instance.getPort()+"，服务ID"+instance.getServiceId()+"，服务uri："+instance.getUri());
        }
        return discoveryClient;
    }
}
