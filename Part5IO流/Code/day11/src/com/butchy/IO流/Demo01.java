package com.butchy.IO流;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Demo01 {
    public static void main(String[] args) throws IOException {
        //创
        FileOutputStream fos=new FileOutputStream("outPut01",true);
        //写
        //fos.write("nmsl".getBytes());
        //fos.write("nmslnmsl4".getBytes(),4,4);
        fos.write(65);
        //关
        fos.close();
    }
}
