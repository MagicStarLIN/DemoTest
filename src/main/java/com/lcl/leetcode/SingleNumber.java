package com.lcl.leetcode;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: SingleNumber
 * @Description: 136. 只出现一次的数字
 * @date 2019-08-28 14:16
 */
public class SingleNumber {
    private int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }
}
