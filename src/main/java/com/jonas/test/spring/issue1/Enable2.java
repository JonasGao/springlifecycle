package com.jonas.test.spring.issue1;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(Configuration2.class)
public @interface Enable2 {
}
