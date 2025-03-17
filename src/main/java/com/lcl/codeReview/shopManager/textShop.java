package com.lcl.codeReview.shopManager;

import java.util.Scanner;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: textShop
 * @date 2021/1/3 8:09 下午
 */
public class textShop { 
    static Scanner sc = new Scanner(System.in);
    public static LocalShop lclshop = new LocalShop();
    public static Cart currentCart = new Cart();
    public static boolean b1 = true;
    public static User currentUser = new User(0000, "0000");

    public static void main(String[] args) {
        System.out.println("刘常林商店");
        lclshop.typeSet();
        System.out.println("请选择需要进行的操作");
        System.out.println("1.登陆");
        System.out.println("2.退出");
        boolean b = true;
        int temp = 0;
        while (b) {
            temp = sc.nextInt();
            if (temp == 1) {
                if (login()) {
                    boolean b2 = true;
                    while (b2) {
                        System.out.println("欢迎光临，商店内货物及价格如下");
                        lclshop.show();
                        System.out.println("请选择如下操作");
                        System.out.println("1.开始购物");
                        System.out.println("2.查看购物车");
                        System.out.println("3.结账");
                        System.out.println("4.退出");
                        System.out.println("请输入操作编号");
                        int x = sc.nextInt();
                        switch (x) {
                            case 1:
                                while (b1) {
                                    shoping();
                                    if (lclshop.CommodityList == null) {
                                        System.out.println("商店没货了 关门！");
                                    }

                                }
                                break;
                            case 2:
                                showCart();
                                break;
                            case 3:
                                checkOut();
                                break;
                            case 4:
                                b2 = false;
                                break;
                            default:
                                System.out.println("请输入正确的操作代码");
                                break;
                        }
                    }
                }

            } else {
                b = false;
            }
        }


    }

    private static void checkOut() {
        currentCart.checkOut();
    }

    private static void showCart() {
        for (Commodity c : currentCart.CommodityCartList) {
            System.out.println("购物车中商品为");
            c.showCom();
        }
    }

    private static void shoping() {
        System.out.println("请输入要购买的商品名称");
        String name = sc.next();
        for (Commodity c : lclshop.CommodityList) {
            if (name.equals(c.getName())) {
                currentCart.add(c);
                lclshop.CommodityList.remove(c);
                break;
            }

        }
        if (name.equalsIgnoreCase("exit")) {
            b1 = false;
        }
    }

    private static boolean login() {
        System.out.println("请输入账号");
        int ID = sc.nextInt();
        System.out.println("请输入密码");
        String password = sc.next();
        if (ID == currentUser.getID() && password.equals(currentUser.getPassword())) {
            System.out.println("登陆成功");
            return true;
        } else {
            System.out.println("密码或账号输入错误");
            return false;
        }
    }
}
