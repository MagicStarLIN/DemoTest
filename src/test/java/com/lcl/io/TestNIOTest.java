package com.lcl.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class TestNIOTest {

    @TempDir
    Path tempDir;

    @Test
    void writesAndReadsUtf8TextAtTheCallerSuppliedPath() throws Exception {
        Path file = tempDir.resolve("demo.txt");

        TestNIO.write(file, "学习 Java 21");

        assertEquals("学习 Java 21", TestNIO.read(file));
    }
}
