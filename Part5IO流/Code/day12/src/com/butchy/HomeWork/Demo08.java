package com.butchy.HomeWork;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Demo08 {
    public static void main(String[] args) {

        Properties ps=new Properties();
        ps.setProperty("zhangsan","90");
        ps.setProperty("lisi","80");
        ps.setProperty("wangwu","85");

        //把配置信息存储进去
        try {
            ps.store(new FileOutputStream("Properties.properties"),"this is a comment");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Properties psOut=new Properties();

        try {
            psOut.load(new FileInputStream("Properties.properties"));
            if (psOut.containsKey("lisi")) {
                //String lisi = psOut.getProperty("lisi");
                psOut.setProperty("lisi","100");
            psOut.store(new FileOutputStream("newProperties.properties"),"this is new!");
            }



        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
