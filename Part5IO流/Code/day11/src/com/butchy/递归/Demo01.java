package com.butchy.递归;

public class Demo01 {
    public static void main(String[] args) {
        int add = add(5);
        System.out.println("add = " + add);

    }
    public static int add(int a){
        if (a==1) return 1;
        return a+add(a-1);
    }
}
