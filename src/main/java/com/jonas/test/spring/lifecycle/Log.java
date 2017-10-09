package com.jonas.test.spring.lifecycle;

public final class Log {
    public static void println(String component, String method) {
        println(component, method, "");
    }

    public static void println(String component, String method, Object obj) {
        String msg;
        if (obj == null) {
            msg = "null";
        } else {
            msg = obj.getClass().getName();
        }
        println(component, method, msg);
    }

    public static void println(String component, String method, String msg) {
        System.out.println(String.format("============> %-40s | %-60s | %-100s <============", component, method, msg));
    }
}
