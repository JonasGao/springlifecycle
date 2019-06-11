package com.other.test;

import com.jonas.test.spring.lifecycle.MyImportProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import static com.jonas.test.spring.lifecycle.Log.println;

@Slf4j
public class OtherConfigurationAware implements BeanFactoryPostProcessor, BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        println(log, "BeanDefinitionRegistryPostProcessor", "postProcessBeanDefinitionRegistry");
        println(log, "", "", "这里注意！下面一行日志会提示myConfigurationAwareConfiguration被过早的创建了。而且无论运行多少次，出现的位置相同。可能说明Spring对registry的处理要告一段落了。");
        println(log, "", "", "可以看到，警告之后，就开始处理factory了。");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        println(log, "BeanFactoryPostProcessor", "postProcessBeanFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        println(log, "ApplicationContextAware", "setApplicationContext");
        println(log, "ApplicationContextAware", "getBean (MyImportProperties) (Before)");
        MyImportProperties properties = applicationContext.getBean(MyImportProperties.class);
        println(log, "ApplicationContextAware", "getBean (MyImportProperties) (After)", String.valueOf(properties));
        println(log, "", "", "这里还是applicationContext.getBean(MyImportProperties.class)。get出来，属性还是null。");
    }
}
