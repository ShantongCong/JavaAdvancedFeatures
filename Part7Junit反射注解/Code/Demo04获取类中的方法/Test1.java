package com.butchy.Demo04获取类中的方法;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
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
    public void test2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<? extends User> cls = new User().getClass();
        //3. Method getMethod("方法名", 方法的参数类型... 类型)
        //根据方法名和参数类型获得一个方法对象，只能是获取public修饰的
        Method method1 = cls.getMethod("print1");
        System.out.println(method1);

        Method method2 = cls.getMethod("print1", String.class);
        System.out.println("method2 = " + method2);
        //4. Method getDeclaredMethod("方法名", 方法的参数类型... 类型)
        Class<? extends User> class2 = new User().getClass();

        Method print4 = class2.getDeclaredMethod("print4");
        System.out.println("print4 = " + print4);

        //根据方法名和参数类型获得一个方法对象，包括private修饰的

        //1. Object invoke(Object obj, Object... args)
        Class<? extends User> class3 = new User().getClass();

        User obj = class3.newInstance();//需要建立对象调用才能方法

        Method class3Method = class3.getMethod("print1", String.class);
        //Object invoke = class3Method.invoke(obj, "小红");
         class3Method.invoke(obj, "小红");//Class对象获取方法，方法再调用invoke，把参数输入进入

        Method method4 = class3.getDeclaredMethod("print4");
        method4.setAccessible(true);//遇到私有方法，需要暴力访问
        method4.invoke(obj);


        //invoke


        //根据参数args调用对象obj的该成员方法
        //如果obj=null，则表示该方法是静态方法

        //2. void setAccessible(true)
        //暴力反射，设置为可以直接调用私有修饰的成员方法【对于私有方法使用前需要调用该方法】


    }

}
