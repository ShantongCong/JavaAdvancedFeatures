package com.butchy.HomeWork;

import java.io.File;
import java.io.IOException;

public class Demo02 {
    public static void main(String[] args) throws IOException {
        //练习二:检查文件是否存在,文件的创建
        //描述:检查D盘下是否存在文件a.txt,如果不存在则创建该文件。
        File file4=new File("d:/a.txt");
        //如果未找到盘符怎样提示？
        if (file4.exists()) {
            System.out.println("文件已存在");
        }
        else {
            file4.createNewFile();
            System.out.println("文件创建完毕");
        }

    }
}
