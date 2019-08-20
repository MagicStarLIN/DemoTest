package com.lcl.designmodel.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: WechatServer
 * @Description: 被观察者，也就是微信公众号服务
 * @date 2019-07-22 10:38
 */
public class WechatServer implements Observerable{

    private List<Observer> list;
    private String message;

    public WechatServer() {
        list = new ArrayList<>();

    }

    @Override
    public void registerObserver(Observer observer) {
        list.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (!list.isEmpty()) {
            list.remove(observer);
        }
    }

    @Override
    public void notifyObserver() {
        for (int i = 0; i < list.size(); i++) {
            Observer observer = list.get(i);
            observer.update(message);
        }
    }

    public void setInformation(String message) {
        this.message = message;
        System.out.println("消息：" + message);
        notifyObserver();
    }
}
