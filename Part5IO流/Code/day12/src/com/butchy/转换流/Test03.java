package com.butchy.转换流;

import java.io.*;

public class Test03 {
    public static void main(String[] args) {
        try (
                InputStreamReader isr=new InputStreamReader(new FileInputStream("FileGBK.txt"),"GBK");
                OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("utf.txt"), "UTF-8");
        ) {
            int b;
            while ((b=isr.read())!=-1){
                osw.write(b);
            }
            System.out.println("转换完成!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
