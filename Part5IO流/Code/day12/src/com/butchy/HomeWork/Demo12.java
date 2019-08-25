package com.butchy.HomeWork;

import com.butchy.序列化.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Demo12 {
    public static void main(String[] args) {
        ArrayList<Student> list=new ArrayList<>();

        Scanner sc=new Scanner(System.in);
        System.out.println("学生属性之间只用逗号隔开！");
        for (int i = 0; i < 3; i++) {
            System.out.println("请输入第"+(i+1)+"的学生信息");
            String s = sc.nextLine();
            String[] sp = s.split("\\.");
            list.add(new Student(sp[0],Integer.valueOf(sp[1]),sp[2]));
        }

        //System.out.println(list);
        //把对象写入硬盘中去
        try(
                ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("stuDemo12.obj"))
                ){
            oos.writeObject(list);
            System.out.println("序列化成功！");
        }catch (IOException e)
        {
            e.printStackTrace();
        }



        //list.add(new Student())
    }
}
