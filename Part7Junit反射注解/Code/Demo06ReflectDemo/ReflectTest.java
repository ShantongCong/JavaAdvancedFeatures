package com.butchy.Demo06ReflectDemo;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

/**
 * 需求：写一个"框架"，不能改变该类的任何代码的前提下，可以帮我们创建任意类的对象，并且执行其中任意方法
 * 实现：
 * 1. 配置文件
 * 2. 反射
 * 步骤：
 * 1. 将需要创建的对象的全类名和需要执行的方法定义在配置文件中
 * 2. 在程序中加载读取配置文件
 * 3. 使用反射技术来加载类文件进内存
 * 4. 创建对象
 * 5. 执行方法
 */
public class ReflectTest {
    @Test
    public void test1() throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //把配置文件加载进内存
        Properties pr = new Properties();
        pr.load(new FileInputStream("pro.properties"));

        //读取类名和方法名
        String className = pr.getProperty("className");

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(pr.getProperty("methodName" + i));
        }
        for (String s : list) {
            System.out.println("method = " + s);
        }

        System.out.println("className = " + className);
        System.out.println("类名和方法名加载成功！");

        //通过类名获取Class对象
        Class<?> class1 = Class.forName(className);

        //获得该类的对象用于调用方法
        Object obj = class1.getConstructor().newInstance();

        Method method = class1.getMethod(list.get(1));
        method.invoke(obj);


    }

    //拓展反思
    //1.如果类中有多个方法呢？如何判断自己想要的方法？
    //2.怎样知道自己如何调用的方法是否由参数？





}
