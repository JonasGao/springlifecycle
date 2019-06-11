package com.jonas.test.spring.lifecycle.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;

import static com.jonas.test.spring.lifecycle.Log.println;

@Component
@Slf4j
public class MyEventListener {

    @EventListener(ContextRefreshedEvent.class)
    public void handleContextRefreshedEvent() {
        println(log, "(@EventListener)", "handleContextRefreshedEvent");
    }

    @EventListener(ContextStartedEvent.class)
    public void handleContextStartedEvent() {
        println(log, "(@EventListener)", "handleContextStartedEvent");
    }

    @EventListener(ContextStoppedEvent.class)
    public void handleContextStoppedEvent() {
        println(log, "(@EventListener)", "handleContextStoppedEvent");
    }

    @EventListener(ContextClosedEvent.class)
    public void handleContextClosedEvent() {
        println(log, "(@EventListener)", "handleContextClosedEvent");
    }
}
