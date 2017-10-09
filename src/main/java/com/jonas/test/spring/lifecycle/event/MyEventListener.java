package com.jonas.test.spring.lifecycle.event;

import org.springframework.context.event.*;
import org.springframework.stereotype.Component;

import static com.jonas.test.spring.lifecycle.Log.println;

@Component
public class MyEventListener {

    @EventListener(ContextRefreshedEvent.class)
    public void handleContextRefreshedEvent() {
        println("MyEventListener", "handleContextRefreshedEvent");
    }

    @EventListener(ContextStartedEvent.class)
    public void handleContextStartedEvent() {
        println("MyEventListener", "handleContextStartedEvent");
    }

    @EventListener(ContextStoppedEvent.class)
    public void handleContextStoppedEvent() {
        println("MyEventListener", "handleContextStoppedEvent");
    }

    @EventListener(ContextClosedEvent.class)
    public void handleContextClosedEvent() {
        println("MyEventListener", "handleContextClosedEvent");
    }
}
