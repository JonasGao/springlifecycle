package com.jonas.test.spring.lifecycle;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.jonas.test.spring.lifecycle.Log.println;

@Configuration
@EnableConfigurationProperties(MyProperties.class)
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

@ConfigurationProperties("com.test.my")
class MyProperties {

    public MyProperties() {
        println("MyProperties", "constructor");
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        println("MyProperties", "setName");
        this.name = name;
    }
}