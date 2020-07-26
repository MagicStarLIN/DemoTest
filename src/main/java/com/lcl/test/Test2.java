package com.lcl.test;

import com.lcl.entity.LxrMore;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test2
 * @date 2019-08-13 09:21
 */
public class Test2 {
    public static void main(String[] args) {
        LxrMore lxrMore = new LxrMore();
        lxrMore.setFax("028-86272358(办公室)办公811");
        System.err.println(lxrMore.toString());

    }

    public static String trimLine(String str) {
        char[] value = str.toCharArray();
        int len = value.length;
        int st = 0;
        char[] val = str.toCharArray();    /* avoid getfield opcode */

        while ((st < len) && (val[st] <= '-')) {
            st++;
        }
        while ((st < len) && (val[len - 1] <= '-')) {
            len--;
        }
        return ((st > 0) || (len < value.length)) ? str.substring(st, len) : str;
    }
}
