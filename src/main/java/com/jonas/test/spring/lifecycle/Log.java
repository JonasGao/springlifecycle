package com.jonas.test.spring.lifecycle;

public final class Log {
    public static void println(String component, String method) {
        println(component, method, "");
    }

    public static void println(String component, String method, String msg) {
        System.out.println(String.format("============> %-40s | %-50s | %-100s <============", component, method, msg));
    }
}
