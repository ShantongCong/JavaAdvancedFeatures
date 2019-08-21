package com.butchy.Demo01Junit测试;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Test01 {

//访问权限只能是public 无参 ，无返回值

    @Test
    public void show() {//为啥必须要使用Public
        System.out.println("这是为了测试Junit的方法aaaa");
    }

    @Test
    public void show3() {//为啥必须要使用Public
        System.out.println("这是为了测试Junit的方法BBBB");
    }
    @Test
    public void show4(){
        System.out.println("孙笑川尼玛死了这是可以肯定 的");
    }


    @Before
    public void show1() {//为啥必须要使用Public
        System.out.println("这是为了测试Junit的方法before");
    }

    @After
    public void show2() {//为啥必须要使用Public
        System.out.println("这是为了测试Junit的方法after");
    }
}
