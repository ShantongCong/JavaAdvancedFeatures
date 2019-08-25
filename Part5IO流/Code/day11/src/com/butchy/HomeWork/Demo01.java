package com.butchy.HomeWork;

import java.io.File;
import java.io.IOException;

public class Demo01 {
    public static void main(String[] args) throws IOException {
        //练习一:相对路径和绝对路径的使用
        File file1=new File("练习1.txt");
        System.out.println(file1.getAbsolutePath());//D:\JavaCode\readyWork\day11\练习1.txt
        System.out.println(file1.createNewFile());
        //描述:创建两个文件对象，分别使用相对路径和绝对路径创建。

        File file2=new File("D:\\JavaCode\\readyWork\\day11\\练习2.txt");
        System.out.println(file2.createNewFile());
        File file3=new File("D:\\JavaCode\\readyWork\\day11\\练习文件夹");
        System.out.println(file3.mkdirs());

    }
}
