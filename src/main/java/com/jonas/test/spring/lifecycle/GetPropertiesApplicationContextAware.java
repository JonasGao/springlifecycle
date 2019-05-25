package com.jonas.test.spring.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import static com.jonas.test.spring.lifecycle.Log.println;

@Component
@EnableConfigurationProperties(GetInContextAwareProperties.class)
@Slf4j
public class GetPropertiesApplicationContextAware implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        println(log, "ApplicationContextAware", "setApplicationContext");
        println(log, "ApplicationContextAware", "getBean (GetInContextAwareProperties) (Before)");
        GetInContextAwareProperties properties = applicationContext.getBean(GetInContextAwareProperties.class);
        println(log, "ApplicationContextAware", "getBean (GetInContextAwareProperties) (After)", properties.toString());
    }
}
