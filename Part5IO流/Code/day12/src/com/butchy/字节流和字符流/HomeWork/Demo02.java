package com.butchy.字节流和字符流.HomeWork;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Demo02 {
    public static void main(String[] args) throws IOException {
        File file=new File("网恋.txt");
        System.out.println(file.createNewFile());

        FileOutputStream fos=new FileOutputStream(file,true);

        Scanner sc=new Scanner(System.in);
        while (true){
            String s = sc.nextLine();
            byte[] bytes = s.getBytes();
            fos.write(bytes);
            fos.write("\r\n".getBytes());
            fos.flush();
            if (s.equals("886"))break;//字符串相等要使用equals
        }
        fos.close();




    }
}
