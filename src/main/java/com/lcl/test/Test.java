package com.lcl.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Date;

/**
 * Miscellaneous language and file-copy demonstrations.
 */
public class Test {
    volatile String lcl = "lcl";

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

//    private static void testMethod2() {
//        Student student = new Student("lcl", "1", 0);
//        changeStudent();
//        System.err.println(student.toString());
//    }

    private static String changeStudent(String str) {
        String s = "您的视频简历" + str + "已经发送给boss 播放视频附件";
        return s.substring(16 + str.length());
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: Test <source-file> <destination-file>");
            return;
        }

        Path source = Path.of(args[0]);
        Path destination = Path.of(args[1]);
        Path parent = destination.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
    }

    private static int compareDate(Date d1, Date d2) {
        long dif = d1.getTime() - d2.getTime();

        return Integer.parseInt(String.valueOf(dif));
    }

    private static long modLong(long temp) {
        return temp % 10;
    }
}
