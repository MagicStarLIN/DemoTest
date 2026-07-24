package com.lcl.leetcode.top100;

import java.util.*;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: TwoSum
 * @date 2026/7/24 20:33
 */
public class Top100Solution {

    /**
     * @Title twoSum
     * @Author liuchanglin
     * @Date 2026/7/24 20:34
     * @Param [nums, target]
     * @return int[]
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
     * 你可以按任意顺序返回答案。
     * 示例 1：
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * 示例 2：
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     * 示例 3：
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     * 提示：
     * 2 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     * 只会存在一个有效答案
     *
     *
     * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
     **/
    public int[] twoSumV1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }

        }
        return null;
    }

    public int[] twoSumV2(int[] nums, int target) {
        Map<Integer, Integer> remainderAndIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (remainderAndIndex.containsKey(nums[i])) {
                return new int[]{remainderAndIndex.get(nums[i]), i};
            }
            int remainder = target - nums[i];
            remainderAndIndex.put(remainder, i);
        }
        return null;
    }


    /**
     * @Title groupAnagrams
     * @Author liuchanglin
     * @Date 2026/7/24 21:15
     * @Param [strs]
     * @return java.util.List<java.util.List<java.lang.String>>
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * 示例 1:
     * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     * 解释：
     * 在 strs 中没有字符串可以通过重新排列来形成 "bat"。
     * 字符串 "nat" 和 "tan" 是字母异位词，因为它们可以重新排列以形成彼此。
     * 字符串 "ate" ，"eat" 和 "tea" 是字母异位词，因为它们可以重新排列以形成彼此。
     * 示例 2:
     * 输入: strs = [""]
     * 输出: [[""]]
     * 示例 3:
     * 输入: strs = ["a"]
     * 输出: [["a"]]
     * 提示：
     * 1 <= strs.length <= 104
     * 0 <= strs[i].length <= 100
     * strs[i] 仅包含小写字母
     **/
    public List<List<String>> groupAnagramsV1(String[] strs) {
        Map<String, List<String>> stringListMap = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] strChar = strs[i].toCharArray();
            Arrays.sort(strChar);
            String sortedStr = new String(strChar);
            List<String> categoryStrs = stringListMap.getOrDefault(sortedStr, new ArrayList<>());
            categoryStrs.add(strs[i]);
            stringListMap.put(sortedStr, categoryStrs);
        }
        return new ArrayList<>(stringListMap.values());
    }

    public List<List<String>> groupAnagramsV2(String[] strs) {
        Map<String, List<String>> stringListMap = new HashMap<>();
        for (String str : strs) {
            String key = "";
            int[] letterCount = new int[26];

            char[] strChar = str.toCharArray();
            for (char c : strChar) {
                int i = c - 'a';
                letterCount[i]++;
            }


            for (int i = 0; i < letterCount.length; i++) {
                key += "#" + letterCount[i];
            }

//            List<String> orDefault = stringListMap.getOrDefault(key, new ArrayList<>());
//            orDefault.add(str);
//            stringListMap.put(key, orDefault);

            stringListMap.computeIfAbsent(key, ignored -> new ArrayList<>()).add(str);

        }
        return new ArrayList<>(stringListMap.values());
    }

}
