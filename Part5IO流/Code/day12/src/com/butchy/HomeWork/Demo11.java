package com.butchy.HomeWork;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Demo11 {
    public static void main(String[] args) {
        //分析
        //先获取录入信息，
        while (true) {
            System.out.println("请输入你想要写入的信息！");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            if (s.equals("end"))
                break;

            try (
                    FileWriter fw = new FileWriter("stu.txt",true)//追加信息后面要加true
            ) {
                fw.write(s);
                fw.write(System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 检测手机否为end
            // 存储写入，记得换行


        }
        System.out.println("输入完成，已写入硬盘");
    }
}
