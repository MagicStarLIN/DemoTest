package com.lcl.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lcl.utils.DateUtils;
import com.lcl.utils.HttpClientUtil;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName DongXinAXTest
 * @Description
 * @Date 2021/4/7 4:28 下午
 * @Author liuchanglin
 * @Version 1.0
 **/
public class DongXinAXTest {

    private static final String bindUrl = "http://spn.caihcom.com:9101/spn/secure/v2/ax/mode101";

    private static final String telA = "18618123199";

    private static final String telX = "18518049455";

    private static String bindAXPhone() {
        Map<String, Object> headers = Maps.newHashMap();
        headers.put("Accept", "application/json;charset=utf-8");
        headers.put("Content-Type", "application/json;charset=utf-8");
        headers.put("appkey", "HPAXT_0001");
        headers.put("ts", DateUtils.getDate13());


        Map<String, Object> param = Maps.newHashMap();
        String requestId = String.valueOf(DateUtils.getDate8());
        param.put("requestId", requestId);
//        System.out.println(requestId);
        param.put("telA", DongXinAXTest.telA);
        param.put("telX", DongXinAXTest.telX);
        param.put("subts", DateUtils.getDate10());
        //彭靖琪 450804198608275870
        param.put("name", "张三三");
        param.put("cardtype", "0");
        param.put("cardno", "450804198608275870");
        param.put("areacode", "10");
        param.put("expiration", "180");

        String msgdgt = getMD5msgdgt(headers, param);
//        System.out.println(msgdgt);
        headers.put("msgdgt", msgdgt);
        System.out.println(headers.toString());
        System.out.println(param.toString());

        String json = JSON.toJSONString(param);
        return HttpClientUtil.postRequestJson(bindUrl, json, headers);

    }



    private static String getMD5msgdgt(Map<String, Object> header, Map<String, Object> param) {
        List<String> KVs = Lists.newArrayList();
//        KVs.add("iPhkmW8vQAFNy9hQ");
        for (Map.Entry<String, Object> entry : header.entrySet()) {
            if (entry.getKey().equals("appkey") || entry.getKey().equals("ts")) {
                String kv = entry.getKey() + entry.getValue();
                KVs.add(kv);
            }
        }
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            String kv = entry.getKey() + entry.getValue();
            KVs.add(kv);
        }
        String msgdgt = "iPhkmW8vQAFNy9hQ" + KVs.stream().sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.joining());
        System.out.println(msgdgt);
        return DigestUtils.md5DigestAsHex(msgdgt.getBytes(StandardCharsets.UTF_8)).toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(bindAXPhone());
    }



}
