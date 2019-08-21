package com.butchy.Demo03获取类中的构造器;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//Constructor是构造方法类，类中的每一个构造方法都是Constructor的对象
// 通过Constructor对象可以实例化对象。
public class Test01 {
    @Test
    public void test1() throws NoSuchMethodException {
        Class<User> cls1 = User.class;
        //1.Constructor[] getConstructors() 获取所有的public修饰的构造方法
        Constructor<?>[] cons = cls1.getConstructors();
        for (Constructor<?> con : cons) {
            System.out.println("con = " + con);//只能获取所有的public的构造方法
        }


        //2.Constructor getConstructor(Class... parameterTypes)
        Class<? extends User> cls2 = new User().getClass();
        Constructor<? extends User> consString = cls2.getConstructor(int.class,String.class);
        //参数亦必须时Class对象，基本数据类型+.class即可获取对应的对象

        Constructor<? extends User> consString2 = cls2.getConstructor();
        //若是无参构造则没有必要写参数

        System.out.println("consString = " + consString);
        //根据参数类型获取构造方法对象，只能获得public修饰的构造方法。
        // 如果不存在对应的构造方法，则会抛出 java.lang.NoSuchMethodException 异常。
    }
    @Test
    public void test2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
       //Constructor类中常用方法

        Class<User> cls1 = User.class;
        //1. T newInstance(Object... initargs)   根据指定参数创建对象。
        Constructor<User> cons = cls1.getConstructor(String.class);
        User user1 = cons.newInstance("小明");
        System.out.println("构造方法锁所创建的对象名字叫："+user1.getName());

        //Class<? extends User> cls2 = new User().getClass();
        Constructor<? extends User> cons2 =new User().getClass().getConstructor(int.class, String.class);
        User user2 = cons2.newInstance(8, "小红");
        System.out.println("第二位小朋友的信息为： " + user2);
        //2. T newInstance()  空参构造方法创建对象。
        Class<?> cls3 = Class.forName("com.butchy.Demo03获取类中的构造器.User");

        Constructor<?> cons3 = cls3.getConstructor();
        Object user3 = cons3.newInstance();
        System.out.println("空参构造的小朋友： " + user3);


    }


}
