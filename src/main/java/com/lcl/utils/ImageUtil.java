package com.lcl.utils;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Transparency;
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
        BufferedImage image = new BufferedImage(IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        try {
            graphics.setColor(Color.RED);
            graphics.setStroke(new BasicStroke(1f));
            graphics.fillRect(128, 128, IMAGE_SIZE, IMAGE_SIZE);
        } finally {
            graphics.dispose();
        }
        writePng(image, output);
    }

    /**
     * Draws a red rectangle using an alpha composite.
     */
    public static void drawImage1(Path output) throws IOException {
        BufferedImage image = new BufferedImage(IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        try {
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1.0f));
            graphics.setColor(Color.RED);
            graphics.setStroke(new BasicStroke(1f));
            graphics.fillRect(128, 128, IMAGE_SIZE, IMAGE_SIZE);
        } finally {
            graphics.dispose();
        }
        writePng(image, output);
    }

    /**
     * Places the supplied source image on a transparent canvas.
     */
    public static void drawTransparent(Path input, Path output) throws IOException {
        BufferedImage source = readImage(input);
        BufferedImage transparentImage = newTransparentImage();
        Graphics2D graphics = transparentImage.createGraphics();
        try {
            graphics.drawImage(source, 0, 0, IMAGE_SIZE / 2 - 1, IMAGE_SIZE, null);
        } finally {
            graphics.dispose();
        }
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
        BufferedImage temporary = new BufferedImage(IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = temporary.createGraphics();
        try {
            return graphics.getDeviceConfiguration().createCompatibleImage(
                    IMAGE_SIZE, IMAGE_SIZE, Transparency.TRANSLUCENT);
        } finally {
            graphics.dispose();
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
