package com.lcl.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: HighLightUtils
 * @Description: 高亮utils
 * @date 2019/11/14 3:42 下午
 */
public class HighLightUtils {
    /**
     * @Title highLightContent
     * @Description 高亮
     * @Author liuchanglin
     * @Date 2019/11/14 3:44 下午
     * @Param [content, htmlForm, keyword]
     * @return java.lang.String
     **/
    public static String highLightContent(String content, String htmlForm, String keyword) {
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
    /**
     * @Title cancelHighLightContent
     * @Description 取消高亮
     * @Author liuchanglin
     * @Date 2019/11/14 3:48 下午
     * @Param [content, htmlForm, keyword]
     * @return java.lang.String
     **/
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

    /**
     * @Title Deweighting
     * @Description 字符数组去重 返回字符数组
     * @Author liuchanglin
     * @Date 2019/11/14 3:45 下午
     * @Param [arrStr]
     * @return java.lang.Character[]
     **/
    public static Character[] Deweighting(char[] arrStr) {
        List<Character> list = new ArrayList<Character>(arrStr.length);
        for (int i=0; i<arrStr.length; i++) {
            if(!list.contains(arrStr[i])) {
                list.add(arrStr[i]);
            }
        }
        //返回一个包含所有对象的指定类型的数组
        Character[] newArrStr =  list.toArray(new Character[]{});
        return newArrStr;
    }
    /**
     * @Title highLightContentSameArea
     * @Description 非跨域标记高亮
     * @Author liuchanglin
     * @Date 2019/11/14 7:56 下午
     * @Param [content, keyword]
     * @return java.lang.String
     **/
    public static String highLightContentSameArea(String content, String keyword) {
        String newKeyword = keyword.replace(keyword, "<span style=\"background:red\">" + keyword + "</span>");
        return content.replace(keyword, newKeyword);
    }
    /**
     * @Title cancelhighLightContentSameArea
     * @Description 非跨域取消标记高亮
     * @Author liuchanglin
     * @Date 2019/11/14 7:56 下午
     * @Param [content, keyword]
     * @return java.lang.String
     **/
    public static String cancelhighLightContentSameArea(String content, String keyword) {
//        String newKeyword = keyword.replace( ,keyword);
        return content.replace("<span style=\"background:red\">" + keyword + "</span>", keyword);
    }

    public static void main(String[] args) {
        System.err.println(cancelhighLightContentSameArea("1<span style=\"background:red\">2</span>3","2"));
    }
}
