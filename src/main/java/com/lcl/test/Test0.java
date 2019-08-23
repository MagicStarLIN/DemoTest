package com.lcl.test;

import java.util.ArrayList;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test0
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-20 09:40
 */
public class Test0 {
    public static void main(String[] args) {
        String name = "刘常林lcl";
        int i = Integer.MAX_VALUE;
        int j = Integer.MAX_VALUE;
        ArrayList list = new ArrayList();
        list.add(100);
        list.add("lcl");
        for (Object o : list) {
            System.out.println(o);

        }

    }
}
