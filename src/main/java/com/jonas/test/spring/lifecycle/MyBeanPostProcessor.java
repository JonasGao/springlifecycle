package com.jonas.test.spring.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import static com.jonas.test.spring.lifecycle.Log.println;

@Component
@Slf4j
public class MyBeanPostProcessor implements BeanPostProcessor {

    private final BeforeMyBeanPostProcessor beforeMyBeanPostProcessor;

    @Autowired
    public MyBeanPostProcessor(BeforeMyBeanPostProcessor beforeMyBeanPostProcessor) {
        this.beforeMyBeanPostProcessor = beforeMyBeanPostProcessor;
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        println(log, "BeanPostProcessor", "postProcessBeforeInitialization", s);
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        println(log, "BeanPostProcessor", "postProcessAfterInitialization", s);
        return o;
    }
}

@Component
class BeforeMyBeanPostProcessor {
}
