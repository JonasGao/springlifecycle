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
public class MyImportAware implements BeanFactoryPostProcessor, BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    public MyImportAware() {
        println(log, "MyImportAware", "constructor", "开始构造MyImportAware了。");
        println(log, "", "", "这个类是通过主Context类上标记@EnableMyImportAware注解，然后这个注解再@Import这个类。");
        println(log, "", "", "说明开始处理这类自定义@Import注解了，不过顺序不好确定，有可能是因为这个注解的书写顺序是在@Import下面");
        println(log, "", "", "才会比上面的MyImportBeanDefinitionRegistrar晚。");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        println(log, "BeanDefinitionRegistryPostProcessor", "postProcessBeanDefinitionRegistry", "还是registry，不用多说");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        println(log, "BeanFactoryPostProcessor", "postProcessBeanFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        println(log, "ApplicationContextAware", "setApplicationContext", "依然是没啥好说的");
        println(log, "ApplicationContextAware", "getBean (MyImportProperties) (Before)");
        MyImportProperties properties = applicationContext.getBean(MyImportProperties.class);
        println(log, "ApplicationContextAware", "getBean (MyImportProperties) (After)", String.valueOf(properties));
        println(log, "", "", "获取出来的MyImportProperties实例是有的。但是实例的属性，name却是null。实际配置文件是有内容的。");
        println(log, "", "", "这说明过早的获取了properties。1.x的Spring是何时绑定配置文件到@ConfigurationProperties实例的？");
        println(log, "", "", "这也说明，在setApplicationContext里做一些逻辑处理，需要考虑更多。");
        println(log, "", "", "并且可以看到这里applicationContext.getBean扫描了一次类型信息，并且FactoryBean的类型信息并没有被缓存。因为getObjectType又被调用了。");
    }
}
