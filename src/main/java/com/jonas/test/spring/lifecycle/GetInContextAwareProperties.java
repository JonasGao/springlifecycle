package com.jonas.test.spring.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.jonas.test.spring.lifecycle.Log.println;

@ConfigurationProperties("com.test.context.aware")
@Slf4j
public class GetInContextAwareProperties {

    public GetInContextAwareProperties() {
        println(log, "GetInContextAwareProperties", "constructor");
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        println(log, "GetInContextAwareProperties", "setName");
        this.name = name;
    }

    @Override
    public String toString() {
        return "GetInContextAwareProperties{" +
                "name='" + name + '\'' +
                '}';
    }
}
