package com.lcl.test;

import java.util.Scanner;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test8
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/9/11 3:23 下午
 */
public class Test8 {
    String[] numbers = {"壹","贰","叁","肆","伍","陆","柒","捌","玖","零"};
    String[] level = {"拾","佰","仟","万","亿"};
    String[] money = {"元","角","分","整"};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] element = sc.next().split(",");
        element[0] = element[0].substring(1);
        element[element.length-1] = element[element.length-1].replace("]","");
        for (int i = 0; i < element.length; i++) {
            element[i] = element[i].replaceAll("\"","");

        }
        String[] result = new String[element.length];
        for (int i = 0; i < result.length; i++) {

        }
    }

    private String handleNumbers(String numbs) {
        String[] index = numbs.split(".");
        String result = "";
        int level = index[0].length();
        if (level == 1) {
            if (index[0].equals("0")) {
                result = numbers[9];
            } else {
                int x = Integer.parseInt(index[0]);
                result = numbers[x - 1];
            }
        }
        return result;
    }
}
