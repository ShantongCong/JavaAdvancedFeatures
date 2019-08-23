package com.cong.code.Demo22;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 商城的入口
 */
public class StoreEntry {

    // 文件的存储路径
    private static final String GOODS_INFO_FILE_PATH =  "goods.txt";

    public static void main(String[] args) {

        // 创建用户集合的对象
        ArrayList<User> users = new ArrayList<>();

        while (true) {

            // 商城用户注册和登录界面
            System.out.println("--------------------------欢迎访问ITHEIMA购物商城-------------------------------");
            System.out.println("1. 登录");
            System.out.println("2. 注册");
            System.out.println("3. 退出");

            // 请输入您的选择
            System.out.println();
            System.out.println("请输入您的选择：");
            Scanner sc = new Scanner(System.in);
            String next = sc.next();

            // 选择
            switch (next) {
                case "1":
                    // TODO
                    boolean result = login(users);
                    //
                    if (result) {
//                        System.out.println("登录成功....");
                        // 商品管理
                        goodsManager();
                        break;
                    } else {
                        System.out.println("用户名或者密码错误,请重新输入您的选择....");
                        break;
                    }

                case "2":
                    // TODO
                    register(users);
                    break;
                case "3":
                    // TODO
                    System.out.println("谢谢使用，欢迎下次光临");
                    System.exit(0);
                    break;
                default:
                    System.out.println("不存在您的选择，请重新输入！");

            }

        }
    }

    // 商品管理系统
    private static void goodsManager() {

        while (true) {

            // 商品管理
            System.out.println("--------------------------商品管理模块-------------------------------");
            System.out.println("1: 添加商品");
            System.out.println("2: 查询商品");
            System.out.println("3: 退出");
            System.out.println("--------------------------商品管理模块-------------------------------");

            // 请输入您的选择
            System.out.println();
            System.out.println("请输入您的选择：");
            Scanner sc = new Scanner(System.in);
            String next = sc.next();

            // 选择
            switch (next) {
                case "1":
                    // TODO
                    addGoods();
                    break;
                case "2":
                    // TODO
                    findAllGoods();
                    break;
                case "3":
                    // TODO
                    System.out.println("谢谢使用，欢迎下次光临");
                    System.exit(0);
                    break;
                default:
                    System.out.println("不存在您输入的选择，请重新输入....");
                    break;
            }

        }

    }

    /**
     * 查询所有的商品信息
     */
    private static void findAllGoods() {
        try (
                FileInputStream fis = new FileInputStream(GOODS_INFO_FILE_PATH)
        ) {
            byte[] bytes=new byte[1024];
            int len;
            System.out.println("名称  价格");
            while ((len=fis.read(bytes))!=-1){
                System.out.println(new String(bytes,0,len));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    // 添加图书信息，使用文件存储商品信息
    private static void addGoods() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入商品名称！");
        String name = sc.nextLine();
        System.out.println("请输入商品价格！");
        int price = sc.nextInt();

        storeGoodsInfoToFile(new Goods(name, price));


    }

    /**
     * 商品数据保存到文件中
     *
     * @param goods
     */
    private static void storeGoodsInfoToFile(Goods goods) {
        String s = goods.getName() + "  " + goods.getPrice();
        try (
                FileOutputStream fos = new FileOutputStream(GOODS_INFO_FILE_PATH, true)
        ) {
            fos.write(s.getBytes());
            fos.write(System.lineSeparator().getBytes());
            System.out.println("添加商品成功");

        } catch (Exception e) {
            e.printStackTrace();
        }


        // 向文件中写数据


    }

    // 登录操作
    private static boolean login(ArrayList<User> users) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你的账号：");
        String s1 = sc.nextLine();
        System.out.println("请输入你的密码：");
        String s2 = sc.nextLine();
        for (User user : users) {
            if (s1.equals(user.getName()) && s2.equals(user.getPsw()))
                return true;
            return false;
        }
        return false;
    }

    // 注册
    private static void register(ArrayList<User> users) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你想要注册的账号");
        String name = sc.nextLine();
        if (!isUsed(users, name)) {
            System.out.println("请输入你想要注册的密码");
            String psw = sc.nextLine();

            users.add(new User(name, psw));
            System.out.println("注册成功");
        } else System.out.println("账号已被占用！");


    }


    // 判断用户名是否存在
    private static boolean isUsed(ArrayList<User> users, String userName) {
        for (User user : users) {
            if (userName.equals(user.getName()))
                return true;
        }
        return false;

    }

}
