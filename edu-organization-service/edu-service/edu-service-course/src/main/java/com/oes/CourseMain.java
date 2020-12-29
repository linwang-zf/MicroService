package com.oes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CourseMain {
    public static void main(String[] args) {
        SpringApplication.run(CourseMain.class, args);
    }
}
