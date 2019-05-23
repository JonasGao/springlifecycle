package com.jonas.test.spring.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

import static com.jonas.test.spring.lifecycle.Log.println;

@Component
@Slf4j
public class MyBeanNameAware implements BeanNameAware {
    @Override
    public void setBeanName(String s) {
        println(log, "BeanNameAware", "setBeanName", s);
    }
}
