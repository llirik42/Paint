package ru.nsu.kondrenko.model;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class ImageUtils {
    private ImageUtils() {

    }

    public static BufferedImage createWhiteBufferedImage(int width, int height) {
        final BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        final Graphics2D graphics = (Graphics2D)result.getGraphics();
        graphics.setPaint(Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        return result;
    }

    public static BufferedImage increaseBufferedImage(int newWidth, int newHeight, BufferedImage oldImage) {
        final int oldWidth = oldImage.getWidth();
        final int oldHeight = oldImage.getHeight();

        final BufferedImage result = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

        // TODO: refactor
        for (int x = 0; x < oldWidth; x++) {
            for (int y = 0; y < oldHeight; y++) {
                result.setRGB(x, y, oldImage.getRGB(x, y));
            }
        }

        for (int x = oldWidth; x < newWidth; x++) {
            for (int y = 0; y < newHeight; y++) {
                result.setRGB(x, y, Color.white.getRGB());
            }
        }

        for (int y = oldHeight; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                result.setRGB(x, y, Color.white.getRGB());
            }

        }

        return result;
    }

    public static void drawPolygon(BufferedImage image, Color color, int x, int y, int n, int radius, int rotationDeg) {
        final Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        final double radians = Math.PI * rotationDeg / 180;
        final Polygon polygon = new Polygon();

        for (int i = 0; i < n; i++) {
            final double angle = i * 2 * Math.PI / n + radians;
            polygon.addPoint((int) (x + radius * Math.cos(angle)), (int) (y + radius * Math.sin(angle)));
        }

        final Color oldColor = graphics2D.getColor();
        graphics2D.setColor(color);
        graphics2D.drawPolygon(polygon);
        graphics2D.setColor(oldColor);
    }

    public static BufferedImage drawStar(BufferedImage image, Color color, int x, int y, int n, int radius, int rotationDeg) {
        return image;

//        final Graphics2D graphics2D = (Graphics2D)image.getGraphics();
//
//        final int[] xPoints = new int[n];
//        final int[] yPoints = new int[n];
//
//        for (int i = 0; i < n; i++) {
//            final double x = Math.cos(i*((2*Math.PI)/radius))*radius[i % 4];
//            final double y = Math.sin(i*((2*Math.PI)/radius))*radius[i % 4];
//
//            xPoints[i] = (int) x + x1;
//            yPoints[i] = (int) y + y1;
//        }
//
//        g.setColor(Color.WHITE);
//        g.fillPolygon(X, Y, nPoints);
//
//
//
//        gr
    }

    public static BufferedImage drawLine(BufferedImage image, int thickness, int x1, int y1, int x2, int y2) {
        return thickness == 1 ? drawLineByAlgorithm(image, x1, y1, x2, y2) : drawLineByStandardLibrary(image, thickness, x1, y1, x2, y2);
    }

    private static BufferedImage drawLineByStandardLibrary(BufferedImage image, int thickness, int x1, int y1, int x2, int y2) {
        return image;
    }

    private static BufferedImage drawLineByAlgorithm(BufferedImage image, int x1, int y1, int x2, int y2) {
        return image;
    }
}
