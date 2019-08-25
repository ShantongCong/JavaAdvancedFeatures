package com.butchy.IO流;

import java.io.FileInputStream;
import java.io.IOException;

public class Demo02 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis=new FileInputStream("a.txt");
        byte[] bytes=new byte[100];
        //int read = fis.read(bytes);
        int len;
        while ((len=fis.read(bytes))!=-1){
            //因为read返回的是读取的字节数，所以就是字节数组的有效长度
            //如果未-1则没有读取到文件内容
            String str = new String(bytes,0,len);
            System.out.println("str = " + str);
        }

//        String str=new String(bytes,0,3);
//        System.out.println(str);
        fis.close();
    }
}
