package com.butchy.Demo03动态代理;

public class SuperStar implements Star {
    String name;

    public SuperStar(String name) {
        this.name = name;
    }

    @Override
    public void show(double money) {
        System.out.println(name+"参加了奔跑吧兄弟，赚了"+money);
    }
}
