package com.butchy.Demo07注解;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})//设置作用对象类型
@Retention(RetentionPolicy.RUNTIME)//设置作用时间
public @interface BookInfo {
    String name();

    double price() default 100;

    String[] authors();


}
