package com.lcl.utils;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: CheckUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/10/31 9:29 上午
 */
public class CheckUtil {
    /**
     * @Title checkBankCard
     * @Description luhm算法检验银行卡号
     * @Author liuchanglin
     * @Date 2019/10/31 9:30 上午
     * @Param [cardId]
     * @return boolean
     **/
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId
                .substring(0, cardId.length() - 1));
        return cardId.charAt(cardId.length() - 1) == bit;
    }
    private static char getBankCardCheckCode(String nonCheckCodeCardId) {
        int cardLenth = nonCheckCodeCardId.trim().length();
        if (nonCheckCodeCardId == null || cardLenth == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            throw new IllegalArgumentException("不是银行卡的卡号!");
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    /**
     * @Title regexMatch
     * @Description 正则表达式检验
     * @Author liuchanglin
     * @Date 2019/10/31 9:31 上午
     * @Param [regex, str]
     * @return boolean
     **/
    public static boolean regexMatch(String regex, String str) {
        return Pattern.compile(regex).matcher(str).matches();
    }

    public static void main(String[] args) {
//        System.out.println(getTextMessageResumeName("814简历.pdf"));
        System.out.println(omitResumeName("814简历"));
        System.out.println(omitResumeName("814作品集.pdf"));
        System.out.println(omitResumeName("814作品集芜湖芜湖.pdf"));

    }

//    public static String omitResumeName(String resumeName) {
//
//    }


    private static String omitResumeName(String resumeName) {
        if (StringUtils.isBlank(resumeName)) {
            return "";
        }
        resumeName = StringUtils.substringBeforeLast(resumeName, ".");
        if (getLengthWithChineseDouble(resumeName) > 12) {
            resumeName = subStringWithChineseDoubleV2(resumeName, 5);
            return " " + resumeName + "... ";
        }
        return " " + resumeName + " ";
    }

    private static String subStringWithChineseDoubleV2(String context, int length) {
        String var1 = StringUtils.substring(context, 0, length * 2);
        char[] chars = var1.toCharArray();
        int len = 0;
        int index = 0;

        for (char c : chars) {
            if (getChineseLength(CharUtils.toString(c)) > 0) {
                len += 2;
            } else {
                len++;
            }
            int dif = length * 2 - len;
            if (dif < 2) {
                if (index+1 < chars.length && getChineseLength(CharUtils.toString(c)) < 0) {
                    index += 1;
                }
                break;
            }
            index++;
        }
        return StringUtils.substring(context, 0, index + 1);
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
                index = i;
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
            double a = (double) charLength / 2.0D;
            return (int) Math.ceil(a * 2);
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
