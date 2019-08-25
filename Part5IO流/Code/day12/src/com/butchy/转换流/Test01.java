package com.butchy.转换流;

import java.io.FileReader;
import java.io.IOException;

public class Test01 {
    //把GBK读入为UTF-8，并写入
    public static void main(String[] args) {
        try (
                FileReader fr = new FileReader("网恋.txt")
        ) {
            //char[] chars=new char[1024];
            int len;
            while((len=fr.read())!=-1){
                System.out.println((char) len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //写出
}
