package com.lcl.leetcode;

/**
 * @ClassName BeautifulArray
 * @Description // 昨夜山河齐恸震，今朝大星辞九州 RIP 袁隆平
 * @Date 2021/5/22 3:15 下午
 * @Author liuchanglin
 * @Version 1.0
 **/

import java.util.HashMap;
import java.util.Map;

/**
 * 对于某些固定的N，如果数组A是整数1, 2, ..., N组成的排列，使得：
 * 对于每个i < j，都不存在k 满足i < k < j使得A[k] * 2 = A[i] + A[j]。
 * 那么数组 A是漂亮数组。
 * 给定N，返回任意漂亮数组A（保证存在一个）。
 *
 * 示例 1：
 *  输入：4
 *  输出：[2,1,4,3]
 * 示例 2：
 *  输入：5
 *  输出：[3,1,2,5,4]
 * 提示：
 * 1 <= N <= 1000
 */
public class BeautifulArray {
    Map<Integer, int[]> memo;
    public int[] solution(int n) {
        memo = new HashMap<>();
        memo.put(1, new int[]{1});
        return getBeautifulArray(n);
    }

    private int[] getBeautifulArray(int n) {
        if (!memo.containsKey(n)) {
            int[] result = new int[n];
            int index = 0;
            for (int i : getBeautifulArray((n + 1) / 2)) {
                result[index++] = 2 * i - 1;
            }

            for (int i : getBeautifulArray(n / 2)) {
                result[index++] = 2 * i;
            }
            memo.put(n, result);
        }
        return memo.get(n);
    }
}
