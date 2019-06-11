package com.jonas.test.spring.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import static com.jonas.test.spring.lifecycle.Log.println;

@Component
@Slf4j
public class MyFactoryBean implements FactoryBean<MyFactoryBeanBean> {
    private int getObjectTypeCount = 0;

    @Override
    public MyFactoryBeanBean getObject() throws Exception {
        println(log, "FactoryBean", "getObject");
        MyFactoryBeanBean bean = new MyFactoryBeanBean();
        bean.setName("Hello FactoryBean");
        return bean;
    }

    @Override
    public Class<?> getObjectType() {
        getObjectTypeCount++;
        println(log, "FactoryBean", "getObjectType", "也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第" + getObjectTypeCount + "次");
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