package com.jonas.test.spring.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import static com.jonas.test.spring.lifecycle.Log.println;

@Service
@EnableConfigurationProperties(MyServiceProperties.class)
@Slf4j
public class MyService {
    @Autowired
    public MyService(MyServiceProperties properties) {
        println(log, "", "constructor", String.valueOf(properties));
    }
}

@ConfigurationProperties("com.test.service")
@Slf4j
class MyServiceProperties {

    public MyServiceProperties() {
        println(log, "MyServiceProperties", "constructor");
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyServiceProperties{" +
                "name='" + name + '\'' +
                '}';
    }
}