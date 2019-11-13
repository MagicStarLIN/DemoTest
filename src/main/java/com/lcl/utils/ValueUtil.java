package com.lcl.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: ValueUtil
 * @Description: 线性求值
 * @date 2019/10/28 4:39 下午
 */
public class ValueUtil {
    /**
     * @Title LinearEvaluation
     * @Description 向量线性求值
     * @Author liuchanglin
     * @Date 2019/10/28 4:58 下午
     * @Param [vector1, vector2, lambda]
     * @return int[]
     **/
    public static int[] LinearEvaluation(int[] vector1,int[] vector2,double lambda) {
        int[] result = new int[vector1.length];

        for (int i = 0; i < vector1.length; i++) {
            vector1[i] = (int) Math.round (vector1[i] * lambda);
        }
        for (int i = 0; i < vector2.length; i++) {
            vector2[i] = (int) Math.round(vector2[i] * (1-lambda));
        }
        for (int i = 0; i < vector1.length; i++) {

            result[i] = vector1[i] + vector2[i];
        }
        return result;
    }
    /**
     * @Title LinearEvaluation
     * @Description 单一值向量线性求值
     * @Author liuchanglin
     * @Date 2019/10/28 5:29 下午
     * @Param [value1, value2, lambda]
     * @return int
     **/
    public static int SinggleLinearEvaluation(int value1, int value2, float lambda) {
        return Math.round (lambda * value1 + (1 - lambda) * value2);
    }

    public static void checkField(String labels) {
        Map<String, Integer> label = (Map<String, Integer>) JsonUtils.jsonToObj(labels, HashMap.class);
        for (Map.Entry<String, Integer> entry : label.entrySet()) {
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }
    }
    /**
     * @Title compareMap3
     * @Description 以第一个map为标准作为对比，将俩个map对比，使用时应当将两个参数调换位置使用两次做&&运算
     * @Author liuchanglin
     * @Date 2019/11/13 10:47 上午
     * @Param [m1, m2]
     * @return boolean
     **/
    public static boolean compareMap3(Map<String, Integer> m1,Map<String, Integer> m2) {
//        Map<String, String> m1 = new HashMap<String, String>();
//        Map<String, String> m2 = new HashMap<String, String>();

        for (Map.Entry<String, Integer> entry1 : m1.entrySet()) {
//            Integer m1value = entry1.getValue() == null ? 0 : entry1.getValue();
            Integer m1value = m1.get(entry1.getKey());
            Integer m2value = m2.get(entry1.getKey());
            if (m1value != m2value) {//若两个map中相同key对应的value不相等
                return false;
            }
        }
        return true;
    }

    /**
     * {
     *   "建材":1,
     *   "电子产品":0
     * }
     *
     *

     * @param args
     */


        public static void main(String[] args) {
            Map<String, Integer> label1 = (Map<String, Integer>) JsonUtils.jsonToObj("    {\n" +
                    "        \"建材\":1,\n" +
                    "        \"电子产品\":0\n" +
                    "    }", HashMap.class);

            Map<String, Integer> label2 = (Map<String, Integer>) JsonUtils.jsonToObj("{\n" +
                    "  \"建材\":1,\n" +
                    "  \"电子产品\":0,\n" +
                    "  \"母婴产品\":0\n" +
                    "}", HashMap.class);

            System.err.println(compareMap3(label1,label2));

        }

}
