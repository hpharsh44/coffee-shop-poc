package com.digital.franchise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.digital"})
@EntityScan(value = "com.digital")
@EnableJpaAuditing
@EnableJpaRepositories("com.digital")
public class FranchiseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FranchiseServiceApplication.class, args);
    }
}
