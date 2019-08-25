package com.butchy.File_Class.Demo01File初体验.Demo02_map方法;

public class Student {
  private String name;
  private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
