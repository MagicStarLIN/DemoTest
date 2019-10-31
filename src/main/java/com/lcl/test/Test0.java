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
    public static void main(String[] args) {
//        boolean flag = match("^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$", "1302301998072227123");
        boolean flag2 = checkBankCard("6212263500044678593");
        System.err.println(flag2);
    }
    private static boolean match(String regex, String str) {
        return Pattern.compile(regex).matcher(str).matches();
    }

    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    private static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId
                .substring(0, cardId.length() - 1));
        return cardId.charAt(cardId.length() - 1) == bit;
    }
    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
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
}
