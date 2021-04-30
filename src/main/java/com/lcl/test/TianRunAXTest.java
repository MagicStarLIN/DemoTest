package com.lcl.test;

import com.lcl.utils.HttpClientUtil;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TianRunAXTest
 * @Description
 * @Date 2021/4/8 3:52 下午
 * @Author liuchanglin
 * @Version 1.0
 **/
public class TianRunAXTest {

    private static final String bindUrl = "http://vnc.vlink.cn/interface/v3/axb/outtransfer";

    private static final String telA = "18618123199";

    private static final String telX = "13241440720";

    private static final String APP_ID = "5601121";

    private static final String TOKEN = "c4efaea38add89eb";

    private static String bindAXPhone() {
        long timestamp = System.currentTimeMillis() / 1000;
        String sign = getSign(timestamp);
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("appId", APP_ID);
        paramsMap.put("timestamp", timestamp);
        paramsMap.put("sign", sign);
        paramsMap.put("telX", telX);
        paramsMap.put("transferms", telA);
        paramsMap.put("transfercalldisplay", 0);
        paramsMap.put("expiration", 180);
//        paramsMap.put("callrecording", 1);
        paramsMap.put("transfervoicecode", 1);
        //自定义参数
        System.out.println(paramsMap.toString());
        return HttpClientUtil.postRequest(bindUrl, paramsMap);
    }

    private static String queryAXPhone() {
        String queryUrl = "http://vnc.vlink.cn/interface/v3/axb/queryBindNumber?appId=%s&timestamp=%s&sign=%s&telA=%s&telX=%s";
        long timestamp = System.currentTimeMillis() / 1000;
        String sign = getSign(timestamp);

        String url = String.format(queryUrl, APP_ID, timestamp, sign, telA, telX);
        System.out.println(url);
        return HttpClientUtil.getRequest(url, null);
    }

    private static String getSign(long timestamp) {
        return DigestUtils.md5DigestAsHex((APP_ID + TOKEN + timestamp).getBytes()).toLowerCase();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(bindAXPhone());


    }

}
