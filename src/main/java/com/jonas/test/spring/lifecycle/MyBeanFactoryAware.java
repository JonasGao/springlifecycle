package com.jonas.test.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

import static com.jonas.test.spring.lifecycle.Log.println;

@Component
public class MyBeanFactoryAware implements BeanFactoryAware {
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        println("BeanFactoryAware", "setBeanFactory");
    }
}
