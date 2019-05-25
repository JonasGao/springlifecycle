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
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        println(log, "BeanPostProcessor", "postProcessBeforeInitialization", "beanName=" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        println(log, "BeanPostProcessor", "postProcessAfterInitialization", "beanName=" + beanName);
        return bean;
    }
}

@Component
@Slf4j
class BeforeMyBeanPostProcessor {
    public BeforeMyBeanPostProcessor() {
        println(log, "", "constructor", "故意测试给BeanPostProcessor接口的实现类，注入一个组件会怎样。果然，下一句就会报警告。");
    }
}
