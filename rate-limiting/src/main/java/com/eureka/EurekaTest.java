package com.eureka;


import com.netflix.client.ClientFactory;
import com.netflix.client.config.IClientConfig;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EurekaTest {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    public List<ServiceInstance> getInstance() {
        List<ServiceInstance> instances = discoveryClient.getInstances("DS-BANYAN-GROUP-SERVICE-DEV");
        return instances;
    }

    public ServiceInstance getInstance1() {
        ServiceInstance instances = loadBalancerClient.choose("DS-BANYAN-GROUP-SERVICE-DEV");
        return instances;
    }

    public Server getInstance2() {
        ConfigurationManager.getConfigInstance().setProperty(
                "eureka.client.enabled", true);
        ConfigurationManager.getConfigInstance().setProperty(
                "eureka.client.serviceUrl.defaultZone", "http://10.200.35.254:30856/eureka/"
        );
        // ILoadBalancer loadBalancer = new ZoneAwareLoadBalancer<>();
        RoundRobinRule chooseRule = new RoundRobinRule();
        IClientConfig config = ClientFactory.getNamedConfig("DS-BANYAN-GROUP-SERVICE-DEV");
        DynamicServerListLoadBalancer lb =
                (DynamicServerListLoadBalancer) ClientFactory.getNamedLoadBalancer("DS-BANYAN-GROUP-SERVICE-DEV", config.getClass());
        Server instances = chooseRule.choose(lb);
        return instances;
    }
}
