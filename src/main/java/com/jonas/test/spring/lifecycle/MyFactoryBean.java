package com.jonas.test.spring.lifecycle;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import static com.jonas.test.spring.lifecycle.Log.println;

@Component
public class MyFactoryBean implements FactoryBean<MyFactoryBeanBean> {
    @Override
    public MyFactoryBeanBean getObject() throws Exception {
        println("FactoryBean", "getObject");
        MyFactoryBeanBean bean = new MyFactoryBeanBean();
        bean.setName("Hello FactoryBean");
        return bean;
    }

    @Override
    public Class<?> getObjectType() {
        println("FactoryBean", "getObjectType");
        return MyFactoryBeanBean.class;
    }

    @Override
    public boolean isSingleton() {
        println("FactoryBean", "isSingleton");
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