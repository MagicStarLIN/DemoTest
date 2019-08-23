package com.lcl.designmodel.abstractFactory;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: AK_Bullet
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-23 15:59
 */
public class AK_Bullet implements Bullet {
    @Override
    public void load() {
        System.out.println("AK load");
    }
}
