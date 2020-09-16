package com.lcl.leetcode;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: longestCommonPrefix
 * @date 2019-07-22 11:09
 */
public class longestCommonPrefix {
    private static String getSolution(String[] args) {
        String minSize = args[0];
        for (String arg : args) {
            if (arg.length() <= minSize.length()) {
                minSize = arg;
            }
            System.out.println(minSize);
        }
        for (int i = 0; i < args.length+1; i++) {
            for (String arg : args) {
                if (!arg.substring(0,minSize.length()).equals(minSize)) {
                    minSize = minSize.substring(0,(minSize.length()-1));
                    System.out.println(minSize);
                }
            }
        }
        return minSize;
    }

    public static void main(String[] args) {
        String[] strings = {"aca","cba"};
        System.out.print(getSolution(strings));
    }
}
