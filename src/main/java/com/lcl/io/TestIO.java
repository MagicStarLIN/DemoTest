package com.lcl.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Demonstrates listing a caller-supplied directory.
 */
public final class TestIO {

    private TestIO() {
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Usage: TestIO <directory>");
            return;
        }

        try (Stream<Path> entries = Files.list(Path.of(args[0]))) {
            entries.map(Path::getFileName)
                    .forEach(System.out::println);
        }
    }
}
