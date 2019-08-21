package com.butchy.Demo04获取类中的方法;

import org.junit.Test;

import java.lang.reflect.Method;

public class Test1 {
    @Test
    public void test1() throws ClassNotFoundException {
        Class<? extends User> cls1 = new User().getClass();

        //1. Method[] getMethods()
        //获取所有的public修饰的成员方法，包括父类中
        Method[] cls1Methods = cls1.getMethods();
        for (Method cls1Method : cls1Methods) {
            System.out.println("cls1Method = " + cls1Method);
        }
        System.out.println("=========================================");
        //2. Method[] getDeclaredMethods()
        //获取当前类中所有的方法，包含私有的，不包括父类中
        Class<?> cls2 = Class.forName("com.butchy.Demo04获取类中的方法.User");
        Method[] cls2DeclaredMethods = cls2.getDeclaredMethods();
        for (Method cls2DeclaredMethod : cls2DeclaredMethods) {
            System.out.println("cls2DeclaredMethod = " + cls2DeclaredMethod);
        }


    }

    @Test
    public void test2() throws NoSuchMethodException {
        Class<? extends User> cls = new User().getClass();
        //3. Method getMethod("方法名", 方法的参数类型... 类型)
        //根据方法名和参数类型获得一个方法对象，只能是获取public修饰的
        Method method1 = cls.getMethod("print1");
        System.out.println(method1);

        Method method2 = cls.getMethod("print1", String.class);
        System.out.println("method2 = " + method2);
        //4. Method getDeclaredMethod("方法名", 方法的参数类型... 类型)
        //根据方法名和参数类型获得一个方法对象，包括private修饰的


    }

}
