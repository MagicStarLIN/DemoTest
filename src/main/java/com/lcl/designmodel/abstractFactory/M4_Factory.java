package com.lcl.designmodel.abstractFactory;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: M4_Factory
 * @date 2019-08-23 16:02
 */
public class M4_Factory implements Factory {
    @Override
    public Gun produceGun() {
        return new M4();
    }

    @Override
    public Bullet produceBullet() {
        return new M4_Bullet();
    }
}
