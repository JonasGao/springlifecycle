package com.jonas.test.spring.lifecycle;

import com.other.test.EnableMyImportAware;
import com.other.test.MyImportBeanDefinitionRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import static com.jonas.test.spring.lifecycle.Log.println;

@SpringBootApplication
@Import(MyImportBeanDefinitionRegistrar.class)
@EnableMyImportAware
@EnableConfigurationProperties(MyImportProperties.class)
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        println("SpringApplication", "run", "开始");
        SpringApplication.run(Application.class, args);
        println("SpringApplication", "run", "结束");
    }

    @Autowired(required = false)
    private BeanDefinitionRegistry beanDefinitionRegistry;

    @Autowired
    private ConfigurableListableBeanFactory configurableListableBeanFactory;

    @Autowired
    private MyService myService;

    @Override
    public void run(String... args) throws Exception {
        println("Application (CommandLineRunner)", "run");

        System.out.println("<====");
        System.out.println(beanDefinitionRegistry);
        System.out.println(configurableListableBeanFactory);
        System.out.println(myService);
        System.out.println("<====");
    }
}
