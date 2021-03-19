package com.lcl.test;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test
 * @date 2019/11/14 8:42 下午
 */
public class Test {
    volatile String lcl = "lcl";

    private static String path = "/Users/admin/tempFile";
    private static void testMethod() {
         int i = 1;
        int[] arr = {1, 2, 3, 4};
//        changeArr(arr);
        System.err.println(Arrays.toString(arr));
    }
    private static void changeArr(int i) {
//        arr = {4, 3, 2, 1};
        i = 2;
    }
//
//    private static void testMethod2() {
//        Student student = new Student("lcl", "1", 0);
//        changeStudent();
//        System.err.println(student.toString());
//    }

    private static String changeStudent(String str) {
        String s = "您的视频简历" + str + "已经发送给boss 播放视频附件";
        return s.substring(16 + str.length());
    }

    public static void main(String[] args) {
        File newFile = new File(path +"/12312"+ "/lcl.txt");

        try {
            FileUtils.copyInputStreamToFile(new FileInputStream(path + "/txtEdit.txt"), newFile);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static File copyFile(InputStream inputStream, File destFile) throws IOException {
        if (null == inputStream) {
            return null;
        }
        if (!destFile.exists()) {
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdir();
            }
            destFile.createNewFile();
        }
        FileUtils.copyInputStreamToFile(inputStream, destFile);
        return destFile;
    }

    private static int compareDate(Date d1, Date d2) {
        long dif = d1.getTime() - d2.getTime();

        return Integer.parseInt(String.valueOf(dif));
    }

    private static long modLong(long temp) {
        return temp % 10;
    }

}