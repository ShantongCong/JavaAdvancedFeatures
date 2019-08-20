package com.butchy.网络编程.HomeWork.TCP网络程序练习;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//需求说明：创建新项目，按以下要求编写代码：
//在项目下创建TCP 服务器端 端口号为8888
//1:等待客户端连接   如果有客户端连接  获取到客户端对象
//2:获取到客户端对象之后 当前在服务器读取数据客户端传送数据
public class Server {
    public static void main(String[] args) throws IOException {
        //创建服务器的SeverSocket对象。
        ServerSocket serverSocket = new ServerSocket(8888);//使用try/catch还是throws
        System.out.println("服务器开启，等待客户端连接！");
        //调用accept方法,服务客户端。
        while (true) {
            Socket socket = serverSocket.accept();//接受方法放在哪儿？
            new Thread(() -> {
                try {
                    //客户端已连接
                    //读取客户端传来的消息
                    InputStream netIn = socket.getInputStream();
                    byte[] bytes = new byte[1024];
                    int len;
                    while (((len = netIn.read(bytes)) != -1)) {
                        System.out.println("客户端："+new String(bytes, 0, len));
                    }
                    //回应客户端，已经收到
                    OutputStream netOut = socket.getOutputStream();
                    netOut.write("上传成功！你的消息我已经收到！".getBytes());
                    socket.shutdownOutput();

                    netOut.close();
                    netIn.close();
                    socket.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //关流
                //netIn不需要关流吗？ 肯定要

            }).start();

            //serverSocket.close();


        }
    }
}
