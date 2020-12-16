package com.lcl.test;

import com.google.common.base.Joiner;
import com.lcl.utils.CommonUtil;
import com.lcl.utils.HttpClientUtil;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.File;
import java.util.*;

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



    public static String httpsSendBossHiMsg(String url, List<String> toUsersEmails, String title, String content) {
        Map<String, Object> params = new HashMap<>();
        params.put("users", Joiner.on(",").join(toUsersEmails));
        params.put("content", content);
        params.put("tname", title);
        params.put("sbiz", "ops");
        return HttpClientUtil.postRequest(url, params);
    }

    public static void main(String[] args) {
//        System.out.println(httpsSendBossHiMsg("https://ops.weizhipin.com/api/v1/send_bosshi/",
//                Lists.newArrayList("liuchanglin@kanzhun.com"), "测试！", "123"));
        System.out.println(DateFormatUtils.format(getBeforeTodayWithStep(30),"yyyy-MM-dd HH:mm:ss"));
    }
}
