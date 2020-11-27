package com.lcl.test;

import com.lcl.utils.CommonUtil;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test2
 * @date 2019-08-13 09:21
 */
public class Test2 {
    public static String getDateDirPath(Date date, String type) {
        String time = CommonUtil.SDF_HM.format(date);
        String time1 = time.substring(0, 3);
        int time2 = Integer.parseInt(time.substring(3, 4));
        if (0 <= time2 && time2 < 5) {
            time2 = 0;
        } else {
            time2 = 5;
        }
        return "BASE_PATH" + File.separator + type + File.separator + CommonUtil.SDF_YMD.format(date) + time1 + time2 + File.separator;
    }
    // TODO: 2020/11/25  date  hh-mm-ss.SSS 00:00:00.000
    private static Date getBeforeTodayWithStep(int timeStep) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, -timeStep);
        return calendar.getTime();
    }
    public static void main(String[] args) {
        System.out.println(DateFormatUtils.format(getBeforeTodayWithStep(530), "yyyy-MM-dd HH:mm:ss.SSS"));

    }
}
