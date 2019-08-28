package com.lcl.leetcode;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: NumJewelsInStones
 * @Description: 771. 宝石与石头
 * @date 2019-08-28 14:26
 */
public class NumJewelsInStones {
    private int numJewelsInStones(String J, String S) {
        char[] jArr = J.toCharArray();
        char[] sArr = S.toCharArray();
        int count = 0;
        for (char c : jArr) {
            for (char c1 : sArr) {
                if (c == c1) {
                    count++;
                }
            }
            
        }
        return count;
    }
}
