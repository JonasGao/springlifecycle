package com.jonas.test.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import static com.jonas.test.spring.lifecycle.Log.println;

@Component
@EnableConfigurationProperties(AutoAwareProperties.class)
public class MyAutoAware implements BeanFactoryPostProcessor, BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    @Autowired
    private AutoAwareProperties properties;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        println("MyAutoAware", "postProcessBeanDefinitionRegistry");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        println("MyAutoAware", "postProcessBeanFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        println("MyAutoAware", "setApplicationContext", applicationContext.getClass().getName() + "; " + applicationContext.getId());
        println("MyAutoAware", "autowired AutoAwareProperties", String.valueOf(properties));
    }
}

@ConfigurationProperties("com.test.auto")
class AutoAwareProperties {

    public AutoAwareProperties() {
        println("AutoAwareProperties", "constructor");
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        println("AutoAwareProperties", "setName");
        this.name = name;
    }

    @Override
    public String toString() {
        return "AutoAwareProperties{" +
                "name='" + name + '\'' +
                '}';
    }
}
