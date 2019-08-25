package com.butchy.字节流和字符流.Demo02字符流;

import com.butchy.字节流和字符流.CloseUntil;

import java.io.FileWriter;
import java.io.IOException;

public class Test02 {
    public static void main(String[] args) throws IOException {
        //字符输出流
        String str="helloworld";
       FileWriter writer = new FileWriter("demo01.txt",true);
       writer.write(System.lineSeparator());
       writer.write("你就是一个傻逼！");
       writer.write(System.lineSeparator());
       writer.write(str,5,5);
        CloseUntil.close(writer);


    }
}
