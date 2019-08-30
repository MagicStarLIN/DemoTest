package com.lcl.designmodel.singleton;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Singleton
 * @Description: 线程安全的单例模式 懒汉
 * @date 2019/8/30 3:31 下午
 */
public class Singleton {
    private Singleton() {

    }

    private volatile static Singleton Instance = null;

    public static Singleton getInstance() {
        if (Instance == null) {
            synchronized (Singleton.class) {
                if (Instance == null) {
                    return Instance = new Singleton();
                }
            }
        }
        return null;
    }
}
