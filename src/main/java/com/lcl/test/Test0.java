package com.lcl.test;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test0
 * @date 2019-08-27 16:51
 */
public class Test0 {
//    ^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$
//    /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/
    public static final int TEST_NUMBER = 1;
    static List<Integer> list = new ArrayList<>();

    private static void listAddData(List<Integer> list) {
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
    }

    private static String highLightContent(String content, String htmlForm, String keyword) {
        Character[] arr = Deweighting(keyword.trim().replaceAll(" ","").toCharArray());
        String newHtml = htmlForm;
        for (char c : arr) {
            String character = String.valueOf(c);
            if (htmlForm.contains(character)) {
                newHtml = newHtml.replace(character, "<span style=\"background:red\">" + character + "</span>");
            }
        }
        return content.replace(htmlForm,newHtml);
    }

    public static Character[] Deweighting(char[] arrStr) {
        List<Character> list = new ArrayList<Character>();
        for (int i=0; i<arrStr.length; i++) {
            if(!list.contains(arrStr[i])) {
                list.add(arrStr[i]);
            }
        }
        //返回一个包含所有对象的指定类型的数组
        Character[] newArrStr =  list.toArray(new Character[]{});
      return newArrStr;
    }
    public static String cancelHighLightContent(String content, String htmlForm, String keyword) {
        Character[] arr = Deweighting(keyword.trim().replaceAll(" ","").toCharArray());
        String newHtml = htmlForm;
        for (char c : arr) {
            String character = String.valueOf(c);
            if (htmlForm.contains(character)) {
                newHtml = newHtml.replace( "<span style=\"background:red\">" + character + "</span>",character);
            }
        }
        return content.replace(htmlForm,newHtml);
    }
    public static String cancelHighLightContentSameArea(String content, String keyword) {
//        String newKeyword = keyword.replace( ,keyword);
        return content.replace("<span style=\"background:red\">" + keyword + "</span>", keyword);
    }

    public static void main(String[] args) {
        String result = cancelHighLightContentSameArea("1231221341234<span style=\"background:red\">" + "1231231" + "</span>", "231");
        System.err.println(result);

    }

}
