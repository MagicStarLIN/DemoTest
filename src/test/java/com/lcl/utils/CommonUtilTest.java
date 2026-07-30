package com.lcl.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.junit.jupiter.api.Test;

class CommonUtilTest {

    @Test
    void formatsValuesWithoutSharedMutableFormatters() {
        Date epoch = new Date(0);
        assertEquals("00042", CommonUtil.formatFiveDigits(42));
        assertEquals(
                DateTimeFormatter.ofPattern("yyyyMMdd")
                        .format(Instant.EPOCH.atZone(ZoneId.systemDefault())),
                CommonUtil.formatYmd(epoch));
        assertEquals(
                DateTimeFormatter.ofPattern("HHmm")
                        .format(Instant.EPOCH.atZone(ZoneId.systemDefault())),
                CommonUtil.formatHm(epoch));
    }
}
