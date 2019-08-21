package com.butchy.Demo04获取类中的方法;

public class User {
   private int age;
   private String name;
   //成员方法不同权限演示
    public void print1(){
        System.out.println("public");
    }
    public void print1(String name){
        System.out.println(name+"public");
    }

     void print2(){
        System.out.println("default");
    }

    protected void print3(){
        System.out.println(" protected ");
    }

    private void print4(){
        System.out.println("private");
    }



    public User() {
    }
    public User(String name){
        this.name=name;
    }
    private User(int age){
        this.age=age;
    }


    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
