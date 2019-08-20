package com.lcl.designmodel.observer;

/**
 * @ClassName: Observer
 * @Description: 定义了一个update()方法，当被观察者调用notifyObservers()方法时，观察者的update()方法会被回调
 * @author liuchanglin
 * @date 2019-07-22 10:29
 * @version 1.0
 */
public interface Observer {
    
    void update(String message);

}
