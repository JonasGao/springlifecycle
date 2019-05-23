package com.other.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import static com.jonas.test.spring.lifecycle.Log.println;

@Slf4j
public class OtherConfigurationAwareConfiguration {
    public OtherConfigurationAwareConfiguration() {
        println(log, "", "constructor", "这是主Context类上标记@EnableMyImportAware注解@Import的另一个类");
    }

    @Bean
    public OtherConfigurationAware otherConfigurationAware() {
        println(log, "@Bean", "otherConfigurationAware");
        return new OtherConfigurationAware();
    }
}
