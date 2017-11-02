package com.jonas.test.spring.issue1;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class Configuration2 {
    @Bean
    @ConditionalOnBean(Interface1.class)
    public Service1 server1(Interface1 i) {
        return new Service1(i);
    }
}