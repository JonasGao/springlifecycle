package com.jonas.test.spring.lifecycle;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

import static com.jonas.test.spring.lifecycle.Log.println;

@Component
public class MyBeanNameAware implements BeanNameAware {
    @Override
    public void setBeanName(String s) {
        println("BeanNameAware", "setBeanName", s);
    }
}
