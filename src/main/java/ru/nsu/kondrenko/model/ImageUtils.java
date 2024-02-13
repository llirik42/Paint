package ru.nsu.kondrenko.model;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class ImageUtils {
    private ImageUtils() {

    }

    public static BufferedImage createBlankImage(int width, int height) {
        final BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        final Graphics2D graphics = (Graphics2D) result.getGraphics();
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
}
