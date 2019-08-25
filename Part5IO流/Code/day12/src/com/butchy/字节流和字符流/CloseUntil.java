package com.butchy.字节流和字符流;

public class CloseUntil {
    private CloseUntil() {
    }
    //关流工具类
    public static void close(AutoCloseable...ac){
        for (AutoCloseable autoCloseable : ac) {
            if (autoCloseable!=null){
                try {
                    autoCloseable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }


    }



}
