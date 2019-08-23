package com.butchy.Demo01ClassLoad;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Demo01 {
    @Test
    public void test1() throws ClassNotFoundException, NoSuchMethodException
            , IllegalAccessException, InvocationTargetException
            , InstantiationException {
        //public ClassLoader getClassLoader() : 返回系统类加载器
        //例如：ClassLoader classLoader = Student.class.getClassLoader();

        ClassLoader classLoader = Student.class.getClassLoader();
        Class<?> student = classLoader.loadClass("com.butchy.Demo01ClassLoad.Student");
        Student stu = (Student) student.getConstructor(String.class, int.class)
                .newInstance("迪丽热巴", 18);
        System.out.println(stu);


    }

    @Test
    public void test2() throws ClassNotFoundException, NoSuchMethodException
            , IllegalAccessException, InvocationTargetException
            , InstantiationException {
        //public static ClassLoader getSystemClassLoader() ： 返回系统类加载器
        //例如: ClassLoader loader = ClassLoader.getSystemClassLoader();

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class<?> classStudent = classLoader.loadClass("com.butchy.Demo01ClassLoad.Student");
        Constructor<?> constructor = classStudent.getConstructor(String.class, int.class);
        Student fiona = (Student) constructor.newInstance("Fiona", 18);
        System.out.println(fiona);


    }

}
