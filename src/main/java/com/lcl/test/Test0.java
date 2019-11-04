package com.lcl.test;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: nono
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-27 16:51
 */
public class Test0 {
//    ^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$
//    /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/

    static List<Integer> list = new ArrayList<>();

    private static void listAddData(List<Integer> list) {
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
    }
    public static void main(String[] args) {
        listAddData(list);

        List<Integer> l1 = list.subList(0, 10);
        List<Integer> l2 = list.subList(10, list.size());

        for (Integer integer : l1) {
            System.err.println("l1:"+integer);
        }

        for (Integer integer : l2) {
            System.err.println("l2:"+integer);

        }
    }


}
