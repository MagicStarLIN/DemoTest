package com.lcl.leetcode;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: MaxSubArray
 * @Description: 53.最大子序和
 * @date 2019-08-20 10:09
 */
public class MaxSubArray {
    public static int maxSubArray(int[] nums) {
//        //[1]暴力法
//        int max = Integer.MIN_VALUE;
//        for (int i = 0; i < nums.length; i++) {
//            int sum = 0;
//            for (int j = i; j < nums.length; j++) {
//                sum += nums[j];
//                if (sum > max) {
//                    max = sum;
//                }
//            }
//
//        }
//        return max;
        //[2]动态规划
        int sum = 0;
        int ans = nums[1];
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(sum, ans);
        }
        return ans;
    }


}
