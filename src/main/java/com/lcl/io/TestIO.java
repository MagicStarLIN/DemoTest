package com.lcl.io;

import java.io.File;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: TestIO
 * @Description: IO
 * @date 2019-08-19 14:04
 */
public class TestIO {
    public static void main(String[] args) {
        File file = new File("/Users/liuchanglin/Magic");
        String[] list;
        list = file.list();
        for (String s : list) {
            System.out.println(s);
        }
    }
}
