package com.butchy.Demo05获取类中的属性;

import org.junit.Test;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Test1 {
    @Test
    public void test01() throws ClassNotFoundException {
        Class<?> class1 = Class.forName("com.butchy.Demo05获取类中的属性.User");
        //1. Field[] getFields()
        //获取所有的包括父类中public修饰的属性对象，返回数组
        Field[] fields = class1.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        System.out.println("==========");
        //2. Field[] getDeclaredFields()
        //获取所有本类属性对象，包括private修饰的，返回数组

        for (Field field : new User().getClass().getDeclaredFields()) {
            System.out.println("field = " + field);
        }

    }
    @Test
    public void test2() throws NoSuchFieldException {
        Class<User> classUser = User.class;
        //3. Field getField(String name)
        //根据属性名获得属性对象，只能获取public修饰的
        Field momsName = classUser.getField("momsName");
        System.out.println("momsName = " + momsName);

        //4. Field getDeclaredField(String name)
        //根据属性名获得属性对象，包括private修饰的
        Field name = new User().getClass().getDeclaredField("name");
        name.setAccessible(true);
        System.out.println("name = " + name);

    }
    @Test
    public void test3() throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //Filed类中的方法
        Class<?> classUser = Class.forName("com.butchy.Demo05获取类中的属性.User");
        //1. Object get(Object obj)
        //  返回指定对象上此 Field 表示的字段的值。
        //1.创建对象
        Object obj = classUser.newInstance();
        //访问filed类，公开和私有使用不同的方法
        Field age = classUser.getDeclaredField("age");
        //3.对私有变量进行修改时，要强制暴力设置
        age.setAccessible(true);
        age.setInt(obj,12);
        System.out.println(age.getInt(obj));

        //2. void set(Object obj, Object value)
        //  将指定对象变量上此 Field 对象表示的字段设置为指定的新值。
        System.out.println("============");
        age.set(obj,13);//为什么不能设置字符串？
        System.out.println(age.get(obj));


        //3. void setAccessible(true);
        //	暴力反射，设置为可以直接访问私有类型的属性
    }

    @Test
    public void test4() throws ClassNotFoundException, NoSuchFieldException
            , IllegalAccessException, InstantiationException
            , NoSuchMethodException, InvocationTargetException {
        Class<?> classUser = Class.forName("com.butchy.Demo05获取类中的属性.User");

        //classUser.newInstance()已经过时
        //可以使用这个classUser.getDeclaredConstructor().newInstance()
        Field className = classUser.getDeclaredField("name");
        Object obj = classUser.getDeclaredConstructor().newInstance();


        className.setAccessible(true);
        className.set(obj,"李天一");

        System.out.println(className.get(obj));


    }

}
