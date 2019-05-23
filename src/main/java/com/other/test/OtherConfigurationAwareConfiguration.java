package com.other.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import static com.jonas.test.spring.lifecycle.Log.println;

@Slf4j
public class OtherConfigurationAwareConfiguration {
    public OtherConfigurationAwareConfiguration() {
        println(log, "OtherConfigurationAwareConfiguration", "constructor");
    }

    @Bean
    public OtherConfigurationAware otherConfigurationAware() {
        println(log, "OtherConfigurationAwareConfiguration", "otherConfigurationAware");
        return new OtherConfigurationAware();
    }
}
