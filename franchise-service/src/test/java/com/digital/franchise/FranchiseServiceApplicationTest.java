package com.digital.franchise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootTest
@EnableFeignClients("com.digital")
public class FranchiseServiceApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(FranchiseServiceApplicationTest.class, args);
    }
}
