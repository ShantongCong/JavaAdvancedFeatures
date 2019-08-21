package com.butchy.Demo02获取Class对象;

import org.junit.Test;

public class Test01 {
    @Test
    public void getClassObj() throws ClassNotFoundException {
        Class<?> cls1 = Class.forName("com.butchy.Demo02获取Class对象.User");
        System.out.println(cls1);

        Class<User> cls2 = User.class;
        System.out.println(cls2);

        Class<? extends User> cls3 = new User().getClass();
        System.out.println(cls3);

        System.out.println(cls1==cls2);//类被加载时只会创建一个Class对象，所以他们的地址时相同的
        System.out.println(cls1==cls3);//true
    }
    @Test
    public void test2() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //三种获得类的class对象的方法
        Class<?> cls1 = Class.forName("com.butchy.Demo02获取Class对象.User");
        System.out.println(cls1);

        Class<User> cls2 = User.class;
        System.out.println(cls2);

        Class<? extends User> cls3 = new User().getClass();
        System.out.println(cls3);

        //1. String getSimpleName(); 获得简单类名，只是类名，没有包
        String simpleName = User.class.getSimpleName();
        System.out.println("simpleName = " + simpleName);

        //2. String getName(); 获取完整类名，包含包名+类名
        String name = cls1.getName();
        System.out.println("name = " + name);

        //3. T newInstance() ;创建此 Class 对象所表示的类的一个新实例。
        User user = new User().getClass().newInstance();
        System.out.println("user = " + user);
        System.out.println("=========");
        System.out.println(new User());


        // 要求：==类必须有public的无参数构造方法==【过时的方法，不建议使用】


        //总结
        //获取类的Class对象三种方法
        //1.Class.forName("User.path"); 通过类的路径获取
        //2.User.getClass 通过类名直接获取
        //3.new User.class 通过类的对象获取

        //获取类名信息
        //1.getName 返回路径字符串
        //2.getSimpleName 返回简单的类名
        //3.newInstance() 获得该类的新的对象(已过时


    }
}
