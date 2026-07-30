package com.lcl.Socket.nioSocket;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReactorTaskTest {

    @Test
    void constructionBindsOnceWithoutStartingRecursiveThreads() throws Exception {
        try (ReactorTask reactor = new ReactorTask(new InetSocketAddress("localhost", 0))) {
            assertTrue(reactor.getLocalAddress().getPort() > 0);
        }
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void closeWakesBlockedSelectorWithoutUncaughtFailure() throws Exception {
        AtomicReference<Throwable> uncaughtFailure = new AtomicReference<>();
        Thread runner;

        try (ReactorTask reactor = new ReactorTask(new InetSocketAddress("localhost", 0))) {
            runner = new Thread(reactor, "reactor-test-runner");
            runner.setUncaughtExceptionHandler((thread, failure) -> uncaughtFailure.set(failure));
            runner.start();
            try {
                assertTrue(awaitBlockedSelect(runner), "runner did not block in selector");

                reactor.close();
                runner.join(1_000);

                assertFalse(runner.isAlive(), "close did not stop the selector loop");
                assertNull(uncaughtFailure.get(), "normal close caused an uncaught failure");
            } finally {
                reactor.close();
                runner.interrupt();
                runner.join(1_000);
            }
        }
    }

    private static boolean awaitBlockedSelect(Thread runner) {
        long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(1);
        while (runner.isAlive() && System.nanoTime() < deadline) {
            if (Arrays.stream(runner.getStackTrace())
                    .anyMatch(frame -> frame.getMethodName().equals("select"))) {
                return true;
            }
            Thread.onSpinWait();
        }
        return false;
    }
}
