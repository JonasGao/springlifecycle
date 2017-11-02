package com.jonas.test.spring.issue1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class Configuration1 {
    @Bean
    public Interface1 interface1() {
        return new Interface1() {
            @Override
            public String toString() {
                return "Hello, World!";
            }
        };
    }
}
