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
        println(log, "", "constructor", "因为这个Configuration没有实现或者提供，任何特殊类型的实例，所以现在才初始化。");
    }

    @Bean
    public MyConfigurationOutputBean myConfigurationOutputBean(MyProperties properties) {
        println(log, "(@Bean)", "myConfigurationOutputBean", properties.toString());
        return new MyConfigurationOutputBean();
    }
}

@Slf4j
class MyConfigurationOutputBean {
    public MyConfigurationOutputBean() {
        println(log, "", "constructor");
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

    @Override
    public String toString() {
        return "MyProperties{" +
                "name='" + name + '\'' +
                '}';
    }
}