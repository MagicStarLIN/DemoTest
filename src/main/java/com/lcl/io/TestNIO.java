package com.lcl.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: TestNIO
 * @Description: testNIO
 * @date 2019-08-19 14:49
 */
public class TestNIO {
    public static void testread() {
        //[1]获取通道
        try {
            FileInputStream fileInputStream = new FileInputStream("/Users/liuchanglin/Magic/test.txt");
            FileChannel fileChannel = fileInputStream.getChannel();
            //[2]创建缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            //[3]将数据从通道读到缓冲区
            int result = fileChannel.read(byteBuffer);
                byteBuffer.flip();
                System.out.println(Charset.forName("utf-8").decode(byteBuffer));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void testwrite() {
        try {
            //[1]获取通道
            FileOutputStream fileOutputStream = new FileOutputStream("/Users/liuchanglin/Magic/test.txt");
            FileChannel fileChannel = fileOutputStream.getChannel();
            //[2]获取缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            String message = "lcl";
            byte[] bytesmessage = message.getBytes("utf-8");
            for (int i = 0; i < bytesmessage.length; i++) {
                byteBuffer.put(bytesmessage[i]);
            }
            byteBuffer.flip();
            //[3]写入到通道
            fileChannel.write(byteBuffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testwrite();
    }
}
