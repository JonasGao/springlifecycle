package com.jonas.test.spring.lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import static com.jonas.test.spring.lifecycle.Log.println;

@Service
@EnableConfigurationProperties(MyServiceProperties.class)
public class MyService {
    @Autowired
    public MyService(MyServiceProperties properties) {
        println("MyService", "constructor", properties.getName());
    }
}

@ConfigurationProperties("com.test.service")
class MyServiceProperties {

    public MyServiceProperties() {
        println("MyServiceProperties", "constructor");
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}