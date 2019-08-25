package com.butchy.File_Class.Demo01File初体验.Demo02_map方法;

import java.util.function.Function;
import java.util.stream.Stream;

public class Tes01 {
    public static void main(String[] args) {
        Stream.of("洪波:20", "加蓬:17").map(new Function<String, Student>() {
            @Override
            public Student apply(String s) {
                return new Student(s.split(":")[0], Integer.valueOf(s.split(":")[1]));
            }
        }).forEach(student -> System.out.println("student = " + student));
        //System.out.println("string = " + string);

        Stream.of("洪波:20", "加蓬:17").map(s -> new Student(s.split(":")[0], Integer.valueOf(s.split(":")[1]))).forEach(student -> System.out.println("student1 = " + student));

    }
}
