package com.lcl.test;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test7
 * @date 2019/9/8 8:43 下午
 */
public class Test7 {

    String INSERT_COLUMNS = " identity, template, demo, type, add_time, category ";

    private static final String ALL_COLUMNS = "id, identity, template, demo, add_time, update_time, type, category";


    //    public static void main(String[] args) {
//        String filePath = "/Users/admin/tempFile/pro_greeting_template.csv";
//
//        String sql = "INSERT INTO `greeting_template`(" + ALL_COLUMNS + ") VALUES (%s, %s, '%s', '%s', '%s', '%s', %s, %s);";
//
//        List<String> greetings = FileUtils.readFromFile(filePath, StandardCharsets.UTF_8.name());
//
//        for (String greeting : greetings) {
//
//            String[] param = greeting.split(",");
//            String finalSql = String.format(sql, param[0], param[1], param[2], param[3], param[4], param[5], param[6], param[7]);
//            System.out.println(finalSql);
//
//        }
//
//    }
    private static final int ROWS_PER_TABLE = 50000000;


    private static int getTableIndexNew(long msgId) {
        int oldIndex = (int) (msgId / ROWS_PER_TABLE);
        int offset = (int) (msgId & 0x3);
        return (oldIndex & ~0x3) + offset;
    }


    public static void main(String[] args) {
        System.out.println(getTableIndexNew(240203483236L));
    }

    private static final String GREETING_NAME_MATCH = "<span class=\"blue\" contenteditable=\"false\">#访谈对象</span>";


    private static final String VARIABLE_REG_STR = "\\$\\{([\\w.]+)\\}";


}
