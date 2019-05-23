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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.jonas.test.spring.lifecycle.Log.println;

@Slf4j
public class MyConfigurationAware implements BeanFactoryPostProcessor, BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        println(log, "BeanDefinitionRegistryPostProcessor", "postProcessBeanDefinitionRegistry", "还是优先（尽快）处理registry。级别真的高。不过这里不是扫描到的，而是来自上面的@Configuration，MyConfigurationAwareConfiguration。说明会尽快收集类型信息，然后有些类型信息会尽早处理？？？还是得看看源码");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        println(log, "BeanFactoryPostProcessor", "postProcessBeanFactory", "beanFactory 类型为 " + beanFactory.getClass().getCanonicalName());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        println(log, "ApplicationContextAware", "setApplicationContext", "这就没啥好说的了，参照上面的说明");
    }
}

@Configuration
@EnableConfigurationProperties(MyConfigurationAwareProperties.class)
@Slf4j
class MyConfigurationAwareConfiguration {
    public MyConfigurationAwareConfiguration() {
        println(log, "@Configuration", "constructor", "开始处理@Configuration了？？？这个@Configuration也是由主Context扫描到的。参考下一行日志，这里开始构造，但是还没开始调用@Bean。应该也是在收集类型信息");
    }

    @Bean
    public MyConfigurationAware myConfigurationAware(MyConfigurationAwareProperties properties) {
        println(log, "@Bean", "MyConfigurationAwareConfiguration.myConfigurationAware", "开始调用@Bean方法，这里取得了@EnableConfigurationProperties(MyConfigurationAwareProperties.class)的实例");
        return new MyConfigurationAware();
    }
}

@ConfigurationProperties("com.test.aware.config")
class MyConfigurationAwareProperties {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}