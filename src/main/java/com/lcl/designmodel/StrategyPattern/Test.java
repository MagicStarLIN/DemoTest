package com.lcl.designmodel.StrategyPattern;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-07-25 15:18
 */
public class Test {
    public static void main(String[] args) {
        Environment environment = new Environment(new AddStrategy());
        int result = environment.calulate(1,2);
        System.out.println(result);
//        Integer i = new Integer(5);
//        Integer[] arr = {123};
////        boolean flag = i.getClass() == Object.class;
//        String[] elementData = {"sdfas"};
//        int size = 0;
//        if (arr.getClass() != Object[].class) {
//
//            System.out.println(1);
//        }
//
//        ArrayList<String> list = new ArrayList<>();
//        list.add("lll");
//        list.toArray(elementData);
//        ArrayList<String> list2 = new ArrayList<String>(list);
    }

}
