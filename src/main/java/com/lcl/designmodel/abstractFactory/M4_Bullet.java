package com.lcl.designmodel.abstractFactory;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: M4_Bullet
 * @date 2019-08-23 16:00
 */
public class M4_Bullet implements Bullet {
    @Override
    public void load() {
        System.out.println("M4 load");
    }
}
