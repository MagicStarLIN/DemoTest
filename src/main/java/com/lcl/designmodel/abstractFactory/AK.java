package com.lcl.designmodel.abstractFactory;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: AK
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-23 15:58
 */
public class AK implements Gun{

    @Override
    public void shot() {
        System.out.println("AK shot");
    }
}
