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
    public static void setCookies(String url) {
        try {
            Document document = Jsoup.connect(url)
                    // 手动设置cookies
                    .header("Cookie","bid=Dk2WuQKkLlo; ll=\"108288\"; __yadk_uid=kF3DpKHvfvGXp7If6ngFAZuIoxnyOiLz; douban-profile-remind=1; __utmv=30149280.15310; douban-fav-remind=1; __utmz=30149280.1570783328.4.4.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; push_doumail_num=0; ps=y; __utmc=30149280; ck=_SuZ; push_noty_num=1; _pk_ref.100001.8cb4=%5B%22%22%2C%22%22%2C1570863425%2C%22https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3D6H1XHgeLTlErvKLX8Ey-l1rDe7_qZ9VjXWtHkhjJd6RXBdVMAswZxRtxyf9gMA-t%26wd%3D%26eqid%3Db8391f7c000b3cd9000000035da0405c%22%5D; _pk_ses.100001.8cb4=*; __utma=30149280.2006208173.1557913858.1570851464.1570863426.6; __utmt=1; _pk_id.100001.8cb4=66eeb942d76785de.1557913881.5.1570863430.1570851586.; __utmb=30149280.2.10.1570863426; dbcl2=\"153101069:QpzJHgvPFhE\"")
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
    public static void jsoupLogin(String loginUrl,String userInfoUrl)  throws IOException {

        // 构造登陆参数
        Map<String,String> data = new HashMap<>();
        data.put("name","751969316@qq.com");
        data.put("password","liuchanglin0529");
        data.put("remember","false");
        data.put("ticket","");
        data.put("ck","");
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
    public static void main(String[] args) throws IOException {
//        setCookies("https://www.douban.com/");
        // 个人中心url
        String user_info_url = "https://www.douban.com/";

        // 登陆接口
        String login_url = "https://accounts.douban.com/j/mobile/login/basic";

        jsoupLogin(login_url,user_info_url);

    }
}
