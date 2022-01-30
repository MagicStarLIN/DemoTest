package com.lcl.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ThreeSum
 * @Description 15. 三数之和
 * @Date 2022/1/30 3:59 PM
 * @Author liuchanglin
 * @Version 1.0
 **/
public class ThreeSum {

    public List<List<Integer>> solution(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (nums.length == 0) {
            return resultList;
        }
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {

            if (nums[i] > 0) {
                return resultList;
            }

            if(i > 0 && nums[i] == nums[i-1]) continue;

            int L = i + 1;
            int R = n - 1;

            while (L < R) {
                int currResult = nums[i] + nums[L] + nums[R];
                if (currResult == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    resultList.add(list);
                    while (L < R && nums[L] == nums[L + 1]) ++L;
                    while (L < R && nums[R] == nums[L - 1]) --R;
                    ++L;
                    --R;
                } else if (currResult < 0) {
                    ++L;
                } else if (currResult > 0) {
                    --R;
                }
            }
        }
        return resultList;
    }
}
