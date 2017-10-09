package com.jonas.test.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.jonas.test.spring.lifecycle.Log.println;

public class MyConfigurationAware implements BeanFactoryPostProcessor, BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        println("MyConfigurationAware", "postProcessBeanDefinitionRegistry", registry);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        println("MyConfigurationAware", "postProcessBeanFactory", beanFactory);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        println("MyConfigurationAware", "setApplicationContext", applicationContext.getClass().getName() + "; " + applicationContext.getId());
    }
}

@Configuration
@EnableConfigurationProperties(MyConfigurationAwareProperties.class)
class MyConfigurationAwareConfiguration {
    public MyConfigurationAwareConfiguration() {
        println("MyConfigurationAwareConfiguration", "constructor");
    }

    @Bean
    public MyConfigurationAware myConfigurationAware(MyConfigurationAwareProperties properties) {
        println("MyConfigurationAwareConfiguration", "MyConfigurationAwareProperties (myConfigurationAware)", properties.getName());
        println("MyConfigurationAwareConfiguration", "myConfigurationAware");
        return new MyConfigurationAware();
    }
}

@ConfigurationProperties("com.test.aware.config")
class MyConfigurationAwareProperties {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}