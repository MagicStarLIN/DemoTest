package com.lcl.designmodel.abstractFactory;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: AK_Factory
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-23 16:01
 */
public class AK_Factory implements Factory {
    @Override
    public Gun produceGun() {
        return new AK();
    }

    @Override
    public Bullet produceBullet() {
        return new AK_Bullet();
    }
}
