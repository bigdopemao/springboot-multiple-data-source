package com.mao.config.annotation;

import java.lang.annotation.*;

/**
 * @author bigdope
 * @create 2020-01-13
 **/
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MultiDataSource {

    String name() default "main";

}
