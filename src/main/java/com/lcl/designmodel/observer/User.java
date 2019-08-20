package com.lcl.designmodel.observer;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: User
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-07-22 10:55
 */
public class User implements Observer {
    private String message;
    private String name;

    public User(String name) {
        this.name = name;
    }
    @Override
    public void update(String message) {
        this.message = message;
        read();
    }

    private void read() {
        System.out.println(name + ":" + message);
    }
}
