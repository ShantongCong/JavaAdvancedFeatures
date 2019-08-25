package com.butchy.HomeWork;

import java.io.File;
import java.io.IOException;

public class Demo04 {
    static long count=0;
    public static void main(String[] args) throws IOException {

    //练习六:获取文件信息:文件名,文件大小,文件的绝对路径,文件的父路径
    //获取D盘aaa文件夹中b.txt文件的文件名，文件大小，文件的绝对路径和父路径等信息，并将信息输出在控制台。
        //打印所有的txt的文件信息

        getTxt(new File("D:/"));



    }
    public static void print(File file) throws IOException {

        System.out.println("============");
        System.out.println("文件名："+file.getName());
        System.out.println("大小："+file.length()+"kb");
        System.out.println("绝对路径："+file.getAbsolutePath());
        System.out.println("父路径："+file.getParentFile());
        count++;
        System.out.println("count = " + count);
        System.out.println("=============");

    }

    public static void getTxt(File file) throws IOException {


//        if (file.exists()){
//            for (File file1 : file.listFiles()) {
//                if (file1.isDirectory()){
//                    getTxt(file1);
//                }else if (file1.getName().toLowerCase().endsWith(".java")){
//                    print(file1);
//                }
//                //System.out.println(file1);
//            }
//        }
        if (file.exists()) {

        if (file.isFile()) {
            if (file.getName().toLowerCase().endsWith(".dll"))
                print(file);
        }else if (file.isDirectory()){
            for (File file1 : file.listFiles()) {
                getTxt(file1);
            }
        }
        }


        //遍历txt文档
        //1.判断是否为文件，如果是 判断是否以。txt结尾
        //2.如果是文件夹则遍历，

    }

}
