package com.jonas.test.spring.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import static com.jonas.test.spring.lifecycle.Log.println;

@Component
@EnableConfigurationProperties(AutoAwareProperties.class)
@Slf4j
public class MyAutoAware implements BeanFactoryPostProcessor, BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        println(log, "BeanDefinitionRegistryPostProcessor", "postProcessBeanDefinitionRegistry",
                "之后开始处理registry。这里处理，应该是来自主Context的@SpringBootApplication带的@ComponentScan。 这里registry类型是"
                        + registry.getClass().getName());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        println(log, "BeanFactoryPostProcessor", "postProcessBeanFactory", "beanFactory 的类型是 " + beanFactory.getClass());
        println(log, "BeanFactoryPostProcessor", "getBean (AutoAwareProperties) (Before)");
        AutoAwareProperties properties = beanFactory.getBean(AutoAwareProperties.class);
        println(log, "BeanFactoryPostProcessor", "getBean (AutoAwareProperties) (After)", String.valueOf(properties));
        println(log, "", "", "这里是在postProcessBeanFactory方法里，get properties实例。可见还是太早。不绑定数据。");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        println(log, "ApplicationContextAware", "setApplicationContext", String.format("setApplicationContext是在Spring构造一个bean时调用的。所以对于整体上下文过程，这一句实际不用关心先后。" +
                "上下文对象信息是 %s 和 id %s", applicationContext.getClass().getName(), applicationContext.getId()));
    }
}

@ConfigurationProperties("com.test.auto")
@Slf4j
class AutoAwareProperties {

    public AutoAwareProperties() {
        println(log, "(@ConfigurationProperties)", "constructor");
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        println(log, "@ConfigurationProperties", "setter", "调用了 setName");
        this.name = name;
    }

    @Override
    public String toString() {
        return "AutoAwareProperties{" +
                "name='" + name + '\'' +
                '}';
    }
}
