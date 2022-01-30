package com.lcl.test;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test11 {

        public static void main(String[] args) {
            HashMap<String, Integer> hashMap = new HashMap<>(2);
            hashMap.put("1",1);
            hashMap.put("2",2);
            hashMap.put("3",3);

            System.out.println(Arrays.toString(hashMap.entrySet().toArray()));
        }


    public static String toString(Map map) {

        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = iter.next();
            sb.append(entry.getKey());
            sb.append('=').append('"');
            sb.append(entry.getValue());
            sb.append('"');
            if (iter.hasNext()) {
                sb.append(',').append(' ');
            }
        }
        return sb.toString();

    }

}
