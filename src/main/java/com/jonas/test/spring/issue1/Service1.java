package com.jonas.test.spring.issue1;

public class Service1 {
    public Service1(Interface1 i) {
        System.out.println(i);
    }

    @Override
    public String toString() {
        return "I`m Service1";
    }
}
