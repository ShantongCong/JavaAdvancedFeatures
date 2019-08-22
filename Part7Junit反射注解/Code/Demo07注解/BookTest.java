package com.butchy.Demo07注解;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
//解析注解
public class BookTest {
    @Test
    public void test1() throws NoSuchMethodException {
        //先获取类对象
        Class<BookStore> bookStoreClass = BookStore.class;
        //再调用getAnnotation方法
        //
        BookInfo annotation = bookStoreClass.getAnnotation(BookInfo.class);

        System.out.println("=========");
        System.out.println(Arrays.toString(annotation.authors()));
        System.out.println(annotation.name());
        System.out.println(annotation.price());

        System.out.println("=========================");
        //先获取方法对象
        Method show = bookStoreClass.getMethod("show");
        //
        BookInfo annotation1 = show.getAnnotation(BookInfo.class);
        System.out.println(annotation1.name());
        System.out.println(annotation1.price());
        System.out.println(Arrays.toString(annotation1.authors()));


    }
}
