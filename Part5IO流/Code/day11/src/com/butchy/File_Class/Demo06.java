package com.butchy.File_Class;

public class Demo06 {
    //猴子吃桃子
    public static void main(String[] args) {
        System.out.println(taozi(10));


    }
    public static int taozi(int day){
        if (day==1)
            return 1;
        return (taozi(day-1)+1)*2;

    }
}
