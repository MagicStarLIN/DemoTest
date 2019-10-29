package com.lcl.leetcode;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: twoSum
 * @Description:
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * @date 2019/10/29 10:00 上午
 */
public class twoSum {
    public static int[] twosum(int[] nums,int target) {
        for (int i = 0; i < nums.length; i++) {
            for(int j = i+1 ; j < nums.length; j++){
                if (nums[j] == target - nums[i]) {
                    return new int[] {i,j};
                }
            }
        }  throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int[] newArr = twosum(arr,9);
        for (int i : newArr) {
            System.out.println(i);
        }
    }
}
