package com.lcl.test;

import java.util.ArrayList;

public class Test9 {

    private static String welcome = "   ___   __  __ ___   ___   ____     \n  / _ \\ / / / // _ ) / _ ) / __ \\  \n / // // /_/ // _  |/ _  |/ /_/ /    \n/____/ \\____//____//____/ \\____/   \n";

    public static void main(String[] args) throws Exception {
        System.out.println(welcome);
    }

    private static void Test() {

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("lcl");
        arrayList.add("scl");
        arrayList.add("cod");
        arrayList.add("csgo");
        for (String s : arrayList.subList(0, 3)) {
            System.err.println(s);
        }
    }

}
