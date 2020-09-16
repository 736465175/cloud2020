package com.lizhiqiang.springcloud.lib;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface IMyLoaderBalancer {

    ServiceInstance instance(List<ServiceInstance> serviceInstances);

}
