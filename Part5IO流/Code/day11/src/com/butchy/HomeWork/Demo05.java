package com.butchy.HomeWork;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo05 {
    public static void main(String[] args) throws IOException {
        //练习一:字节输出流写出字节数据
        //描述:利用字节输出流一次写一个字节的方式，向D盘的a.txt文件输出字符‘a’。
        //3步骤
        //1.创
        FileOutputStream fos=new FileOutputStream("a.txt");
        //2.写
//        fos.write('a');
//        fos.write("i love java".getBytes());


        fos.write("床前明月光，".getBytes());
        String s = System.lineSeparator();
        fos.write(s.getBytes());
        fos.write("疑是地上霜。".getBytes());
        fos.write(s.getBytes());
        fos.write("抬头望明月，".getBytes());
        fos.write(s.getBytes());
        fos.write("低头思故乡。".getBytes());
        fos.write("\r\n".getBytes());

        //fos.write("");
        //3.关
        fos.close();
        //描述:利用字节输出流一次写一个字节数组的方式向D盘的b.txt文件输出内容:"i love java"。



    }
}
