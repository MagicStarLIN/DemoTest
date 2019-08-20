package com.lcl.designmodel.observer;

/**
 * @ClassName: Observerable
 * @Description: 观察者接口
 * @author liuchanglin
 * @date 2019-07-22 10:23
 * @version 1.0
 */
public interface Observerable {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObserver();
}
