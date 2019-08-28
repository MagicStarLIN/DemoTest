package com.lcl.leetcode;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: MoveZeroes
 * @Description: 283. 移动零
 * @date 2019-08-28 15:41
 */
public class MoveZeroes {
    private void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }
        for (int i = index; i < nums.length; i++) {
            nums[index] = 0;
            index++;
        }
    }
}
