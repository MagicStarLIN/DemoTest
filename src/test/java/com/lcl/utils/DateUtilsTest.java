package com.lcl.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

class DateUtilsTest {

    @Test
    void formatsEpochConsistentlyAcrossConcurrentCalls() throws Exception {
        String expected = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .format(Instant.EPOCH.atZone(ZoneId.systemDefault()));
        ExecutorService executor = Executors.newFixedThreadPool(12);
        try {
            List<Callable<String>> calls = new ArrayList<>();
            for (int i = 0; i < 1_000; i++) {
                calls.add(() -> DateUtils.getFormatDateStr(0L));
            }

            List<Future<String>> results = executor.invokeAll(calls, 10, TimeUnit.SECONDS);
            for (Future<String> result : results) {
                assertFalse(result.isCancelled());
                assertEquals(expected, result.get());
            }
        } finally {
            executor.shutdownNow();
            assertTrue(executor.awaitTermination(10, TimeUnit.SECONDS));
        }
    }
}
