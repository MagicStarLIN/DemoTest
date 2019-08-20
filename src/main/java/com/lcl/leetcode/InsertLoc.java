package com.lcl.leetcode;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: InsertLoc
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-07-25 14:14
 */
public class InsertLoc {
    public static int searchInsert(int[] nums, int target) {
        int i = 0;
        int j = 1;
         do {
            if(target < nums[i]){
                System.out.println("!");
                return 0;
            }
            if(target == nums[i]) {
                System.out.println("!!");
                return i;
            }
            if (target > nums[i]) {
                System.out.println("!!!");
                return j;
            }
            i++;
            j++;
        } while (j < nums.length);
        return nums.length;
    }

    public static void main(String[] args) {
        int[] arr = {1};
        System.out.println(searchInsert(arr,2));
    }
}
