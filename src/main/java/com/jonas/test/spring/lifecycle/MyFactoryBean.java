package com.jonas.test.spring.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import static com.jonas.test.spring.lifecycle.Log.println;

@Component
@Slf4j
public class MyFactoryBean implements FactoryBean<MyFactoryBeanBean> {
    @Override
    public MyFactoryBeanBean getObject() throws Exception {
        println(log, "FactoryBean", "getObject");
        MyFactoryBeanBean bean = new MyFactoryBeanBean();
        bean.setName("Hello FactoryBean");
        return bean;
    }

    @Override
    public Class<?> getObjectType() {
        println(log, "FactoryBean", "getObjectType");
        return MyFactoryBeanBean.class;
    }

    @Override
    public boolean isSingleton() {
        println(log, "FactoryBean", "isSingleton");
        return true;
    }
}

class MyFactoryBeanBean {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}