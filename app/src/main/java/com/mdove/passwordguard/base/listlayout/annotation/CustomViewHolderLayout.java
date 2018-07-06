package com.mdove.passwordguard.base.listlayout.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface CustomViewHolderLayout {
    int customViewHolderLayout() default -1;
}
