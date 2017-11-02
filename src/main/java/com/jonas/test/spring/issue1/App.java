package com.jonas.test.spring.issue1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@SpringBootApplication

public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

@Component
class Runner implements CommandLineRunner {

    private final Service1 service1;

    Runner(Service1 service1) {
        this.service1 = service1;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(service1);
    }
}