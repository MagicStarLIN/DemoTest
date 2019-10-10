package com.lcl.Crawler;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: CrawlerDemo
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/10/10 10:28 上午
 */
public class CrawlerDemo {
    public static void jsonpList(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("div.list-hd > h4 > a");
            for (Element element : elements) {
                String d_url = element.attr("href");

                String d_title = element.ownText();

                System.err.println("详情页链接："+d_url+" ,详情页标题："+d_title);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void httpClientList(String url) {
        try {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity, "UTF-8");
                if (body != null) {
                    Pattern p = Pattern.compile("\\t|\\r|\\n");
                    Matcher m = p.matcher(body);
                    body = m.replaceAll("");
                    Pattern pattern = Pattern
                            .compile("<div class=\"list-hd\">\\s* <h4>\\s* <a href=\"(.*?)\"\\s* target=\"_blank\">(.*?)</a>\\s* </h4>\\s* </div>");
                    Matcher matcher = pattern.matcher(body);

                    while (matcher.find()) {
                        System.err.println("详情页链接：" + matcher.group(1) + " ,详情页标题：" + matcher.group(2));
                    }
                } else {
                    System.err.println("获取正文内容为空");
                }
            } else {
                System.err.println("处理失败，返回状态码为"+ response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        jsonpList("https://voice.hupu.com/nba");

        httpClientList("https://voice.hupu.com/nba");
    }
}
