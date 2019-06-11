package com.jonas.test.spring.onebean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TheLifeOfABeanApplication {
    public static void main(String[] args) {
        SpringApplication.run(TheLifeOfABeanApplication.class);
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public TheBean theBean() {
        return new TheBean();
    }
}
