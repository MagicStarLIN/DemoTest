package com.lcl.test;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test2
 * @date 2019-08-13 09:21
 */
public class Test2 {
    public static void main(String[] args) {
        System.err.println(getTextMessageResumeName("814简历.pdf"));
        System.err.println(getTextMessageResumeName("814作品集.pdf"));

    }


    public static String getTextMessageResumeName(String resumeName) {
        resumeName = StringUtils.substringBeforeLast(resumeName, ".");
        if (StringUtils.isNotEmpty(resumeName)) {
            int len = getLengthWithChineseDouble(resumeName);
            if (len > 6) {
                return " " + subStringWithChineseDouble(resumeName, 5) + "... ";
            }
            return " " + resumeName + " ";
        }
        return resumeName;
    }

    public static String subStringWithChineseDouble(String context, int length) {
        if (StringUtils.isEmpty(context)) {
            return "";
        } else if (getLengthWithChineseDouble(context) <= length) {
            return context;
        } else {
            String var1 = StringUtils.substring(context, 0, length * 2);
            char[] chars = var1.toCharArray();
            int len = 0;
            int index = 0;

            for (int i = 0; i < chars.length; ++i) {
                if (getChineseLength(CharUtils.toString(chars[i])) > 0) {
                    len += 2;
                } else {
                    ++len;
                }

                int dif = length * 2 - len;
                if (dif < 2) {
                    if (dif == 0) {
                        index = i;
                    } else if (i + 1 < chars.length && getChineseLength(CharUtils.toString(chars[i + 1])) > 0) {
                        index = i;
                    } else {
                        index = i + 1;
                    }
                    break;
                }
            }

            return StringUtils.substring(context, 0, index + 1);
        }
    }

    public static int getLengthWithChineseDouble(String context) {
        if (StringUtils.isEmpty(context)) {
            return 0;
        } else {
            int chineseCount = getChineseLength(context);
            int charLength = StringUtils.length(context) + chineseCount;
            return (int) Math.ceil((double) charLength / 2.0D) * 2;
        }
    }

    private static Pattern CHINESE_PATTERN = Pattern.compile("[\\u4E00-\\u9FA5]+");

    public static int getChineseLength(String context) {
        Matcher matcher = CHINESE_PATTERN.matcher(context);

        int resultLength;
        for (resultLength = 0; matcher.find(); resultLength += matcher.group().length()) {
        }

        return resultLength;
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
