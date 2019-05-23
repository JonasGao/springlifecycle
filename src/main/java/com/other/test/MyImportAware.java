package com.other.test;

import com.jonas.test.spring.lifecycle.MyImportProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import static com.jonas.test.spring.lifecycle.Log.println;

@Slf4j
public class MyImportAware implements BeanFactoryPostProcessor, BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        println(log, "BeanDefinitionRegistryPostProcessor", "postProcessBeanDefinitionRegistry", "registry 的类型是 " + registry.getClass());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        println(log, "BeanFactoryPostProcessor", "postProcessBeanFactory", "beanFactory 的类型是 " + beanFactory.getClass());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        println(log, "ApplicationContextAware", "setApplicationContext", applicationContext.getClass().getName() + "; " + applicationContext.getId());
        println(log, "ApplicationContextAware", "getBean (MyImportProperties) (Before)");
        MyImportProperties properties = applicationContext.getBean(MyImportProperties.class);
        println(log, "ApplicationContextAware", "getBean (MyImportProperties) (After)", properties.getName());
    }
}
