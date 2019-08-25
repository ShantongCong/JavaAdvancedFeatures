package com.butchy.File_Class.Demo01File初体验;

import java.io.File;

public class Demo02 {
    public static void main(String[] args) {
//        - public String getAbsolutePath() ：返回此File的绝对路径名字符串。【从盘符开始的路径】
        File file1=new File("D:/abc/数学之美.PDF");
        System.out.println(file1.getAbsoluteFile());//D:\abc\数学之美.PDF

//        - public String getPath() ：将此File转换为路径名字符串。【获取构造路径】
        System.out.println(file1.getParent());//返回的是路径字符串//D:\abc

//        - public String getName()  ：返回由此File表示的文件或目录的名称。
        System.out.println(file1.getName());//数学之美.PDF

//        - public long length()  ：返回由此File表示的文件的长度。【文件的大小，单位是字节】
        System.out.println(file1.length()/1024);//50273
//【如果是实际存在的文件返回才有意义】
//        如果是文件夹或者是不存在的文件，会返回0或其他数据，没有实际作用
    }
}
