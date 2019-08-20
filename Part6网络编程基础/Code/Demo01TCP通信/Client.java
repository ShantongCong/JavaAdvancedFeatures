package com.butchy.网络编程.Demo01TCP通信;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        //1.建立socket对象，确定IP和端口
        Socket sc = new Socket("192.168.71.69", 8888);
        System.out.println("连接服务器成功.....");
        //2.创建信息输入流,并发送
        OutputStream outputStream = sc.getOutputStream();
        outputStream.write("嗨！服务器nmsl!".getBytes());
        //3.发送完成标志
        sc.shutdownOutput();
        //4.接受服务器发来的信息
        long l = System.currentTimeMillis();
        FileOutputStream fos=new FileOutputStream("movie/电棍33"+l+".itheima");
        InputStream inputStream = sc.getInputStream();
        byte[] bytes=new byte[1024*1024*40];
        int len;

        long l1 = System.currentTimeMillis();
        while ((len=inputStream.read(bytes))!=-1){
            fos.write(bytes,0,len);
            //String str=new String(bytes,0,len);
        }
        long l2 = System.currentTimeMillis();

        System.out.println("从服务器下载视频完毕！");
        double speed=38000.0/(l2-l1);
        System.out.println("下载速度为：" + speed);
        //5.关闭流
        fos.close();
        sc.close();

    }

}
