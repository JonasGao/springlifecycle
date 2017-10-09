package com.other.test;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({MyImportAware.class, MyConfigurationAwareConfiguration.class})
public @interface EnableMyImportAware {
}
