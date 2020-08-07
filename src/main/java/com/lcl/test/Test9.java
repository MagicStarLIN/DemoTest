package com.lcl.test;

import java.util.ArrayList;

public class Test9 {

    public static void main(String[] args) {
        Test();
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
