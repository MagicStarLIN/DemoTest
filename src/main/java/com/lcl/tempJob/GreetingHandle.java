package com.lcl.tempJob;

import com.google.common.collect.Lists;
import com.lcl.utils.FileUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class GreetingHandle {


    private static void doHandle() {
        List<String> greetings = FileUtils.readFromFile(FileUtils.FilePathPreFix + "greetings.txt", StandardCharsets.UTF_8.displayName());
        List<String> sqls = Lists.newArrayList();
        String sql = "INSERT INTO `greeting_template`(`identity`, `template`, `demo`, `add_time`, `update_time`, `type`, `category`) VALUES (%s, '%s', '%s', now(), now(), 0, %s);";
        for (String greeting : greetings) {
            String[] sqlParams = greeting.split("\\*");
            String template = handleTemplate(sqlParams[0]);
            String readySql = String.format(sql, sqlParams[1], template, template.replace("${position}", "产品经理(示例职位)"), sqlParams[2]);
            sqls.add(readySql);
        }

        for (String s : sqls) {
            System.out.println(s);
        }

    }


    private static String handleTemplate(String template) {
        String stand = "${position}";
        if (template.contains("{职位名称}")) {
            template = template.replace("{职位名称}", stand);
        } else if (template.contains("{岗位名称}")) {
            template = template.replace("{岗位名称}", stand);
        }
        return template;
    }


    public static void main(String[] args) {
        doHandle();
    }
}
