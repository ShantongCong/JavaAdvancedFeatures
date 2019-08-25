package com.butchy.字符缓冲流;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Test01 {
    public static void main(String[] args) {
        //1. 使用流对象逐行读取原文本信息,把读取的信息保存到集合中
        ArrayList<String> list = new ArrayList<>();


        try (
                BufferedReader br = new BufferedReader(new FileReader("出师表.txt"))
        ) {

            String str;
            while ((str = br.readLine()) != null) {//null和-1的区别
                //readline(里面是什么？)
                list.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //2. 使用Collections集合工具类中的方法sort,对集合中的元素按照自定义规则排序
        Collections.sort(list, Comparator.comparingInt(o -> Integer.valueOf(o.split("\\.")[0])));

        //3. 遍历集合,把集合中排序后的文本在写入到新的记事本中
        try (
        BufferedWriter bw=new BufferedWriter(new FileWriter("新出师表.txt"))
       ){
            for (String s : list) {
            bw.write(s);
            bw.newLine();
            }

        }catch (IOException e){
            e.printStackTrace();
        }




    }
}
