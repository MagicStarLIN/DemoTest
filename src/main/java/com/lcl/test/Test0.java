package com.lcl.test;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: nono
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019-08-27 16:51
 */
public class Test0 {
//    ^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$
//    /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/

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

    public static void main(String[] args) {
        String result = cancelHighLightContent("<div>\n" +
                "    <table border=\"0\">\n" +
                "        <tr>\n" +
                "            <td>生成日期：</td>\n" +
                "            <td> 2019-01-24 </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "    <div>\n" +
                "        <div>含山县人民武装部室外配电工程变电站采购项目（二次）流标报告 </div>\n" +
                "        <div>\n" +
                "            <div> <a class=\"bds_qzone\"></a> <a class=\"bds_tsina\"></a> <a class=\"bds_tqq\"></a> <a class=\"bds_renren\"></a>\n" +
                "                <a class=\"bds_t163\"></a> <a class=\"shareCount\"></a> </div>\n" +
                "        </div>\n" +
                "        <div>\n" +
                "            <p>马鞍山中振工程咨询有限公司受含山县含山县重点工程建设管理局的委托，2019年1月21日10时发布公告，对含山县人民武装部室外配电工程变电站采购项目（二次）进行询价，该项目因故流标。</p>\n" +
                "            <p> </p>\n" +
                "            <p><span style=\"background:red\">流</span><span style=\"background:red\">标</span><span style=\"background:red\">原</span><span style=\"background:red\">因</span><span style=\"background:red\">：</span><span style=\"background:red\">投</span><span style=\"background:red\">标</span><span style=\"background:red\">截</span><span style=\"background:red\">止</span><span style=\"background:red\">时</span><span style=\"background:red\">间</span><span style=\"background:red\">前</span><span style=\"background:red\">，</span><span style=\"background:red\">递</span><span style=\"background:red\">交</span><span style=\"background:red\">投</span><span style=\"background:red\">标</span><span style=\"background:red\">文</span><span style=\"background:red\">件</span><span style=\"background:red\">的</span><span style=\"background:red\">投</span><span style=\"background:red\">标</span><span style=\"background:red\">单</span><span style=\"background:red\">位</span><span style=\"background:red\">不</span><span style=\"background:red\">足</span><span style=\"background:red\">三</span><span style=\"background:red\">家</span><span style=\"background:red\">。</span></p>\n" +
                "            <p> </p>\n" +
                "            <p><span style=\"background:red\">特</span><span style=\"background:red\">此</span><span style=\"background:red\">公</span><span style=\"background:red\">告</span><span style=\"background:red\">。</span></p>\n" +
                "            <p> 马鞍山中振工程咨询有限公司</p>\n" +
                "            <p> </p>\n" +
                "            <p> 2019年1月24日</p>\n" +
                "            <p> </p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>","<span style=\"background:red\">流</span><span style=\"background:red\">标</span><span style=\"background:red\">原</span><span style=\"background:red\">因</span><span style=\"background:red\">：</span><span style=\"background:red\">投</span><span style=\"background:red\">标</span><span style=\"background:red\">截</span><span style=\"background:red\">止</span><span style=\"background:red\">时</span><span style=\"background:red\">间</span><span style=\"background:red\">前</span><span style=\"background:red\">，</span><span style=\"background:red\">递</span><span style=\"background:red\">交</span><span style=\"background:red\">投</span><span style=\"background:red\">标</span><span style=\"background:red\">文</span><span style=\"background:red\">件</span><span style=\"background:red\">的</span><span style=\"background:red\">投</span><span style=\"background:red\">标</span><span style=\"background:red\">单</span><span style=\"background:red\">位</span><span style=\"background:red\">不</span><span style=\"background:red\">足</span><span style=\"background:red\">三</span><span style=\"background:red\">家</span><span style=\"background:red\">。</span></p>\n" +
                "            <p> </p>\n" +
                "            <p><span style=\"background:red\">特</span><span style=\"background:red\">此</span><span style=\"background:red\">公</span><span style=\"background:red\">告</span><span style=\"background:red\">。</span>","流标原因：投标截止时间前，递交投标文件的投标单位不足三家。 特此公告。");

        System.err.println(result);
    }



}
