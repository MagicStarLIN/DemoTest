package com.lcl.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.lcl.utils.DateUtils;
import com.lcl.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

/**
 * @ClassName RongLianAXTest
 * @Description
 * @Date 2021/4/8 3:43 下午
 * @Author liuchanglin
 * @Version 1.0
 **/
@Slf4j
public class RongLianAXTest {

    private static final String bindUrl = "http://115.29.163.144:4002/2013-12-26/Accounts/8aaf070864b08c210164b5960997039f/nme/axn/std/set?sid=";

    private static final String unbindUrl = "http://115.29.163.144:4002/2013-12-26/Accounts/8aaf070864b08c210164b5960997039f/nme/axn/std/release";

    private static final String updateUrl = "http://115.29.163.144:4002/2013-12-26/Accounts/8aaf070864b08c210164b5960997039f/nme/axn/std/update";

    private static final String queryUrl = "http://115.29.163.144:4002/2013-12-26/Accounts/8aaf070864b08c210164b5960997039f/nme/axn/std/query?sid=";

    private static final String telA = "18618123199";

    private static final String telX = "18466194723";

    private static final String APP_ID = "7aaf07085f28f414015f2974b03f115g";

    private static final String TOKEN = "525f79d369964dde9d776956ffc75c18";

    private static String bindAXPhone() {
        Map<String, Object> headers = Maps.newHashMap();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json;charset=utf-8");
        headers.put("Authorization", getAuthorization());


        Map<String, Object> param = Maps.newHashMap();
        param.put("appId", APP_ID);
        param.put("aNumber", telA);
        param.put("xNumber", telX);
        param.put("mappingDuration", "20");
        param.put("callNotifyUrl", "https://diaoyong.weizhipin.com/wapi/zpvcall/callback/ronglian/ax/callin");
        param.put("cdrNotifyUrl", "https://diaoyong.weizhipin.com/wapi/zpvcall/callback/ronglian/ax/finish");

        System.out.println(headers.toString());
        System.out.println(param.toString());

        String json = JSON.toJSONString(param);
        String url = bindUrl + getSid();
        System.out.println(url);
        return HttpClientUtil.postRequestJson(url, json, headers);

    }

    private static String queryAXPhone(String mappingId) {
        Map<String, Object> headers = Maps.newHashMap();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json;charset=utf-8");
        headers.put("Authorization", getAuthorization());


        Map<String, Object> param = Maps.newHashMap();
        param.put("appId", APP_ID);
        param.put("mappingId", mappingId);

        System.out.println(headers.toString());
        System.out.println(param.toString());

        String json = JSON.toJSONString(param);
        String url = queryUrl + getSid();
        System.out.println(url);
        return HttpClientUtil.postRequestJson(url, json, headers);

    }

    private static boolean unbindAXPhone(String mappingId) {
        try {
            Map<String, Object> param = Maps.newHashMap();
            param.put("appId", APP_ID);
            param.put("mappingId", mappingId);
            String json = JSON.toJSONString(param);
            String respBody = HttpClientUtil.postRequestJson(unbindUrl, json,null);
            System.out.println(json);
            System.out.println("--------------------");
            System.out.println(respBody);
            JSONObject jsonObject = JSONObject.parseObject(respBody);
            if (jsonObject == null) {
                log.warn("RongLianVCallApi unbindTelX response is empty, mappingId={}, paramsMap={}, respBody={}",
                        mappingId, param, respBody);
                return false;
            }
            String statusCode = jsonObject.getString("statusCode");

            if ("000000".equals(statusCode)) {
                return true;
            }
            log.warn("RongLianVCallApi unbindTelX fail, mappingId={}, paramsMap={}, respBody={}", mappingId, param, respBody);
        } catch (Exception e) {
            log.error("RongLianVCallApi unbindTelX error mappingId={}", mappingId, e);
        }
        return false;
    }

    private static boolean updateBindAXPhone(String mappingId) {
        try {
            Map<String, Object> param = Maps.newHashMap();
            param.put("appId", APP_ID);
            param.put("mappingId", mappingId);
            param.put("mappingDuration", "60");
            String json = JSON.toJSONString(param);
            String respBody = HttpClientUtil.postRequestJson(updateUrl, json,null);
//            System.out.println(json);
//            System.out.println("--------------------");
            System.out.println(respBody);
            JSONObject jsonObject = JSONObject.parseObject(respBody);
            if (jsonObject == null) {
                log.warn("RongLianVCallApi unbindTelX response is empty, mappingId={}, paramsMap={}, respBody={}",
                        mappingId, param, respBody);
                return false;
            }
            String statusCode = jsonObject.getString("statusCode");

            if ("000000".equals(statusCode)) {
                return true;
            }
            log.warn("RongLianVCallApi unbindTelX fail, mappingId={}, paramsMap={}, respBody={}", mappingId, param, respBody);
        } catch (Exception e) {
            log.error("RongLianVCallApi unbindTelX error mappingId={}", mappingId, e);
        }
        return false;
    }

    private static String getAuthorization() {
        String originalInput = APP_ID + ":" + DateUtils.getDate10();
        String result = Base64.getEncoder().encodeToString(originalInput.getBytes());
        return result;
    }

    private static String getSid() {
        String originalSid = APP_ID + TOKEN + DateUtils.getDate10();
        return DigestUtils.md5DigestAsHex(originalSid.getBytes(StandardCharsets.UTF_8)).toUpperCase();
    }

    public static void main(String[] args) {
//        String json = "{\n" +
//                "    \"appId\": 213342,\n" +
//                "    \"testCode\":111\n" +
//                "}";
//        System.out.println(HttpClientUtil.postRequestJson("http://localhost:16680/zpvcall/tianrun/callback/ax/callin?appId=2123", json, null));
//        System.out.println(bindAXPhone());
//        2021-04-26 16:58:22
//          2021-04-26 16:59:06
//        System.out.println(updateBindAXPhone("7054c870a66e11ebbe6a21508a6a0694"));

        System.out.println(queryAXPhone("7054c870a66e11ebbe6a21508a6a0694"));
    }



}
