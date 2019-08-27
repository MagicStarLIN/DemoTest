package com.lcl.leetcode;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: MaxProfit
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-27 15:55
 */
public class MaxProfit {
    public static void main(String[] args) {
        int[] prices = {7,6,4,3,1};
        System.out.println(maxProfit2(prices));
    }
    //[1] 暴力法
    private static int maxProfit(int[] prices) {
        int maxPro = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i ; j < prices.length; j++) {
                maxPro = Math.max(prices[j] - prices[i], maxPro);
            }
        }
        return maxPro;
    }

    //[2] 双指针
    private static int maxProfit2(int[] prices) {
        int maxPro = 0;
        int buy = 0;
        int sell = 0;
        for (; sell < prices.length; sell++) {
            if (prices[sell] - prices[buy] < 0) {
                buy = sell;
            } else {
                maxPro = Math.max(maxPro, prices[sell] - prices[buy]);
            }
        }
        return maxPro;
    }

    //[3] 动态规划
    private static int maxProfit3(int[] prices) {
        int maxPro = 0;
        int cost = Integer.MAX_VALUE;
        for (int price : prices) {
            cost = Math.min(price, cost);
            maxPro = Math.max(maxPro, price - cost);
        }
        return maxPro;
    }

}
