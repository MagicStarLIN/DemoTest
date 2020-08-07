package com.lcl.designmodel.abstractFactory;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: AK
 * @date 2019-08-23 15:58
 */
public class AK implements Gun{

    @Override
    public void shot() {
        System.out.println("AK shot");
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
