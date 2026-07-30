package com.lcl.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@Slf4j
public class CommonUtil {

    private static final DateTimeFormatter YMD_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter HM_FORMATTER = DateTimeFormatter.ofPattern("HHmm");

    public static String formatFiveDigits(int value) {
        return String.format(Locale.ROOT, "%05d", value);
    }

    public static String formatYmd(Date date) {
        return YMD_FORMATTER.format(Objects.requireNonNull(date).toInstant().atZone(ZoneId.systemDefault()));
    }

    public static String formatHm(Date date) {
        return HM_FORMATTER.format(Objects.requireNonNull(date).toInstant().atZone(ZoneId.systemDefault()));
    }

    public static String getMathIP(String ip) {
        if (StringUtils.isNotBlank(ip)) {
            String[] split = ip.split("\\.");
            if (split.length == 4) {
                BigInteger one = new BigInteger(split[0]);
                BigInteger two = new BigInteger(split[1]);
                BigInteger three = new BigInteger(split[2]);
                BigInteger four = new BigInteger(split[3]);
                BigInteger newone = one.multiply(new BigInteger(String.valueOf(1 << 24)));
                BigInteger newtwo = two.multiply(new BigInteger(String.valueOf(1 << 16)));
                BigInteger newthree = three.multiply(new BigInteger(String.valueOf(1 << 8)));

                BigInteger ips = newone.add(newtwo).add(newthree).add(four);
                return ips.toString();
            }
        }
        return "";
    }

}
