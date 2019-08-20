package com.butchy.网络编程.Demo01TCP通信;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {


        //1.创建ServerSocket对象，绑定端口号
        ServerSocket serversocket = new ServerSocket(8888);
        System.out.println("服务器已开启！");

        while (true) {


            //2.调用socket对象的accept方法，服务客户端
            Socket socket = serversocket.accept();//阻塞 等待客户端连接
            System.out.println("客户端连接成功！");

            new Thread(
                    () -> {
                        try {

                            File file1 = new File("E:/视频.itheima");
                            long length = file1.length();
                            System.out.println("准备传输文件的大小为：" + length);
                            //3.接受从客户端传来的信息
                            InputStream inputStream = socket.getInputStream();
                            byte[] bytes = new byte[1024];
                            int len;
                            while ((len = inputStream.read(bytes)) != -1) {
                                String str = new String(bytes, 0, len);
                                System.out.println("从客户端接受到的信息为：" + str);
                            }

                            //4.回复客户端的消息

                            OutputStream outNet = socket.getOutputStream();


                            FileInputStream fis = new FileInputStream(file1);//建


                            byte[] Vbytes = new byte[1024];
                            int len1;

                            while (((len1 = fis.read(Vbytes)) != -1)) {
                                outNet.write(Vbytes, 0, len1);
                            }
                            fis.close();//关闭输入流
                            System.out.println("文件已发送！请注意查收！");

                            //5.告诉客户端信息传输完毕！
                            socket.shutdownOutput();
                            //关闭服务器
                            socket.close();
                        } catch (Exception e) {
                            System.out.println("下载出现异常");
                        }


                    }
            ).start();
        }
    }
}
