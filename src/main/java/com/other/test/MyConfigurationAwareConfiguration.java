package com.other.test;

import org.springframework.context.annotation.Bean;

import static com.jonas.test.spring.lifecycle.Log.println;

public class MyConfigurationAwareConfiguration {
    public MyConfigurationAwareConfiguration() {
        println("MyConfigurationAwareConfiguration", "constructor");
    }

    @Bean
    public MyConfigurationAware myConfigurationAware() {
        println("MyConfigurationAwareConfiguration", "myConfigurationAware");
        return new MyConfigurationAware();
    }
}
