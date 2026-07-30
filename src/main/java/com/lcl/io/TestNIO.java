package com.lcl.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Demonstrates UTF-8 text IO using caller-supplied paths.
 */
public final class TestNIO {

    private TestNIO() {
    }

    public static String read(Path path) throws IOException {
        return Files.readString(path, StandardCharsets.UTF_8);
    }

    public static void write(Path path, String content) throws IOException {
        Files.writeString(path, content, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Usage: TestNIO <path>");
            return;
        }

        Path path = Path.of(args[0]);
        write(path, "学习 Java 21");
        System.out.println(read(path));
    }
}
