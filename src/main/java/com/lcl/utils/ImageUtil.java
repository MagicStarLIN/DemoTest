package com.lcl.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;

/**
 * Image-drawing utilities that operate on caller-supplied paths.
 */
public final class ImageUtil {

    private static final int IMAGE_SIZE = 256;

    private ImageUtil() {
    }

    /**
     * Draws a red rectangle on a black background.
     */
    public static void drawImage(Path output) throws IOException {
        writePng(newRedRectangleImage(), output);
    }

    /**
     * Draws a red rectangle on a black background.
     */
    public static void drawImage1(Path output) throws IOException {
        writePng(newRedRectangleImage(), output);
    }

    /**
     * Places the supplied source image on a transparent canvas.
     */
    public static void drawTransparent(Path input, Path output) throws IOException {
        BufferedImage source = readImage(input);
        BufferedImage transparentImage = newTransparentImage();
        copyScaled(source, transparentImage, IMAGE_SIZE / 2 - 1, IMAGE_SIZE);
        writePng(transparentImage, output);
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: ImageUtil <input-image> <output-image>");
            return;
        }

        drawTransparent(Path.of(args[0]), Path.of(args[1]));
    }

    private static BufferedImage newTransparentImage() {
        return new BufferedImage(IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_ARGB);
    }

    private static BufferedImage newRedRectangleImage() {
        BufferedImage image = new BufferedImage(IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_RGB);
        for (int y = IMAGE_SIZE / 2; y < IMAGE_SIZE; y++) {
            for (int x = IMAGE_SIZE / 2; x < IMAGE_SIZE; x++) {
                image.setRGB(x, y, 0xffff0000);
            }
        }
        return image;
    }

    private static void copyScaled(
            BufferedImage source, BufferedImage destination, int width, int height) {
        // Match Graphics2D's nearest-neighbor/SrcOver result without initializing
        // a platform graphics device.
        for (int y = 0; y < height; y++) {
            int sourceY =
                    (int) ((2L * y + 1) * source.getHeight() / (2L * height));
            for (int x = 0; x < width; x++) {
                int sourceX =
                        (int) ((2L * x + 1) * source.getWidth() / (2L * width));
                int pixel = source.getRGB(sourceX, sourceY);
                destination.setRGB(x, y, pixel >>> 24 == 0 ? 0 : pixel);
            }
        }
    }

    private static BufferedImage readImage(Path input) throws IOException {
        try (InputStream stream = Files.newInputStream(input)) {
            BufferedImage image = ImageIO.read(stream);
            if (image == null) {
                throw new IOException("Unsupported image format: " + input);
            }
            return image;
        }
    }

    private static void writePng(BufferedImage image, Path output) throws IOException {
        try (OutputStream stream = Files.newOutputStream(output)) {
            if (!ImageIO.write(image, "png", stream)) {
                throw new IOException("No PNG image writer is available");
            }
        }
    }
}
