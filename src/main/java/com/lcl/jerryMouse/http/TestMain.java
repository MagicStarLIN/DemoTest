package com.lcl.jerryMouse.http;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: TestMain
 * @date 2025/3/19 01:13
 */
public class TestMain {

    public static void main(String[] args) {
        String host = "0.0.0.0";
        int port = 8080;

        try {
            SimpleHttpServer simpleHttpServer = new SimpleHttpServer(host, port);
            do {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
