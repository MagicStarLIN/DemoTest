package com.lcl.test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/11/14 8:42 下午
 */
public class Test {
    public static void main(String[] args) {
//        BigDecimal bigDecimal1 = new BigDecimal(0.15);
//        BigDecimal bigDecimal2 = new BigDecimal(3);
//        System.out.print(bigDecimal1.multiply(bigDecimal2).floatValue());
        Timestamp currentTime = new Timestamp(new Date().getTime());

        System.err.println(currentTime);
    }
}