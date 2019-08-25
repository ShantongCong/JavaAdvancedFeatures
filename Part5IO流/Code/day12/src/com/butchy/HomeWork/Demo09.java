package com.butchy.HomeWork;

import java.io.FileWriter;
import java.io.IOException;

public class Demo09 {
    public static void main(String[] args) {
        try(
        FileWriter fw=new FileWriter("test.txt")
                ){
            fw.write("asdacasjcnjaca12dsgcongshantongfDgzhhfbcvb533ghfgfhweasdca4saddasdadcasx");
            System.out.println("写入成功！");
        }catch (IOException e){
            e.printStackTrace();
        }



    }
}
