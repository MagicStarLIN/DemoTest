package com.lcl.utils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * File-reading utilities.
 */
public final class FileUtils {

    private FileUtils() {
    }

    public static List<String> readFromFile(Path path, Charset charset) {
        try {
            return Files.readAllLines(path, charset);
        } catch (IOException exception) {
            throw new UncheckedIOException("Unable to read file: " + path, exception);
        }
    }
}
