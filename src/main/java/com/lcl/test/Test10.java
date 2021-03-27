package com.lcl.test;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test10 {
    public static final int STATUS_INTEREST = 1; //很感兴趣

    public static final int STATUS_REFUSE = 2; //委婉拒绝

    public static final int STATUS_INTERVIEW = 4; //请求面试

    public static final int STATUS_REMIND_TO_REPLY = 8; //提醒回复

    public static final int STATUS_EXCHANGE_CONTACT = 16; //请求交换通讯录

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static void main(String[] args) throws ParseException {
        int status = 0;

        status |= STATUS_EXCHANGE_CONTACT;
        System.out.println(status);
        status |= STATUS_INTEREST;
        System.out.println(status);

        System.out.println((status & STATUS_EXCHANGE_CONTACT) != 0);
        System.out.println((status & STATUS_REMIND_TO_REPLY) != 0);
        System.out.println((status & STATUS_REFUSE) != 0);
        System.out.println((status & STATUS_INTEREST) != 0);
        System.out.println(status);

        Date startDate = DateUtils.parseDate("2021-03-18", "yyyy-MM-dd");
        Date endDate = DateUtils.parseDate("2021-03-22 16:30", "yyyy-MM-dd HH:mm");

        System.out.println(SDF.format(startDate));
        System.out.println(SDF.format(endDate));

    }
}
