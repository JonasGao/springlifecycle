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

public class OtherConfigurationAware implements BeanFactoryPostProcessor, BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        println("OtherConfigurationAware", "postProcessBeanDefinitionRegistry");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        println("OtherConfigurationAware", "postProcessBeanFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        println("OtherConfigurationAware", "setApplicationContext", applicationContext.getClass().getName() + "; " + applicationContext.getId());
        println("OtherConfigurationAware", "getBean (MyImportProperties) (Before)");
        MyImportProperties properties = applicationContext.getBean(MyImportProperties.class);
        println("OtherConfigurationAware", "getBean (MyImportProperties) (After)", properties.getName());
    }
}
