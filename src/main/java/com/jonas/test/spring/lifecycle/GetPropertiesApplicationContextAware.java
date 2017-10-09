package com.jonas.test.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import static com.jonas.test.spring.lifecycle.Log.println;

@Component
@EnableConfigurationProperties(GetInContextAwareProperties.class)
public class GetPropertiesApplicationContextAware implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        println("GetPropertiesApplicationContextAware", "setApplicationContext");
        println("GetPropertiesApplicationContextAware", "getBean (GetInContextAwareProperties) (Before)");
        GetInContextAwareProperties properties = applicationContext.getBean(GetInContextAwareProperties.class);
        println("GetPropertiesApplicationContextAware", "getBean (GetInContextAwareProperties) (After)", properties.toString());
    }
}
