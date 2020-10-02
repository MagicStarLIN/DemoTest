package com.lcl.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

@Slf4j
public class CommonUtil {

    public static final DecimalFormat DF_5 = new DecimalFormat("00000");
    public static final SimpleDateFormat SDF_YMD = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat SDF_HM = new SimpleDateFormat("HHmm");

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
