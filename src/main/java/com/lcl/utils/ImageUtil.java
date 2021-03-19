package com.lcl.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 图片工具类
 */
public class ImageUtil {

//    /**
//     * 裁剪PNG图片工具类
//     *
//     * @param fromFile
//     *            源文件
//     * @param toFile
//     *            裁剪后的文件
//     * @param outputWidth
//     *            裁剪宽度
//     * @param outputHeight
//     *            裁剪高度
//     * @param proportion
//     *            是否是等比缩放
//     */
//    public static void resizePng(File fromFile, File toFile, int outputWidth, int outputHeight,
//            boolean proportion) {
//        try {
//            BufferedImage bi2 = ImageIO.read(fromFile);
//            int newWidth;
//            int newHeight;
//            // 判断是否是等比缩放
//            if (proportion) {
//                // 为等比缩放计算输出的图片宽度及高度
//                double rate1 = ((double) bi2.getWidth(null)) / (double) outputWidth + 0.1;
//                double rate2 = ((double) bi2.getHeight(null)) / (double) outputHeight + 0.1;
//                // 根据缩放比率大的进行缩放控制
//                double rate = rate1 < rate2 ? rate1 : rate2;
//                newWidth = (int) (((double) bi2.getWidth(null)) / rate);
//                newHeight = (int) (((double) bi2.getHeight(null)) / rate);
//            } else {
//                newWidth = outputWidth; // 输出的图片宽度
//                newHeight = outputHeight; // 输出的图片高度
//            }
//            BufferedImage to = new BufferedImage(120, 120, BufferedImage.TYPE_INT_RGB);
//            Graphics2D g2d = to.createGraphics();
//            to = g2d.getDeviceConfiguration().createCompatibleImage(newWidth, newHeight,
//                    Transparency.TRANSLUCENT);
//            g2d.dispose();
//            g2d = to.createGraphics();
//            @SuppressWarnings("static-access")
//            Image from = bi2.getScaledInstance(newWidth, newHeight, bi2.SCALE_AREA_AVERAGING);
//            g2d.drawImage(from, 0, 0, null);
//            g2d.dispose();
//            ImageIO.write(to, "png", toFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 测试
//     */
//    public static void main(String[] args) throws Exception {
//        String img1Path = "/Users/admin/tempFile/IMG_1088.JPG";
//        String img2Path = "/Users/admin/tempFile/IMG_1363.JPG";
//        String img3Path = "/Users/admin/tempFile/resultFile.png";
//        File fromFile = new File(img1Path);
//        File toFile = new File(img3Path);
//        resizePng(fromFile, toFile, 10, 10, true);
//    }


    /**
     * 纯绘制图形，其背景是黑色的
     *
     * @param args
     * @throws IOException
     */
    public void drawImage() throws IOException {
        int width = 256;
        int height = 256;
        //创建BufferedImage对象
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        // 获取Graphics2D
        Graphics2D g2d = bi.createGraphics();

        // 画图BasicStroke是JDK中提供的一个基本的画笔类,我们对他设置画笔的粗细，就可以在drawPanel上任意画出自己想要的图形了。
        g2d.setColor(new Color(255, 0, 0));
        g2d.setStroke(new BasicStroke(1f));
        g2d.fillRect(128, 128, width, height);

        // 释放对象
        g2d.dispose();

        // 保存文件
        ImageIO.write(bi, "png", new File("H:/test.png"));
    }

    /**
     * 绘制图形，把自己绘制的图形设置为透明或半透明，背景并不透明   前景透明，背景依然是黑色
     *
     * @param args
     * @throws IOException
     */
    public static void drawImage1() throws IOException {
        int width = 256;
        int height = 256;
        //创建BufferedImage对象
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取Graphics2D
        Graphics2D g2d = bi.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1.0f));// 1.0f为透明度 ，值从0-1.0，依次变得不透明

        // 画图BasicStroke是JDK中提供的一个基本的画笔类,我们对他设置画笔的粗细，就可以在drawPanel上任意画出自己想要的图形了。
        g2d.setColor(new Color(255, 0, 0));
        g2d.setStroke(new BasicStroke(1f));
        g2d.fillRect(128, 128, width, height);

        // 释放对象 透明度设置结束
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        g2d.dispose();

        // 保存文件
        ImageIO.write(bi, "png", new File("/Users/admin/tempFile/resultFile.png"));
    }

    /**
     * 绘制透明图形
     *
     * @param args
     * @throws IOException
     */
    public static void drawTransparent() throws IOException {
        int width = 256;
        int height = 256;
        //创建BufferedImage对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取Graphics2D
        Graphics2D g2d = image.createGraphics();

        // 增加下面代码使得背景透明
        image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g2d.dispose();
        g2d = image.createGraphics();
        // 背景透明代码结束

        // 画图BasicStroke是JDK中提供的一个基本的画笔类,我们对他设置画笔的粗细，就可以在drawPanel上任意画出自己想要的图形了。
//        g2d.setColor(new Color(255, 0, 0));
        g2d.setStroke(new BasicStroke(1f));
        g2d.fillRect(width, height, width, height);

        // 释放对象
        g2d.dispose();
        String img1Path = "/Users/admin/tempFile/IMG_1088.JPG";
        BufferedImage images = ImageIO.read(new File(img1Path));

        Graphics2D g = image.createGraphics();

        g.drawImage(images, 0, 0, width / 2 - 1, height, null);

        // 保存文件
        ImageIO.write(image, "png", new File("/Users/admin/tempFile/resultFile.png"));
    }

    public static void main(String[] args) throws IOException {
//		drawTransparent();
        drawTransparent();

    }


}


