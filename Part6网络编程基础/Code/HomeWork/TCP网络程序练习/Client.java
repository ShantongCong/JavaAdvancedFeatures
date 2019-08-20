package com.butchy.网络编程.HomeWork.TCP网络程序练习;

import java.io.*;
import java.net.Socket;

//需求说明：创建新项目，按以下要求编写代码：
//在项目下创建TCP 客户端
//访问之前创建的服务器端,服务器端ip127.0.0.1 端口号8888
//1:客户端连接服务器,并发送 hello.服务器,我是客户端.
//2:开启上一题服务器,等待客户端连接,客户端连接并发送数据
public class Client {
    public static void main(String[] args) throws IOException{
        //1.创立Socket对象
        //192.168.71.690
        Socket socket=new Socket("192.168.71.690",8888);

        //创建输出流，
        OutputStream netOut = socket.getOutputStream();
        FileInputStream fis1=new FileInputStream("movie/电棍.png");
        byte[] bytes=new byte[1024];
        int len;
        while (((len = fis1.read()) != -1)) {
            netOut.write(bytes,0,len);
        }

//        netOut.write("hello!服务器，我是客户端".getBytes());
//        //告诉服务器传输完成，不必再进行监听客户端
        socket.shutdownOutput();
//
//        InputStream netIn = socket.getInputStream();
//        byte[] bytes=new byte[1024];
//        int len;
//
//        while (((len = netIn.read(bytes)) != -1)) {
//            System.out.println("服务器："+new String(bytes,0,len));
//        }

        //关流
        //netIn.close();
        netOut.close();
        socket.close();

    }
}
