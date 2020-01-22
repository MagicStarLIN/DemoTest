package com.lcl.test;

import com.lcl.entity.LxrMore;
import org.apache.commons.lang3.StringUtils;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Test2
 * @date 2019-08-13 09:21
 */
public class Test2 {
    public static void main(String[] args) {
        LxrMore lxrMore = new LxrMore();
        lxrMore.setFax("028-86272358(办公室)办公811");
        normalizedFaxPro(lxrMore);
        System.err.println(lxrMore.toString());

    }
    private static void normalizedPhonePro(LxrMore lxrMore) {
        String phone = lxrMore.getPhone();
        if (lxrMore.getPhone() != null) {
            lxrMore.setPhone(phone.replace("办公室", "")
                    .replace("办公", "")
                    .replace("(", "-")
                    .replace(")", "-")
                    .replace("（", "-")
                    .replace("）", "-")
                    .replace("：", "-")
                    .replace(":", "")
                    .replace("ext", "-")
                    .replace("Ext", "-")
                    .replace(".", "")
                    .replace("---", "-")
                    .replace("--", "-")
            );
            lxrMore.setPhone(trimLine(lxrMore.getPhone()));
        }

    }
    private static void normalizedMobilePro(LxrMore lxrMore) {
        String mobile = lxrMore.getMobile();
        if (lxrMore.getMobile() != null) {
            lxrMore.setMobile(mobile.replace("办公", "")
                    .replace("办公室", "")
                    .replace("(", "-")
                    .replace(")", "-")
                    .replace("（", "-")
                    .replace("）", "-")
                    .replace("：", "-")
                    .replace(":", "")
                    .replace("ext", "-")
                    .replace("Ext", "-")
                    .replace(".", "")
                    .replace("---", "-")
                    .replace("--", "-")
            );
            lxrMore.setMobile(trimLine(lxrMore.getMobile()));
        }

    }
    private static void normalizedFaxPro(LxrMore lxrMore) {
        String fax = lxrMore.getFax();
        if (lxrMore.getFax() != null) {
            lxrMore.setFax(fax.replace("办公室", "")
                    .replace("办公", "")
                    .replace("(", "-")
                    .replace(")", "-")
                    .replace("（", "-")
                    .replace("）", "-")
                    .replace("：", "-")
                    .replace(":", "")
                    .replace("ext", "-")
                    .replace("Ext", "-")
                    .replace(".", "")
                    .replace("---", "-")
                    .replace("--", "-")
            );
            lxrMore.setFax(trimLine(lxrMore.getFax()));
        }

    }
    public static String trimLine(String str) {
        char[] value = str.toCharArray();
        int len = value.length;
        int st = 0;
        char[] val = str.toCharArray();    /* avoid getfield opcode */

        while ((st < len) && (val[st] <= '-')) {
            st++;
        }
        while ((st < len) && (val[len - 1] <= '-')) {
            len--;
        }
        return ((st > 0) || (len < value.length)) ? str.substring(st, len) : str;
    }
}
