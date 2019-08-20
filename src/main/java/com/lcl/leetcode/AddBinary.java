package com.lcl.leetcode;

import java.util.LinkedList;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: AddBinary
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-07-29 09:49
 */
public class AddBinary {
    public static String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int ca = 0;
        for(int i = a.length() - 1, j = b.length() - 1;i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            ans.append(sum % 2);
            System.out.println(ans);
            ca = sum / 2;
        }
        ans.append(ca == 1 ? ca : "");
        return ans.reverse().toString();
    }

    public static void main(String[] args) {
//        LinkedList<Integer> linkedList = new LinkedList<>();
//        System.out.println(addBinary("1","111"));
//        System.out.println(c);
        int a = 0,b = 2;
        System.out.println(a);
        System.out.println(b);
    }
}
