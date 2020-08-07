package com.lcl.designmodel.abstractFactory;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test
 * @date 2019-08-23 16:03
 */
public class Test {
    public static void main(String[] args) {
        Factory factory;
        Gun gun;
        Bullet bullet;
        factory = new AK_Factory();
        bullet = factory.produceBullet();
        gun = factory.produceGun();
        gun.shot();
        bullet.load();
    }
}
