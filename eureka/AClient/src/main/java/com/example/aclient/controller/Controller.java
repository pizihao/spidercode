package com.example.aclient.controller;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class Controller {

    final DiscoveryClient discoveryClient;
    final ApplicationInfoManager properties;

    @Value("${spring.application.name}")
    String name;

    @GetMapping("/instanceList")
    public List<ServiceInstance> getInstance() {
        return discoveryClient.getInstances(name);
    }

    @GetMapping("/currentInstance")
    public InstanceInfo currentInstance() {
        return properties.getInfo();
    }

}
