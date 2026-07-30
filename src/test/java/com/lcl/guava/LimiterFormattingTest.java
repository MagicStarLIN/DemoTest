package com.lcl.guava;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LimiterFormattingTest {

    @Test
    void formatsTimestampsConsistentlyAcrossConcurrentCalls() throws Exception {
        Instant firstInstant = Instant.parse("2000-01-01T00:00:00Z");
        SimpleDateFormat referenceFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ExecutorService executor = Executors.newFixedThreadPool(8);
        try {
            List<Callable<String>> calls = new ArrayList<>();
            List<String> expectedResults = new ArrayList<>();
            for (int i = 0; i < 500; i++) {
                Instant instant = firstInstant.plusSeconds(i * 12_345L);
                expectedResults.add(referenceFormatter.format(Date.from(instant)));
                calls.add(() -> LimiterTest.formatTimestamp(instant));
            }

            List<Future<String>> results = executor.invokeAll(calls, 5, TimeUnit.SECONDS);
            for (int i = 0; i < results.size(); i++) {
                Future<String> result = results.get(i);
                assertFalse(result.isCancelled());
                assertEquals(expectedResults.get(i), result.get());
            }
        } finally {
            executor.shutdownNow();
            assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS));
        }
    }
}
