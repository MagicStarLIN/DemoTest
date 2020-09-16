package com.lcl.guava;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestGuava {


    private final static Cache<String, Integer> messageRecordCountCache = CacheBuilder
            .newBuilder()
            .expireAfterWrite(3, TimeUnit.SECONDS)
            .removalListener(RemovalListeners.asynchronous(new RemovalListener<String, Integer>() {
                @Override
                public void onRemoval(RemovalNotification<String, Integer> notification) {
                    if (notification.getCause() != RemovalCause.EXPIRED) {
                        return;
                    }
                    System.out.println("clean cache key = " + notification.getKey() +" value = "+ notification.getValue());
                }
            }, Executors.newFixedThreadPool(5)))

            .build();

    public static void main(String[] args) throws InterruptedException {
        messageRecordCountCache.put("key1", 1);
        messageRecordCountCache.put("key2", 2);
        messageRecordCountCache.put("key3", 3);
        messageRecordCountCache.put("key4", 4);

        System.out.println(messageRecordCountCache.asMap().toString());
        System.out.println("sleep start");
        Thread.sleep(7000);
        System.out.println(messageRecordCountCache.asMap().toString());
        messageRecordCountCache.cleanUp();

        Thread.sleep(7000);

        System.out.println("sleep over");




    }
    private static Map<String, Object> newActionData(int type, String name) {
        Map<String, Object> actionData = Maps.newLinkedHashMap();
        actionData.put("type", type);
        actionData.put("name", name);
        return actionData;
    }

    public static int getDate8(Date date) {
        if (date != null) {
            return NumberUtils.toInt(org.apache.commons.lang3.time.DateFormatUtils.format(date, "yyyyMMdd"));
        }
        return 0;
    }

    private static void test() {
        List<Map<String , Long>> list = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            Map<String, Long> map = Maps.newHashMap();
            map.put("date", (long) getDate8(new Date()) - i);
            map.put("count", 100000L);
            list.add(map);
        }

        System.out.println(JSON.toJSONString(list));
    }

}
