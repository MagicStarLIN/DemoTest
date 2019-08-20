package com.lcl.leetcode;


/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: LengthOfLastWord
 * @Description: TODO(这里用一句话描述这个类的作用)
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("a "));
    }
}
