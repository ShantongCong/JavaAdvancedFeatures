package com.butchy.字节流和字符流.HomeWork;

import java.io.*;

public class Demo01 {
    public static void main(String[] args)  {
        File file=new File("E:/电棍2.itheima");
        try (
        FileInputStream fis=new FileInputStream(file);
        FileOutputStream fos=new FileOutputStream("E:/电棍5.itheima");
                ){
        long len=file.length();
        byte[] bytes=new byte[(int) len];
        while ((fis.read(bytes))!=-1) {
            fos.write(bytes);
        }
        }
         catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }







    }
}
