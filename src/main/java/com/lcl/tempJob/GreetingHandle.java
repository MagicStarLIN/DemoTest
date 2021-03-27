package com.lcl.tempJob;

import com.google.common.collect.Maps;
import com.lcl.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
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

    private static Map<Long, String> getResumeMailBossId(String path) {
        if (StringUtils.isBlank(path)) {
            return Collections.EMPTY_MAP;
        }
        File file = new File(path);
        if (!file.exists() || !file.canRead()) {
            log.error("getResumeMailBossId failure, cause=FILE_NOT_OPERATE");
            return Collections.EMPTY_MAP;
        }

        Map<Long, String> infoMap = Maps.newHashMap();
        LineIterator lineIter = null;
        try {
            lineIter = org.apache.commons.io.FileUtils.lineIterator(file, StandardCharsets.UTF_8.toString());
            while (lineIter.hasNext()) {
                String line = lineIter.nextLine();
                //userId userIdentity userSource friendId friendSource
                if (StringUtils.isBlank(line)) {
                    log.warn("getResumeMailBossId line illegal line={}", line);
                }
                String[] info = line.split(" ");
                infoMap.put(NumberUtils.toLong(info[0]), info[1]);
            }
        } catch (Exception e) {
            log.error("getResumeMailBossId error", e);
        } finally {
            if (lineIter != null) {
                lineIter.close();
            }
        }
        return infoMap;
    }


//    private static String handleTemplate(String template) {
//        d
//    }


    public static void main(String[] args) {
        getResumeMailBossId("/Users/admin/tempFile/uniview_boss_idname.txt");
    }
}
