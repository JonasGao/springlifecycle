package com.jonas.test.spring.lifecycle;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.jonas.test.spring.lifecycle.Log.println;

@ConfigurationProperties("com.test.context.aware")
public class GetInContextAwareProperties {

    public GetInContextAwareProperties() {
        println("GetInContextAwareProperties", "constructor");
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        println("GetInContextAwareProperties", "setName");
        this.name = name;
    }

    @Override
    public String toString() {
        return "GetInContextAwareProperties{" +
                "name='" + name + '\'' +
                '}';
    }
}
