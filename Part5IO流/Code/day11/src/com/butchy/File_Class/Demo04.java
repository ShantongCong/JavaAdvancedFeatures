package com.butchy.File_Class;

import java.io.File;

//删除所有文件
public class Demo04 {
    public static void main(String[] args) {
        File file=new File("D:/abc1.rar");
        delete(file);
    }
    public static void delete(File file){//fen
        if (file.isFile())
            file.delete();
        else if (file.isDirectory()){
            for (File file1 : file.listFiles()) {
                //分为三种情况要罗列好
                //1.如果是文件直接删除
                //2.如果是文件夹，且不为空，则遍历一下并递归
                //3.空文件夹直接删除
                delete(file1);
            }
            file.delete();
        }

    }

}
