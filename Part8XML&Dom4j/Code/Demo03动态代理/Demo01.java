package com.butchy.Demo03动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Demo01 {
    public static void main(String[] args) {
       SuperStar star=new SuperStar("宝强");
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class<?>[] interfaces = star.getClass().getInterfaces();

        Star client = (Star)Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                double money = (double) args[0];
                System.out.println("出场费为：" + money+"元");
                if ("show".equals(methodName)) {
                    if (money < 200000) {
                        System.out.println("太少了！不show!");
                        return null;
                    } else {
                        System.out.println("经纪人抽了" + money * 0.2);
                        return method.invoke(star, money * 0.8);
                    }
                }
                return null;

            }
        });
        client.show(200000);
        System.out.println("======分割线=======");
        client.show(200);

    }

}
