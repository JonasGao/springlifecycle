package com.other.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import static com.jonas.test.spring.lifecycle.Log.println;

@Slf4j
public class OtherConfigurationAwareConfiguration {
    public OtherConfigurationAwareConfiguration() {
        println(log, "", "constructor");
    }

    @Bean
    public OtherConfigurationAware otherConfigurationAware() {
        println(log, "@Bean", "otherConfigurationAware");
        return new OtherConfigurationAware();
    }
}
