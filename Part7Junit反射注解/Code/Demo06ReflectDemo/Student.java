package com.butchy.Demo06ReflectDemo;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Properties;

public class Student {
    public void sleep() {
        System.out.println("这个学生在睡觉！");
    }

    public void eat(String food){
        System.out.println("这个学生在吃"+food);
    }

    public void mlove(String gf,String place){
        System.out.println("这个学生和"+gf+"买可乐"+"在"+place);
    }

    @Test
    public void test() throws IOException {
        //得到类名和方法名
        String className = Student.class.getName();
        //String name;
        Method[] methods = Student.class.getDeclaredMethods();
        System.out.println(methods.length);//两个方法

        Properties pr = new Properties();
        //把className和methodName放入配置文件
        pr.put("className", className);

        for (int i = 0; i < methods.length; i++) {
            StringBuilder method = new StringBuilder(methods[i].getName());
           // method.append(methods[i].getName());
            for (Class<?> para : methods[i].getParameterTypes()) {
                method.append(",");
                method.append(para.getName());
//
            }
            //System.out.println(Arrays.toString(methods[i].getParameterTypes()));
            pr.put("methodName"+i,method.toString());
        }

        // 创建文件输出流，把配置文件写出
        OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream("pro.properties"));
        pr.store(osw,"this is a commit");



    }
}
