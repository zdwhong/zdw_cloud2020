package com.zdw.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface MyLoadBalancer {
    /**
     * 获取存活的服务实例列表
     * @param serviceInstances
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
