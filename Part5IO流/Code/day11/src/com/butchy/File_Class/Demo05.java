package com.butchy.File_Class;

public class Demo05 {
    //斐波那契
    public static void main(String[] args) {

        //1 1 2 3 5 8 13
        //因为需要一个月成长，所有第N个月的兔子=老兔子+新兔子（刚具有甚于能力）
        System.out.println(fb(24));

    }
    public static  int fb(int n){
        if (n==0||n==1)  return 1;
        return fb(n-1)+fb(n-2);
    }
}
