package com.butchy.Demo02静态代理;

public class Test1 {
    public static void main(String[] args) {
        SuperStar superStar = new SuperStar("宝强");
        Client client = new Client("宋哲",superStar);

        client.show(200000);
        System.out.println("========");
        client.show(200);
    }
}
