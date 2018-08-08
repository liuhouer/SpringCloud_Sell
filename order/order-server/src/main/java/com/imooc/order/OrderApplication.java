package com.imooc.order;

import com.imooc.product.client.ProductClient;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
@SpringCloudApplication
@EnableFeignClients(basePackageClasses = ProductClient.class)
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
