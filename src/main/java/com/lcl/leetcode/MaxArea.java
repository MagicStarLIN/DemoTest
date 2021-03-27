package com.lcl.leetcode;

/**
 * @ClassName MaxArea
 * @Description leetCode 11. Container With Most Water
 * @Date 2021/3/27 6:19 下午
 * @Author liuchanglin
 * @Version 1.0
 **/
public class MaxArea {
    public static int solution(int[] height) {
        int start = 0;
        int end = height.length - 1;
        int res = Math.min(height[start], height[end]) * (end - start);

        while (end > start) {
            if (height[start] < height[end]) {
                start += 1;
                int newRes = Math.min(height[start], height[end]) * (end - start);
                res = Math.max(newRes, res);
            } else {
                end -= 1;
                int newRes = Math.min(height[start], height[end]) * (end - start);
                res = Math.max(newRes, res);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] height = {1,2,1};
        System.out.println(solution(height));

    }
}
