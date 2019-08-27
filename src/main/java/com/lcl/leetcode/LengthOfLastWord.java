package com.lcl.leetcode;


/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: LengthOfLastWord
 * @Description: 58. 最后一个单词的长度
 * @date 2019-07-30 10:32
 */
public class LengthOfLastWord {
    public static int lengthOfLastWord(String s) {
        if (s.indexOf(" ") == -1) {
            return s.toCharArray().length;
        }
        String[] arr = s.split(" ");
        int index = arr.length -1;
        if (index < 0) {
            return 0;
        }
        return arr[index].toCharArray().length;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("a "));
    }
}
