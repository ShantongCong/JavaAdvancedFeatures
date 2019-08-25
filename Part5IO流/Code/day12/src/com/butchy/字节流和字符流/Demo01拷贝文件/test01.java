package com.butchy.字节流和字符流.Demo01拷贝文件;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;

public class test01 {
    public static void main(String[] args) throws IOException {
        long l = System.currentTimeMillis();
        //01知识巩固_上海黑马JavaEE就业86期（20190731面授）.itheima
        File file1=new File("E:\\GameDownload\\就业班上课视频\\day11\\01_视频\\01知识巩固_上海黑马JavaEE就业86期（20190731面授）.itheima");
        long length = file1.length();
        FileOutputStream fos=new FileOutputStream("E:\\电棍4.itheima");
        FileInputStream fis=new FileInputStream(file1);

        //读
        byte[] bytes=new byte[(int) length];
        while (fis.read(bytes)!=-1){
            fos.write(bytes);
        }//电棍304毫秒
//        int b;
//        while ((b=fis.read())!=-1){
//            fos.write(b);
//        }



        fis.close();
        fos.close();
        long s=System.currentTimeMillis();
        System.out.println("拷贝花了："+(s-l));




    }
}
