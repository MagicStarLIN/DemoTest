package com.lcl.Crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: LoginSimulate
 * @Description: 爬虫模拟登陆
 * @author liuchanglin
 * @date 2019/10/11 4:51 下午
 * @version 1.0
 */
public class LoginSimulate {
    /**
     * @Title setCookies
     * @Description
     * 手动设置 cookies
     * 先从网站上登录，然后查看 request headers 里面的 cookies
     * @Author liuchanglin
     * @Date 2019/10/12 2:54 下午
     * @Param [url]
     * @return void
     **/
    public static void setCookies(String url, String cookie) {
        try {
            Document document = Jsoup.connect(url)
                    // 手动设置cookies
                    .header("Cookie", cookie)
                    .get();
            if (document != null) {
                // 获取豆瓣昵称节点
                Element element = document.select("#db-global-nav > div > div.top-nav-info > ul > li.nav-user-account > a > span:nth-child(1)").first();
                if (element == null) {
                    System.out.println("未找到标签");
                    return;
                }
                // 取出豆瓣节点昵称
                String username = element.ownText();
                System.err.println("豆瓣名称为：" + username);
            } else {
                System.err.println("error");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @Title jsoupLogin
     * @Description
     * Jsoup 模拟登录豆瓣 访问个人中心
     * 在豆瓣登录时先输入一个错误的账号密码，查看到登录所需要的参数
     * 先构造登录请求参数，成功后获取到cookies
     * 设置request cookies，再次请求
     * @param loginUrl 登录url
     * @param userInfoUrl 个人中心url
     * @Author liuchanglin
     * @Date 2019/10/12 2:49 下午
     * @Param [loginUrl, userInfoUrl]
     * @return void
     **/
    public static void jsoupLogin(String loginUrl, String userInfoUrl, String username, String password)
            throws IOException {

        // 构造登陆参数
        Map<String, String> data = buildLoginData(username, password);
        Connection.Response login = Jsoup.connect(loginUrl)
                .ignoreContentType(true) // 忽略类型验证
                .followRedirects(false) // 禁止重定向
                .postDataCharset("utf-8")
                .header("Upgrade-Insecure-Requests","1")
                .header("Accept","application/json")
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("X-Requested-With","XMLHttpRequest")
                .header("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36")
                .data(data)
                .method(Connection.Method.POST)
                .execute();
        login.charset("UTF-8");
        // login 中已经获取到登录成功之后的cookies
        // 构造访问个人中心的请求
        Document document = Jsoup.connect(userInfoUrl)
                // 取出login对象里面的cookies
                .cookies(login.cookies())
                .get();
        if (document != null) {
            Element element = document.select("#db-global-nav > div > div.top-nav-info > ul > li.nav-user-account > a > span:nth-child(1)").first();
            if (element == null) {
                System.out.println("没有找到 .info h1 标签");
                return;
            }
            String userName = element.ownText();
            System.out.println("豆瓣我的网名为：" + userName);
        } else {
            System.out.println("出错啦！！！！！");
        }
    }

    public static Map<String, String> buildLoginData(String username, String password) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Username and password must not be blank");
        }

        Map<String, String> data = new HashMap<>();
        data.put("name", username);
        data.put("password", password);
        data.put("remember", "false");
        data.put("ticket", "");
        data.put("ck", "");
        return data;
    }

    public static void main(String[] args) throws IOException {
        // 个人中心url
        String user_info_url = "https://www.douban.com/";

        // 登陆接口
        String login_url = "https://accounts.douban.com/j/mobile/login/basic";
        String username = System.getenv("DOUBAN_LOGIN_NAME");
        String password = System.getenv("DOUBAN_LOGIN_PASSWORD");
        String cookie = System.getenv("DOUBAN_COOKIE");

        if (cookie != null && !cookie.isBlank()) {
            setCookies(user_info_url, cookie);
            return;
        }

        jsoupLogin(login_url, user_info_url, username, password);

    }
}
