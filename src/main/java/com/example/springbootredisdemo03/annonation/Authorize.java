package com.example.springbootredisdemo03.annonation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限拦截 注解
 * @Author: qyq
 * @Date: 2022/09/19/22:54
 * @Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Authorize {
    String permission() default "";
}
