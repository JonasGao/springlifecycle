package com.jonas.test.spring.lifecycle;

import com.other.test.EnableMyImportAware;
import com.other.test.MyImportBeanDefinitionRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import static com.jonas.test.spring.lifecycle.Log.println;

@SpringBootApplication
@Import(MyImportBeanDefinitionRegistrar.class)
@EnableMyImportAware
@EnableConfigurationProperties(MyImportProperties.class)
public class Application {
    public static void main(String[] args) {
        println("SpringApplication", "run", "开始");
        SpringApplication.run(Application.class, args);
        println("SpringApplication", "run", "结束");
    }
}
