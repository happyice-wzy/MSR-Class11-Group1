package com.msr.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关只对请求处理，不做数据库处理，因此需要排除数据源的加载
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MsrGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsrGatewayApplication.class, args);
    }
}
