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
        println(log, "(@Bean)", "otherConfigurationAware", "Configuration中的这些@Bean，何时被实例化（也就是调用方法）可能还是看实例的类型信息，比如实现了这些BeanFactory或者Registry一类接口的，会被较早的初始化");
        println(log, "", "", "但是，是哪里的代码促使这一行为，是一个很重要的问题。");
        return new OtherConfigurationAware();
    }
}
