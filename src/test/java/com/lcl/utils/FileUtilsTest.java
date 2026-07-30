package com.lcl.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileUtilsTest {

    @TempDir
    Path tempDir;

    @Test
    void readsUtf8LinesFromTheCallerSuppliedPath() throws Exception {
        Path file = writeLines(tempDir.resolve("lines.txt"));

        assertEquals(List.of("第一行", "第二行"),
                FileUtils.readFromFile(file, StandardCharsets.UTF_8));
    }

    @Test
    void wrapsReadFailuresInUncheckedIOException() throws Exception {
        Path directory = Files.createDirectory(tempDir.resolve("directory"));

        assertThrows(UncheckedIOException.class,
                () -> FileUtils.readFromFile(directory, StandardCharsets.UTF_8));
    }

    private static Path writeLines(Path file) throws Exception {
        return Files.writeString(file, "第一行\n第二行\n", StandardCharsets.UTF_8);
    }
}
