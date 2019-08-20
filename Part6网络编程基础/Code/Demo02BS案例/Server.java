package com.butchy.网络编程.Demo02BS案例;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        //1.创建ServerSocket 对象，设置端口号
        //2.调用accept方法，服务浏览器
        //3.根据浏览器的请求，识别浏览器要访问的路径
        //4.把硬盘中的数据读出来，写入到socket的输出流中
        //5.关流

        //优化：1.定义while循环，使得服务器能承受多次方法
        //2.创建多线程匿名对象，支持多个浏览器同时访问和下载页面
        //3.有异常要及时抛出或者捕获

        //http协议
        //字节流转化为字符流，要封装

        ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println(" 服务器已开启！ ");
        while (true) {
            Socket socket = serverSocket.accept();
            //System.out.println("浏览器连接成功！");

            new Thread(()->{
                try {

            String path = getPath(socket);

            FileInputStream fis = new FileInputStream(path);
            OutputStream outNet = socket.getOutputStream();

            outNet.write("HTTP/1.1 200 OK\r\n".getBytes());
            outNet.write("Content-Type:text/html\r\n".getBytes());
            outNet.write("\r\n".getBytes());

            int len;
            byte[] bytes = new byte[1024];
            while (((len = fis.read(bytes)) != -1)) {
                outNet.write(bytes, 0, len);
            }


                }catch (Exception e)
                {
                    System.out.println("连接出现异常");
                    e.printStackTrace();
                }finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }).start();

        }
    }

    public static String getPath(Socket socket) throws IOException {
        //GET /web/index.html HTTP/1.1
        InputStream inputStream = socket.getInputStream();//字节流转变为字符流
        InputStreamReader isr=new InputStreamReader(inputStream);
        BufferedReader br=new BufferedReader(isr);
        String line = br.readLine();

        String substring = line.split(" ")[1].substring(1);
        return substring;
    }
}
