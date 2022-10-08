package com.digital.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootTest
@EnableFeignClients("com.digital")
public class ConsumerServiceApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerServiceApplicationTest.class, args);
    }
}
