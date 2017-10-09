package com.other.test;

import org.springframework.context.annotation.Bean;

import static com.jonas.test.spring.lifecycle.Log.println;

public class OtherConfigurationAwareConfiguration {
    public OtherConfigurationAwareConfiguration() {
        println("OtherConfigurationAwareConfiguration", "constructor");
    }

    @Bean
    public OtherConfigurationAware otherConfigurationAware() {
        println("OtherConfigurationAwareConfiguration", "otherConfigurationAware");
        return new OtherConfigurationAware();
    }
}
