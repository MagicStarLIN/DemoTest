package com.lcl.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImageUtilTest {

    private static final byte[] PNG_SIGNATURE = {
            (byte) 0x89, 'P', 'N', 'G', '\r', '\n', 0x1a, '\n'
    };

    @TempDir
    Path tempDir;

    @Test
    void drawImageWritesPngToCallerSuppliedPath() throws IOException {
        Path output = tempDir.resolve("caller-selected-image.png");

        ImageUtil.drawImage(output);

        assertPngWithExpectedDimensions(output);
    }

    @Test
    void drawImage1WritesPngToCallerSuppliedPath() throws IOException {
        Path output = tempDir.resolve("caller-selected-alpha-image.png");

        ImageUtil.drawImage1(output);

        assertPngWithExpectedDimensions(output);
    }

    @Test
    void drawTransparentCreatesTransparentPngAtCallerSuppliedPath() throws IOException {
        Path input = tempDir.resolve("source.png");
        Path output = tempDir.resolve("transparent-result.png");
        BufferedImage source = new BufferedImage(2, 2, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                source.setRGB(x, y, 0xffff0000);
            }
        }
        assertTrue(ImageIO.write(source, "png", input.toFile()));

        ImageUtil.drawTransparent(input, output);

        BufferedImage result = readImage(output);
        assertEquals(256, result.getWidth());
        assertEquals(256, result.getHeight());
        assertTrue(result.getColorModel().hasAlpha());
        assertEquals(255, result.getRGB(0, 128) >>> 24);
        assertEquals(0, result.getRGB(255, 128) >>> 24);
    }

    @Test
    void drawTransparentRejectsInvalidInputAndReleasesSource() throws IOException {
        Path input = tempDir.resolve("not-an-image.txt");
        Path output = tempDir.resolve("must-not-exist.png");
        Files.writeString(input, "not an image", StandardCharsets.UTF_8);

        IOException failure =
                assertThrows(IOException.class, () -> ImageUtil.drawTransparent(input, output));

        assertTrue(failure.getMessage().contains("Unsupported image format"));
        assertFalse(Files.exists(output));
        Path movedInput = Files.move(input, tempDir.resolve("released-source.txt"));
        Files.writeString(movedInput, "source can be replaced", StandardCharsets.UTF_8);
        assertEquals(
                "source can be replaced",
                Files.readString(movedInput, StandardCharsets.UTF_8));
    }

    private static void assertPngWithExpectedDimensions(Path output) throws IOException {
        assertTrue(Files.isRegularFile(output));
        byte[] bytes = Files.readAllBytes(output);
        assertArrayEquals(PNG_SIGNATURE, Arrays.copyOf(bytes, PNG_SIGNATURE.length));
        BufferedImage image = readImage(output);
        assertEquals(256, image.getWidth());
        assertEquals(256, image.getHeight());
    }

    private static BufferedImage readImage(Path path) throws IOException {
        try (InputStream stream = Files.newInputStream(path)) {
            BufferedImage image = ImageIO.read(stream);
            assertNotNull(image);
            return image;
        }
    }
}
