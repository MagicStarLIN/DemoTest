package com.lcl.jerryMouse.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: DateUtils
 * @date 2025/4/6 00:56
 */
public class DateUtils {

    static final ZoneId GMT = ZoneId.of("Z");

    public static long parseDateTimeGMT(String s) {
        ZonedDateTime zdt = ZonedDateTime.parse(s, DateTimeFormatter.RFC_1123_DATE_TIME);
        return zdt.toInstant().toEpochMilli();
    }

    public static String formatDateTimeGMT(long ts) {
        ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(ts), GMT);
        return zdt.format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }
}