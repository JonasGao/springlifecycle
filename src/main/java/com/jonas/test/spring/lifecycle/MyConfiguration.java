package com.jonas.test.spring.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.jonas.test.spring.lifecycle.Log.println;

@Configuration
public class MyConfiguration {
    public MyConfiguration() {
        println("Configuration", "constructor");
    }

    @Bean
    public MyConfigurationOutputBean myConfigurationOutputBean() {
        return new MyConfigurationOutputBean();
    }
}

class MyConfigurationOutputBean {
    public MyConfigurationOutputBean() {
        println("MyConfigurationOutputBean", "constructor");
    }
}
