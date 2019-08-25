package com.butchy.HomeWork;

import java.io.File;

public class Demo03 {
    public static void main(String[] args) {
        //描述:在D盘下创建一个名为ccc的文件夹，要求如下：
        //1.ccc文件夹中要求包含bbb子文件夹
        //2.bbb子文件夹要求包含aaa文件夹
        File file1=new File("D:/ccc/bbb/aaa");
        File file2=new File("D:/ccc/bbb/ddd");

        file1.mkdirs();
        file2.mkdirs();
        delect(new File("D:/ccc"));


    }
    public static void delect(File file){
        //分为三种情况
        //单个文件，直接删除
        //非空文件夹，进入递归
        //空文件夹直接删除
        if (file.exists()){
            if (file.isFile()) {
                file.delete();//删除文件
            }else if (file.isDirectory()){
                for (File file1 : file.listFiles()) {
                    delect(file1);//递归
                }
                file.delete();//删除空文件夹
            }
        }
    }
}
