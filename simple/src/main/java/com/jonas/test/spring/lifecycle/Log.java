package com.jonas.test.spring.lifecycle;

import org.slf4j.Logger;

public final class Log {
    public static void println(Logger log, String component, String method) {
        println(log, component, method, "");
    }

    public static void println(Logger log, String component, String method, String msg) {
        log.info(String.format("%-85s | %s", component + "." + method, msg));
    }
}
