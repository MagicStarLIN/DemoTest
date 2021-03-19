package com.lcl.tempJob;

import com.lcl.utils.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GreetingHandle {


    private static void doHandle() {
        try {
            List<String> bgInfos = FileUtils.readFromFile(FileUtils.FilePathPreFix + "bgInfos.txt", StandardCharsets.UTF_8.displayName());


            Set<String> resultInfos = new HashSet<>(bgInfos);
            File newFile = new File(FileUtils.FilePathPreFix + "finalBgInfos.txt");
            for (String resultInfo : resultInfos) {
                resultInfo += "\n";
                org.apache.commons.io.FileUtils.writeStringToFile(newFile, resultInfo, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    private static String handleTemplate(String template) {
//        d
//    }


    public static void main(String[] args) {
        doHandle();
    }
}
