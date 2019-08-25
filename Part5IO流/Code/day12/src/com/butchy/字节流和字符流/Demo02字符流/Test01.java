package com.butchy.字节流和字符流.Demo02字符流;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Test01 {
    public static void main(String[] args) throws IOException {
        File file1=new File("demo01.txt");
        System.out.println(file1.createNewFile());//创建一个文件

        FileOutputStream fos=new FileOutputStream(file1);//创
        fos.write("这是一个天才创建的文本".getBytes());

        FileReader fr=new FileReader("demo01.txt");
        char[] chars=new char[3];
        int lenth;
        while (((lenth=fr.read(chars) )!= -1)) {
        String string=new String(chars,0,lenth);
            System.out.println("string = " + string);

        }
        fr.close();


    }
}
