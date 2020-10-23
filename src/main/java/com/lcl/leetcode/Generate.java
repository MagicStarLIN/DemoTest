package com.lcl.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 118. 杨辉三角
 */
public class Generate {

    /**
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
     * 输入: 5
     * 输出:
     * [
     * [1],
     * [1,1],
     * [1,2,1],
     * [1,3,3,1],
     * [1,4,6,4,1]
     * ]
     */

    public List<List<Integer>> solution(int nums) {
        List<List<Integer>> lists = new ArrayList<>(nums);
        List<Integer> preList = new ArrayList<>(1);
        preList.add(1);
        lists.add(preList);
        for (int i = 0; i < nums - 1; i++) {
            List<Integer> list = new ArrayList<>(i + 1);
            list.add(1);
//            list.set(list.size() - 1, 1);
            for (int j = 1; j < i +1; j++) {
                list.add(preList.get(j - 1) + preList.get(j));
            }
            list.add(1);
            preList = list;
            lists.add(list);
        }
        return lists;
    }

    public static void main(String[] args) {
        System.out.println(new Generate().solution(5).toString());;
    }
}
