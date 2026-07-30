package com.lcl.Crawler;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: CrawlerDemo
 * @Description: 爬虫demo
 * @date 2019/10/10 10:28 上午
 */
public class CrawlerDemo {
    public static void jsonpList(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("div.list-hd > h4 > a");
            for (Element element : elements) {
                String detailUrl = element.attr("href");
                String detailTitle = element.ownText();
                System.err.println("详情页链接：" + detailUrl + " ,详情页标题：" + detailTitle);
            }
        } catch (IOException exception) {
            throw new IllegalStateException("Crawler request failed: " + url, exception);
        }
    }

    public static void httpClientList(String url) {
        HttpGet request = new HttpGet(url);
        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(request)) {
            if (response.getCode() == 200) {
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                if (body != null) {
                    Pattern whitespace = Pattern.compile("\\t|\\r|\\n");
                    Matcher whitespaceMatcher = whitespace.matcher(body);
                    body = whitespaceMatcher.replaceAll("");
                    Pattern itemPattern = Pattern.compile(
                            "<div class=\"list-hd\">\\s* <h4>\\s* "
                                    + "<a href=\"(.*?)\"\\s* target=\"_blank\">(.*?)</a>\\s* "
                                    + "</h4>\\s* </div>");
                    Matcher itemMatcher = itemPattern.matcher(body);
                    while (itemMatcher.find()) {
                        System.err.println(
                                "详情页链接：" + itemMatcher.group(1)
                                        + " ,详情页标题：" + itemMatcher.group(2));
                    }
                } else {
                    System.err.println("获取正文内容为空");
                }
            } else {
                System.err.println("处理失败，返回状态码为" + response.getCode());
            }
        } catch (Exception exception) {
            throw new IllegalStateException("Crawler request failed: " + url, exception);
        }
    }

    public static void main(String[] args) {
//        jsonpList("https://voice.hupu.com/nba");

        httpClientList("https://voice.hupu.com/nba");
    }
}
