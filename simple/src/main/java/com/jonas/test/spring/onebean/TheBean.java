package com.jonas.test.spring.onebean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static com.jonas.test.spring.lifecycle.Log.println;

@Slf4j
public class TheBean implements InitializingBean, BeanNameAware, ApplicationContextAware, BeanFactoryAware {

    public TheBean() {
        println(log, "", "constructor");
    }

    @PostConstruct
    public void postConstruct() {
        println(log, "", "@PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        println(log, "", "@PreDestroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        println(log, "InitializingBean", "afterPropertiesSet");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        println(log, "BeanNameAware", "setBeanFactory");
    }

    @Override
    public void setBeanName(String name) {
        println(log, "ApplicationContextAware", "setBeanName");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        println(log, "BeanFactoryAware", "setApplicationContext");
    }

    public void init() {
        println(log, "(@Bean initMethod=)", "init");
    }

    public void destroy() {
        println(log, "(@Bean destroyMethod=)", "destroy");
    }
}
