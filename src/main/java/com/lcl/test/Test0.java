package com.lcl.test;

import java.util.Arrays;
import java.util.List;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test0
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-20 09:40
 */
public class Test0 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring(" "));

    }
    public static int lengthOfLongestSubstring(String s) {
        String a = s;
        char[] array = a.toCharArray();
        int b =array.length;
        int k = 0;
        if(s =="" || s ==" "){
            return 1;
        }else{
            for(int i = 0;i<b-1;i++){
                for(int j = i+1; j <=b-1;j++){
                    if(array[i] == array[j]){
                        if(j>i && Math.abs(i-j)>k){
                            k = Math.abs(i-j);

                        }
                        break;
                    }

                }
            }
            return k;
        }
    }
}
