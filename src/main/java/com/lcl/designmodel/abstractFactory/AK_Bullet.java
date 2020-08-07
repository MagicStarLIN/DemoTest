package com.lcl.designmodel.abstractFactory;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: AK_Bullet
 * @date 2019-08-23 15:59
 */
public class AK_Bullet implements Bullet {
    @Override
    public void load() {
        System.out.println("AK load");
    }
}
