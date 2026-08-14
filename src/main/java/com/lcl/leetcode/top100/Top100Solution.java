package com.lcl.leetcode.top100;

import java.util.*;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Top100Solution
 * @Description <a href="https://leetcode.cn/studyplan/top-100-liked/">LeetCode Top100</a>
 * @date 2026/7/24 20:33
 */
public class Top100Solution {

    /**
     * @return int[]
     * @Title twoSum
     * @Author liuchanglin
     * @Date 2026/7/24 20:34
     * @Param [nums, target]
     * @Description 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
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
     * <p>
     * <p>
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
     * @return java.util.List<java.util.List<java.lang.String>>
     * @Title groupAnagrams
     * @Author liuchanglin
     * @Date 2026/7/24 21:15
     * @Param [strs]
     * @Description 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
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


    /**
     * @Title
     * @Author liuchanglin
     * @Date 2026/7/25 20:20
     * @Param
     * @Description 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     * 示例 1：
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
     * 示例 2：
     * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
     * 输出：9
     * 示例 3：
     * 输入：nums = [1,0,1,2]
     * 输出：3
     * 提示：
     * 0 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     **/
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int resultMax = 1;
        for (int num : set) {
            int max = 1;
            if (set.contains(num + 1)) {
                continue;
            }
            while (set.contains(num--)) {
                max++;
            }

            resultMax = Math.max(resultMax, max);
        }
        return resultMax;
    }


    /**
     * @Title moveZeroes
     * @Author liuchanglin
     * @Date 2026/7/26 20:40
     * @Param [nums]
     * @Description 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     * 示例 1:
     * 输入: nums = [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 示例 2:
     * 输入: nums = [0]
     * 输出: [0]
     * 提示:
     * 1 <= nums.length <= 104
     * -231 <= nums[i] <= 231 - 1
     * 进阶：你能尽量减少完成的操作次数吗？
     **/
    public void moveZeroes(int[] nums) {
        if (nums.length == 0) {
            return;
        }
        int i = 0;
        int j = i + 1;
        while (i < j && j < nums.length) {
            if (nums[i] != 0 && nums[j] != 0) {
                i++;
                j++;
                continue;
            }
            if (nums[i] == 0 && nums[j] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j++;
                continue;
            }

            if (nums[i] == 0 && nums[j] == 0) {
                j++;
                continue;
            }

            if (nums[i] != 0 && nums[j] == 0) {
                i++;
                if (i == j) {
                    j++;
                }
            }
        }
    }

    public void moveZeroesV2(int[] nums) {
        int pointer = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[pointer];
                nums[pointer] = temp;
                pointer++;
            }
        }
    }

    /**
     * @return int
     * @Title maxArea
     * @Description <a href="https://leetcode.cn/problems/container-with-most-water/description/?envType=study-plan-v2&envId=top-100-liked">盛最多水的容器</a>
     * @Author liuchanglin
     * @Date 2026/7/26 22:10
     * @Param [height]
     **/
    public int maxArea(int[] height) {

        int max = 0;

        for (int i = 0; i < height.length; i++) {
            for (int j = height.length - 1; j > i; j--) {
                int h = Math.min(height[i], height[j]);
                int s = j - i;
                int size = h * s;
                max = Math.max(size, max);
            }
        }
        return max;

    }

    public int maxAreaV2(int[] height) {

        int i = 0;
        int j = height.length - 1;
        int area = (j - i) * Math.min(height[i], height[j]);

        while (i < j) {
            if (height[i] >= height[j]) {
                j--;
                int newArea = (j - i) * Math.min(height[i], height[j]);
                area = Math.max(area, newArea);
            } else {
                i++;
                int newArea = (j - i) * Math.min(height[i], height[j]);
                area = Math.max(area, newArea);
            }

        }
        return area;

    }


    /**
     * @return java.util.List<java.util.List<java.lang.Integer>>
     * @Title threeSum
     * @Description <a href="https://leetcode.cn/problems/3sum/description/?envType=study-plan-v2&envId=top-100-liked">15. 三数之和</a>
     * @Author liuchanglin
     * @Date 2026/7/26 23:43
     * @Param [nums]
     **/
    public List<List<Integer>> threeSum(int[] nums) {

        Set<List<Integer>> result = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            int target = -nums[i];

            Map<Integer, Integer> resAndIndex = new HashMap<>();
            for (int j = i + 1; j < nums.length; j++) {
                if (resAndIndex.containsKey(nums[j])) {
                    int k = resAndIndex.get(nums[j]);
                    List<Integer> triple = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k]));

                    Collections.sort(triple);
                    result.add(triple);

                }
                resAndIndex.put(target - nums[j], j);
            }

        }
        return new ArrayList<>(result);
    }


    public List<List<Integer>> threeSumV2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            if (nums[i] > 0) {
                return result;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                i++;
                continue;
            }

            int L = i + 1;
            int R = j;

            while (L < R) {
                int sumResult = nums[i] + nums[L] + nums[R];
                if (sumResult == 0) {
                    result.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) {
                        L++;
                    }
                    while (L < R && nums[R] == nums[R - 1]) {
                        R--;
                    }
                    L++;
                    R--;
                } else if (sumResult < 0) {
                    L++;
                } else {
                    R--;
                }
            }
            i++;
        }
        return new ArrayList<>(result);
    }


    /**
     * @return int
     * @Title trap
     * @Description <a href="https://leetcode.cn/problems/trapping-rain-water/?envType=study-plan-v2&envId=top-100-liked">42. 接雨水</a>
     * @Author liuchanglin
     * @Date 2026/7/27 17:06
     * @Param [height]
     **/
    public int trap(int[] height) {
        int vol = 0;
        int left = 0;
        while (left < height.length - 1) {
            int right = left + 1;
            int maxRight = right;

            // 寻找后续最大的边
            while (right < height.length) {
                if (height[right] < height[left]) {
                    if (height[right] > height[maxRight]) {
                        maxRight = right;
                    }
                } else {
                    // 说明有新的边界可以用了
                    break;
                }
                right++;
            }

            if (right == height.length) {
                right = maxRight;
            }

            // 计算这一波雨水
            int maxHeight = Math.min(height[left], height[right]);
            for (int i = left + 1; i < right; i++) {
                vol += maxHeight - height[i];
            }

            // 更新left
            left = right;

        }
        return vol;
    }


    public int trapV2(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int vol = 0;

        int leftMax = height[left];
        int rightMax = height[right];

        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (leftMax > rightMax) {
                vol += rightMax - height[right];
                right--;
            } else {
                vol += leftMax - height[left];
                left++;
            }
        }
        return vol;
    }

    /**
     * @return int
     * @Title lengthOfLongestSubstring
     * @Description <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/?envType=study-plan-v2&envId=top-100-liked">3. 无重复字符的最长子串</a>
     * @Author liuchanglin
     * @Date 2026/7/28 22:18
     * @Param [s]
     **/
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        // "abcabcbb"
        char[] strs = s.toCharArray();
        String resultS = String.valueOf(strs[0]);
        int left = 0;
        int right = 1;
        int max = 1;
        while (left <= right && right < strs.length) {
            if (resultS.contains(String.valueOf(strs[right]))) {
                left++;
                resultS = resultS.substring(1);
            } else {
                resultS += strs[right];
                right++;
            }
            max = Math.max(max, right - left);
        }
        return max;

    }


    public static int lengthOfLongestSubstringV2(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        // "au"
        char[] strs = s.toCharArray();
        HashSet<Character> resultS = new HashSet<>(Collections.singleton(strs[0]));
        int left = 0;
        int right = 1;
        int max = 1;
        while (left <= right && right < strs.length) {
            if (resultS.contains(strs[right])) {
                resultS.remove(strs[left]);
                left++;
            } else {
                resultS.add(strs[right]);
                right++;
            }
            max = Math.max(max, resultS.size());
        }
        return max;

    }


    /**
     * @return java.util.List<java.lang.Integer>
     * @Title findAnagrams
     * @Description <a href="https://leetcode.cn/problems/find-all-anagrams-in-a-string/?envType=study-plan-v2&envId=top-100-liked">438. 找到字符串中所有字母异位词</a>
     * @Author liuchanglin
     * @Date 2026/7/29 23:46
     * @Param [s, p]
     **/
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int[] cntP = new int[26];
        int[] window = new int[26];

        for (char c : p.toCharArray()) {
            cntP[c - 'a']++;
        }

        int left = 0;
        int right = p.length() - 1;

        while (right < s.length()) {
            int index = left;
            while (index <= right) {
                window[s.charAt(index) - 'a']++;
                index++;
            }

            if (Arrays.equals(cntP, window)) {
                ans.add(left);
            }
            window = new int[26];
            left++;
            right++;

        }
        return ans;
    }


    public static List<Integer> findAnagramsV2(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int[] cntP = new int[26];
        int[] window = new int[26];

        for (char c : p.toCharArray()) {
            cntP[c - 'a']++;
        }

        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            window[s.charAt(right) - 'a']++;

            if ((right - left) + 1 > p.length()) {
                window[s.charAt(left) - 'a']--;
                left++;
            }

            if ((right - left) + 1 == p.length()) {
                if (Arrays.equals(cntP, window)) {
                    ans.add(left);
                } else {
                    window[s.charAt(left) - 'a']--;
                    left++;
                }
            }

        }

        return ans;
    }

    /**
     * @return int
     * @Title subarraySum
     * @Description <a href="https://leetcode.cn/problems/subarray-sum-equals-k/description/?envType=study-plan-v2&envId=top-100-liked">560. 和为 K 的子数组</a>
     * @Author liuchanglin
     * @Date 2026/7/31 00:39
     * @Param [nums, k]
     **/
    public int subarraySum(int[] nums, int k) {
        int ans = 0;
        for (int start = 0; start < nums.length; start++) {
            int res = k;
            int end = start;
            while (end < nums.length) {
                res -= nums[end];
                if (res == 0) {
                    ans++;
                }
                end++;
            }
        }
        return ans;
    }

    // 前缀和概念
    public int subarraySumV2(int[] nums, int k) {
        int ans = 0;
        HashMap<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);
        int sum = 0;
        for (int start = 0; start < nums.length; start++) {
            sum += nums[start];
            if (prefixSum.containsKey(sum - k)) {
                ans += prefixSum.get(sum - k);
            }
            prefixSum.put(sum, prefixSum.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }

    /**
     * @Title maxSlidingWindow
     * @Description <a href="https://leetcode.cn/problems/sliding-window-maximum/?envType=study-plan-v2&envId=top-100-liked">239. 滑动窗口最大值</a>
     * @Author liuchanglin
     * @Date 2026/8/4 20:04
     * @Param [nums, k]
     * @return int[]
     **/
    public int[] maxSlidingWindow(int[] nums, int k) {

        int left = 0;
        int right = k - 1;

        List<int[]> heap = new ArrayList<>();
        int heapSize = 0;

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {

            heapSize = offerHeap(heap, heapSize, nums[i], i);

            if (heapSize >= k) {
                while (true) {
                    int[] maxPos = heap.get(0);
                    int currentIndex = maxPos[1];
                    if (currentIndex <= right && currentIndex >= left) {
                        result.add(maxPos[0]);
                        break;
                    } else {
                        heapSize = removeHead(heap, heapSize);
                    }
                }
            }

            if (heapSize >= k) {
                left++;
                right++;
            }
        }

        return result.stream()
                .mapToInt(Integer::intValue)
                .toArray();


    }

    private int offerHeap(List<int[]> heap, int size, int num, int index) {
        int[] numAndIndex = new int[]{num, index};
        heap.add(numAndIndex);
        // sift up  上浮
        siftUp(heap, size);
        size++;
        return size;
    }

    private void siftUp(List<int[]> heap, int size) {
        while (size > 0) {
            int parentIndex = (size - 1) / 2;
            if (heap.get(parentIndex)[0] < heap.get(size)[0]) {
                swap(heap, size, parentIndex);
            } else {
                break;
            }
            size = parentIndex;
        }
    }

    private static void swap(List<int[]> heap, int aIndex, int bIndex) {
        int[] temp = heap.get(bIndex);
        heap.set(bIndex, heap.get(aIndex));
        heap.set(aIndex, temp);
    }

    private void siftDown(List<int[]> heap, int index, int size) {
        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int largest = index;

            if (left < size && heap.get(left)[0] > heap.get(largest)[0]) {
                largest = left;
            }

            if (right < size && heap.get(right)[0] > heap.get(largest)[0]) {
                largest = right;
            }

            if (largest == index) {
                break;
            }

            swap(heap, largest, index);
            index = largest;
        }
    }


    private int removeHead(List<int[]> heap, int heapSize) {
        heap.set(0, heap.get(heapSize - 1));
        heap.removeLast();
        heapSize--;
        if (heapSize > 0) {
            siftDown(heap, 0, heapSize);
        }
        return heapSize;
    }

    public int[] maxSlidingWindowV2(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();

        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty()
                    && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }

            deque.addLast(i);
        }

        result.add(nums[deque.peekFirst()]);

        for (int i = k; i < nums.length; i++) {

            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }

            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            deque.addLast(i);
            result.add(nums[deque.peekFirst()]);

        }

        return result.stream().mapToInt(Integer::intValue)
                .toArray();
    }


    /**
     * @Title minWindow
     * @Description <a href="https://leetcode.cn/problems/minimum-window-substring/?envType=study-plan-v2&envId=top-100-liked">76. 最小覆盖子串</a>
     * @Author liuchanglin
     * @Date 2026/8/5 20:25
     * @Param [s, t]
     * @return java.lang.String
     **/
    public String minWindow(String s, String t) {

        if (s == null || t == null || t.isEmpty() || s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> tMap = new HashMap<>();

        for (char c : t.toCharArray()) {
            int count = tMap.getOrDefault(c, 0);
            tMap.put(c, count + 1);
        }

        int left = 0;
        int right = 0;

        int valid = 0;

        int start = 0;

        Map<Character, Integer> sMap = new HashMap<>();
        int minLen = Integer.MAX_VALUE;

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;

            if (tMap.containsKey(c)) {
                int count = sMap.getOrDefault(c, 0) + 1;
                sMap.put(c, count);
                if (count <= tMap.get(c)) {
                    valid++;
                }
            }

            while (valid == t.length()) {
                char d = s.charAt(left);
                if (minLen > right - left) {
                    start = left;
                    minLen = Math.min(right - left, minLen);
                }
                left++;
                if (tMap.containsKey(d)) {
                    int count = sMap.get(d);
                    if (count <= tMap.get(d)) {
                        valid--;
                    }
                    sMap.put(d, count - 1);
                }
            }

        }
        return minLen == Integer.MAX_VALUE
                ? ""
                : s.substring(start, start + minLen);

    }


    /**
     * @Title maxSubArray
     * @Description <a href="https://leetcode.cn/problems/maximum-subarray/?envType=study-plan-v2&envId=top-100-liked">53. 最大子数组和</a>
     * @Author liuchanglin
     * @Date 2026/8/6 02:14
     * @Param [nums]
     * @return int
     **/
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int res = 0;
            for (int j = i; j < nums.length; j++) {
                res += nums[j];
                max = Math.max(res, max);
            }
        }
        return max;

    }
    // TODO 动态规划法

    /**
     * @Title merge
     * @Description <a href="https://leetcode.cn/problems/merge-intervals/?envType=study-plan-v2&envId=top-100-liked">56. 合并区间</a>
     * @Author liuchanglin
     * @Date 2026/8/6 18:33
     * @Param [intervals]
     * @return int[][]
     **/
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][];
        }

        // 按照左端点排序
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));

        List<int[]> result = new ArrayList<>();

        int start = intervals[0][0];
        int end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= end) {
                end = Math.max(intervals[i][1], end);
            } else {
                result.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }

        }

        result.add(new int[]{start, end});

        return result.toArray(new int[result.size()][]);

    }

    /**
     * @return void
     * @Title rotate
     * @Description <a href="https://leetcode.cn/problems/rotate-array/?envType=study-plan-v2&envId=top-100-liked">189. 轮转数组</a>
     * @Author liuchanglin
     * @Date 2026/8/6 19:39
     * @Param [nums, k]
     **/
    public boolean rotate(int[] nums, int k) {
        if (k == nums.length) {
            return false;
        }

        k %= nums.length; // nums[1,2] k = 7 这种情况下 先把 k 转换成“有效移动次数”
        List<Integer> tail = new ArrayList<>();
        List<Integer> head = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i < nums.length - k) {
                tail.add(nums[i]);
            } else {
                head.add(nums[i]);
            }
        }
        int tailIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < head.size()) {
                nums[i] = head.get(i);
            } else {
                nums[i] = tail.get(tailIndex);
                tailIndex++;
            }
        }
        return false;
    }

    // 三次反转法
    public void rotateV2(int[] nums, int k) {
        int n = nums.length;
        k %= n;

        if (k == 0) {
            return;
        }

        // 1. 整体反转
        reverse(nums, 0, n - 1);

        // 2. 反转前 k 个元素
        reverse(nums, 0, k - 1);

        // 3. 反转后 n-k 个元素
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            left++;
            right--;
        }
    }


    /**
     * @Title productExceptSelf
     * @Description <a href="https://leetcode.cn/problems/product-of-array-except-self/?envType=study-plan-v2&envId=top-100-liked">238. 除了自身以外数组的乘积</a>
     * @Author liuchanglin
     * @Date 2026/8/6 20:25
     * @Param [nums]
     * @return int[]
     **/
    public int[] productExceptSelf(int[] nums) {
        Map<Integer, Integer> prePro = new HashMap<>();
        Map<Integer, Integer> sufPro = new HashMap<>();

        int pro = 1;
        int index = 1;
        for (int i = 0; i < nums.length; i++) {
            pro *= nums[i];
            prePro.put(index, pro);
            index++;
        }

        pro = 1;
        index = 1;
        for (int i = nums.length - 1; i > -1; i--) {
            pro *= nums[i];
            sufPro.put(index, pro);
            index++;
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = sufPro.getOrDefault(nums.length - i - 1, 1) * prePro.getOrDefault(i, 1);
        }
        return result;
    }


    public int[] productExceptSelfV2(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // result[i] 暂时保存 nums[i] 左侧所有元素的乘积
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // suffix 保存当前位置右侧所有元素的乘积
        int suffix = 1;

        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffix;
            suffix *= nums[i];
        }

        return result;
    }

    /**
     * @Title firstMissingPositive
     * @Description <a href="https://leetcode.cn/problems/first-missing-positive/description/?envType=study-plan-v2&envId=top-100-liked">41. 缺失的第一个正数</a>
     * @Author liuchanglin
     * @Date 2026/8/7 21:05
     * @Param [nums]
     * @return int
     **/
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] <= nums.length && nums[i] >= 1 && nums[nums[i] - 1] != nums[i]) {
                int target = nums[i] - 1;

                int temp = nums[i];
                nums[i] = nums[target];
                nums[target] = temp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    /**
     * @Title setZeroes
     * @Description <a href="https://leetcode.cn/problems/set-matrix-zeroes/?envType=study-plan-v2&envId=top-100-liked">73. 矩阵置零</a>
     * @Author liuchanglin
     * @Date 2026/8/14 15:41
     * @Param [matrix]
     * @return void
     **/
    public void setZeroes(int[][] matrix) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    list.add(new int[]{i, j});
                }
            }
        }

        for (int[] ints : list) {
            int x = ints[0];
            int y = ints[1];
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][y] = 0;
            }
            for (int i = 0; i < matrix[x].length; i++) {
                matrix[x][i] = 0;
            }

        }

    }

}
