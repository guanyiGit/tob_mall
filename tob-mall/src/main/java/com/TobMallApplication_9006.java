package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient //本服务启动后会自动注册进eureka服务中
@EnableDiscoveryClient //服务发现
@EnableTransactionManagement
@EnableFeignClients(basePackages = {"com.feign.service"})
@EnableCircuitBreaker
@ComponentScan(basePackages = {"com.fallBack.service","com"})
public class TobMallApplication_9006 {

    public static void main(String[] args) {
        SpringApplication.run(TobMallApplication_9006.class, args);
    }

}

