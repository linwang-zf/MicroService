package com.oes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ShoppingMain {
    public static void main(String[] args) {
        SpringApplication.run(ShoppingMain.class, args);
    }
}
