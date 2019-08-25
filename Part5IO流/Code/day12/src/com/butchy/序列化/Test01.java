package com.butchy.序列化;

import java.io.*;

public class Test01 {
    public static void main(String[] args)  {
        File file1=new File("stu.obj");
        try(
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(file1));
        ObjectInputStream  ois=new ObjectInputStream(new FileInputStream(file1))
                ){
            //序列化对象
//            oos.writeObject(new Student("迪丽热巴",18,"female"));
//            System.out.println("序列化成功！");

            Object o = ois.readObject();
            System.out.println((Student) o);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException c){
            c.printStackTrace();
        }
    }
}
