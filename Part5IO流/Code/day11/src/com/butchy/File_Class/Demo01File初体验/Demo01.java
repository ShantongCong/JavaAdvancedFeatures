package com.butchy.File_Class.Demo01File初体验;

import java.io.File;
/**
 * public File(String pathname) ：通过将给定的路径名字符串转换为抽象路径名来创建新的 File实例。
 - public File(String parent, String child) ：从父路径名字符串和子路径名字符串创建新的 File实例。
 - public File(File parent, String child) ：从父抽象路径名和子路径名字符串创建新的 File实例。*/
public class Demo01 {
    public static void main(String[] args) {
        //1.通过地址直接创建
        String pathname = "D:\\aaa.txt";
        File file1 = new File(pathname);

        //通过父路径名字符串和子路径名字符串再次创建file对象
        File filep=new File("D:\\bbb","n/cc.txt");
        File fileFather=new File("D:/bbb");
        System.out.println("filep = " + filep);

        //通过父类对象和子类路径一起创建
        File file3=new File(fileFather,"n/ccc3.txt");
        System.out.println(file3);



    }
}
