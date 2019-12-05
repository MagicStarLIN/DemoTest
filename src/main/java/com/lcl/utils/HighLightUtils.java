package com.lcl.utils;

import com.sun.org.apache.xpath.internal.compiler.Keywords;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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
     * @Description 非跨标签标记高亮
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
     * @Description 非跨标签取消标记高亮
     * @Author liuchanglin
     * @Date 2019/11/14 7:56 下午
     * @Param [content, keyword]
     * @return java.lang.String
     **/
    public static String cancelhighLightContentSameArea(String content, String keyword) {
//        String newKeyword = keyword.replace( ,keyword);
        return content.replace("<span style=\"background:red\">" + keyword + "</span>", keyword);
    }

    private static Element parseHtml(String content, String id) {
        Document document = Jsoup.parse(content);
        return document.getElementById(id);
    }



    private static String html = "<div id=\"1\"> 沂水县沂城街道社区卫生服务中心维生素C片交易公告 <p id=\"2\">发布时间：2018-06-19 17:20</p>\n" +
            "    <div id=\"3\">\n" +
            "        <div id=\"4\">医院名称：沂水县沂城街道社区卫生服务中心； 药品名称：维生素C片；采购量：300</div>\n" +
            "    </div>\n" +
            "</div>信息来源：http://www.ggzy.gov.cn/";

//    String content="<div>招标单位：12121212</div>";
//    Document doc = Jsoup.parse(content);
//    Elements divS = doc.getElementsByTag("DIV");
//        for (Element div : divS) {
//        String text = div.text();
//        text=text.replace("招标单位：","");
//        div.html("<span>招标单位：</span>"+text);
//    }

    private static String mark(String content, String startId, String endId, String[] keyWords, String color) {
        Document document = Jsoup.parse(html);
        int start = Integer.parseInt(startId);
        int end = Integer.parseInt(endId);
        int i = 0;
        for (int j = start; j <= end; j++) {
            String originText = parseHtml(content, String.valueOf(start)).text();
            for (int k = i ; k < keyWords.length ; k++) {
                if (originText.contains(keyWords[k])) {
                    content =
                            parseHtml(content,String.valueOf(j))
                                    .html(originText.replace(keyWords[i],"<span style=\"background:"+color+"\">" + keyWords[i] + "</span>"))
                            .ownerDocument().outerHtml();
                    i++;
                    break;
                }

            }
        }
        return content;
    }


    private static String[] keyWords = {"交易公告","发布时间：2018-06-19 17:20","医院名称"};

    public static void main(String[] args) {
        String result = mark(html, "1", "4", keyWords, "red");
        System.err.println(result);
//        Document document = Jsoup.parse(html).getElementById("4").text("123124").ownerDocument();
//        String afterDeal = document.outerHtml();
//        System.err.println(afterDeal);
    }
}
