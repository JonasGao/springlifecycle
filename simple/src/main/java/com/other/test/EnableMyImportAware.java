package com.other.test;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({MyImportAware.class, OtherConfigurationAwareConfiguration.class})
public @interface EnableMyImportAware {
}
