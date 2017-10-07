package com.jonas.test.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import static com.jonas.test.spring.lifecycle.Log.println;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        println("BeanPostProcessor", "postProcessBeforeInitialization", s);
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        println("BeanPostProcessor", "postProcessAfterInitialization", s);
        return o;
    }
}
