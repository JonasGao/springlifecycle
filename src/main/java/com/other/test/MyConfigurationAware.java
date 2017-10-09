package com.other.test;

import com.jonas.test.spring.lifecycle.MyImportProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import static com.jonas.test.spring.lifecycle.Log.println;

public class MyConfigurationAware implements BeanFactoryPostProcessor, BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        println("MyConfigurationAware", "postProcessBeanDefinitionRegistry");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        println("MyConfigurationAware", "postProcessBeanFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        println("MyConfigurationAware", "setApplicationContext", applicationContext.getClass().getName() + "; " + applicationContext.getId());
        println("MyConfigurationAware", "getBean (MyImportProperties) (Before)");
        MyImportProperties properties = applicationContext.getBean(MyImportProperties.class);
        println("MyConfigurationAware", "getBean (MyImportProperties) (After)", properties.getName());
    }
}
