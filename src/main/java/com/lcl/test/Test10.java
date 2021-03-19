package com.lcl.test;

public class Test10 {
    public static final int STATUS_INTEREST = 1; //很感兴趣

    public static final int STATUS_REFUSE = 2; //委婉拒绝

    public static final int STATUS_INTERVIEW = 4; //请求面试

    public static final int STATUS_REMIND_TO_REPLY = 8; //提醒回复

    public static final int STATUS_EXCHANGE_CONTACT = 16; //请求交换通讯录


    public static void main(String[] args) {
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
    }
}
