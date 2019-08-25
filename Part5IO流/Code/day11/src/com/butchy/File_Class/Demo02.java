package com.butchy.File_Class;

import java.io.File;
import java.io.IOException;

public class Demo02 {
    public static void main(String[] args) throws IOException {

//      ==如果创建文件时，无法找到实际的路径，报错==
//      - public boolean createNewFile() ：当且仅当具有该名称的文件尚不存在时，创建一个新的空文件。
        File file1 = new File("file01.txt");
        System.out.println(file1.createNewFile());//如果已经创建好，则不会再次创建文件，返回的为flase

        //不能使用creat创建文件夹
//        File file2 = new File("file02/file0201");
//        System.out.println(file2.createNewFile());
//      - public boolean delete() ：删除由此File表示的文件或目录。
//      ==永久删除，如果是文件夹，那么要求文件夹内容为空，才能删除==

//      - public boolean mkdir() ：创建由此File表示的目录。
        File file3=new File("file02");//只能创建单级目录
        System.out.println(file3.mkdir());
//       ==只能创建单层目录==
//      - public boolean mkdirs() ：创建由此File表示的目录，包括任何必需但不存在的父目录。
//  ==可以创建多级目录，如果父目录不存在，会自动创建=
        File file4=new File("file03/file0301");
        System.out.println(file4.getAbsolutePath());
        System.out.println(file4.getAbsoluteFile());
        System.out.println(file4.mkdirs());

        File file5=new File("file03/01");
        System.out.println(file4.isDirectory());//是一个目录
        System.out.println(file4.isFile());//不是一个文件



        //System.out.println(file5.exists());


    }
}
