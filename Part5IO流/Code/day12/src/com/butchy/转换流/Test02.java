package com.butchy.转换流;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test02 {
    public static void main(String[] args) {

        try (
                //把文本文档中的GBK转化为UTF-8
                InputStreamReader isr = new InputStreamReader(new FileInputStream("FileGBK.txt"), "GBK");
        ) {

            int read;
            char [] chars=new char[1024];
            while ((read = isr.read(chars))!=-1)
            System.out.println(new String(chars,0,read));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
