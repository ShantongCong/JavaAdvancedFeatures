package com.butchy.Demo02静态代理;

public class Client implements Star {
    String name;
    SuperStar star;

    public Client(String name, SuperStar star) {
        this.name = name;
        this.star = star;
    }

    //代理模式
    //实现了相同的接口，实现了相同的抽象方法，本质上是对参数的一种过滤
    @Override
    public void show(double money) {
        if (money < 20000) {
            System.out.println("钱太少了");
        } else {
            System.out.println(name+"赚了"+money*0.5);
            star.show(money*0.5);
        }
    }
}
