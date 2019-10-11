package com.lcl.Crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * @ClassName: LoginSimulate
 * @Description: 爬虫模拟登陆
 * @author liuchanglin
 * @date 2019/10/11 4:51 下午
 * @version 1.0
 */
public class LoginSimulate {
    public static void setCookies(String url) {
        try {
            Document document = Jsoup.connect(url)
                    .header("Cookie","dbcl2=\"153101069:cf6iv1kE4vY\"; __utma=30149280.162791458.1570021115.1570272827.1570783292.4; __utmb=30149280.1.10.1570783292; __utmc=30149280; __utmv=30149280.15310; __utmz=30149280.1570783292.4.3.utmcsr=baidu.com|utmccn=(referral)|utmcmd=referral|utmcct=/; _pk_id.100001.8cb4=4e4d83debeb0fa9b.1570021113.4.1570783291.1570272846.; _pk_ref.100001.8cb4=%5B%22%22%2C%22%22%2C1570783291%2C%22https%3A%2F%2Fbaidu.com%2F%22%5D; _pk_ses.100001.8cb4=*; _vwo_uuid_v2=DA96C0E07239BF22315DFD1D467DAF357|e6df34685dcdafd1e9f16cb0f8148287; push_doumail_num=0; push_noty_num=0; ll=\"108288\"; viewed=\"10470970_27591386_1230413_3852290_26612779\"; bid=s2bhiNilQJQ")
                    .get();
            if (document != null) {
                Element element = document.select("#db-global-nav > div > div.top-nav-info > ul > li.nav-user-account > a > span:nth-child(1)").first();
                if (element == null) {
                    System.out.println("未找到标签");
                    return;
                }
                String username = element.ownText();
                System.err.println("豆瓣名称为：" + username);
            } else {
                System.err.println("error");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        setCookies("https://www.douban.com/");
    }
}
