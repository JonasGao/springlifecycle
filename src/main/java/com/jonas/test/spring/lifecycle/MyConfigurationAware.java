package com.jonas.test.spring.lifecycle;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
public class MyConfigurationAware implements BeanFactoryPostProcessor, BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        println(log, "MyConfigurationAware", "postProcessBeanDefinitionRegistry", "registry 类型为 " + registry.getClass().getCanonicalName());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        println(log, "MyConfigurationAware", "postProcessBeanFactory", "beanFactory 类型为 " + beanFactory.getClass().getCanonicalName());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        println(log, "MyConfigurationAware", "setApplicationContext", applicationContext.getClass().getName() + "; " + applicationContext.getId());
    }
}

@Configuration
@EnableConfigurationProperties(MyConfigurationAwareProperties.class)
@Slf4j
class MyConfigurationAwareConfiguration {
    public MyConfigurationAwareConfiguration() {
        println(log, "@Configuration", "constructor", "开始处理@Configuration了？？？这个@Configuration也是由主Context扫描到的");
    }

    @Bean
    public MyConfigurationAware myConfigurationAware(MyConfigurationAwareProperties properties) {
        println(log, "@Bean", "MyConfigurationAwareConfiguration.myConfigurationAware", "取得@EnableConfigurationProperties(MyConfigurationAwareProperties.class)的实例");
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