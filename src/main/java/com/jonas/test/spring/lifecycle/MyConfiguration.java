package com.jonas.test.spring.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.jonas.test.spring.lifecycle.Log.println;

@Configuration
@EnableConfigurationProperties(MyProperties.class)
@Slf4j
public class MyConfiguration {
    public MyConfiguration() {
        println(log, "Configuration", "constructor");
    }

    @Bean
    public MyConfigurationOutputBean myConfigurationOutputBean(MyProperties properties) {
        println(log, "MyConfiguration", "myConfigurationOutputBean", properties.getName());
        return new MyConfigurationOutputBean();
    }
}

@Slf4j
class MyConfigurationOutputBean {
    public MyConfigurationOutputBean() {
        println(log, "MyConfigurationOutputBean", "constructor");
    }
}

@ConfigurationProperties("com.test.my")
@Slf4j
class MyProperties {

    public MyProperties() {
        println(log, "MyProperties", "constructor");
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        println(log, "MyProperties", "setName");
        this.name = name;
    }
}