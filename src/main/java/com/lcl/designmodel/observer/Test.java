package com.lcl.designmodel.observer;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test
 * @date 2019-07-22 11:01
 */
public class Test {
    private static WechatServer wechatServer = new WechatServer();
    public static void main(String[] args) {

        Observer user1 = new User("lcl 1");
        Observer user2 = new User("lcl 2");
        Observer user3 = new User("lcl 3");

        wechatServer.registerObserver(user1);
        wechatServer.registerObserver(user2);
        wechatServer.registerObserver(user3);

        wechatServer.setInformation("java nb");
        System.out.println("-----------------------------------------");

        wechatServer.removeObserver(user3);
        wechatServer.setInformation("java nb again");
    }

}
