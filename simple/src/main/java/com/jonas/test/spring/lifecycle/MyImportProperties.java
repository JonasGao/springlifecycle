package com.jonas.test.spring.lifecycle;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("com.test.import")
public class MyImportProperties {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyImportProperties{" +
                "name='" + name + '\'' +
                '}';
    }
}
