package com.lizhiqiang.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    /**
     * 我们在这里看到系统默认采用了ZoneAwareLoadBalancer负载均衡器。此时我们需要重新回到RibbonLoadBalancerClient类中继续看我们的execute方法的执行情况，在execute方法中，当获取到一个Server对象之后，将之包装成一个RibbonServer对象（从包装的过程我们可以发现，RibbonServer对象中保存了Server的所有信息，同时还保存了服务名serviceId、是否需要HTTPS等其他信息），然后再调用另一个重载的execute方法，在另一个重载的execute方法中最终调用到了LoadBalancerRequest中的apply方法，该方法向一个具体的服务实例发送请求，从而实现了从http://服务名/hello到http://域名/hello的转换
     * @author Lizhiqiang
     * @date 2020/9/1 10:25
     * @param []
     * @return org.springframework.web.client.RestTemplate
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
