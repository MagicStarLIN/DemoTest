package com.lcl.Crawler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CrawlerDemoTest {

    private static final String SECRET = "crawler-token-must-not-leak";
    private static final String MALFORMED_URL =
            "http://user:" + SECRET + "@[invalid/path?token=" + SECRET;

    @Test
    void jsonpFailureSanitizesTargetAndPreservesCause() {
        IllegalStateException failure = assertThrows(
                IllegalStateException.class, () -> CrawlerDemo.jsonpList(MALFORMED_URL));

        assertEquals("Crawler request failed", failure.getMessage());
        assertFalse(failure.getMessage().contains(SECRET));
        assertInstanceOf(IllegalArgumentException.class, failure.getCause());
    }

    @Test
    void httpClientFailureSanitizesTargetAndPreservesCause() {
        IllegalStateException failure = assertThrows(
                IllegalStateException.class, () -> CrawlerDemo.httpClientList(MALFORMED_URL));

        assertEquals("Crawler request failed", failure.getMessage());
        assertFalse(failure.getMessage().contains(SECRET));
        assertInstanceOf(IllegalArgumentException.class, failure.getCause());
    }
}
