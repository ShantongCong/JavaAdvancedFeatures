package com.butchy.HomeWork;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Demo10 {
    public static void main(String[] args) {
        try (
            FileInputStream fis=new FileInputStream("test.txt")
        ) {
            int b;
            int count=0;
            while ((b=fis.read())!=-1)
            {
                if ((char)b=='a')
                {count++;
                System.out.println("count = " + count);}
                if (count==10){
                    System.out.println("a出现了十次！");

                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
