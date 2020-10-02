package com.lcl.test;

import com.lcl.utils.CommonUtil;

import java.io.File;
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

    public static void main(String[] args) {
        String result = getDateDirPath(new Date(System.currentTimeMillis() + 5 * 60 * 1000), "exchange");
        System.err.println(result);
    }
}
