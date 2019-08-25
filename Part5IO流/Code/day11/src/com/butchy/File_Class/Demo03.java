package com.butchy.File_Class;

import java.io.File;

public class Demo03 {
    static int count=0;
    public static void main(String[] args) {
        File file02=new File("D:\\JavaCode");
        read(file02);
        System.out.println("count = " + count);
        System.out.println(count/21);//平均每天写14个程序

    }
    public static void read(File file){
        if (file.exists()){
        for (File file1 : file.listFiles()) {
            if (file1.isDirectory()){
                read(file1);
            }else if (file1.getName().toLowerCase().endsWith(".java")){
                System.out.println(file1);
                count++;//写了三百多个java程序
                //来了
            }
            //System.out.println(file1);
        }
        }
        else System.out.println("文件不存在！");
    }
}
