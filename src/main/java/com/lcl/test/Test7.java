package com.lcl.test;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test7
 * @date 2019/9/8 8:43 下午
 */
public class Test7 {


    private static String params = "203 333 1023 3323";

    public static void main(String[] args) {

//        long friendId = 123412341L;
//        int friendSource = 1;
//        long expectId = 123412341L;
//        long jobId = 123412341L;
//
//        String s = String.format("bosszp://bosszhipin.app/openwith?" +
//                        "type=bossInviteVideoInterview&friendId=%s&friendSource=%s&expectId=%s&jobId=%s",
//                friendId, friendSource, expectId, jobId);

        System.out.println(DateFormatUtils.format(new Date(), "yyyyMMdd'T'HHmmss"));

    }


}
