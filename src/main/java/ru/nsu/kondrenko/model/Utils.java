package ru.nsu.kondrenko.model;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class Utils {
    private Utils() {

    }

    public static BufferedImage createWhiteBufferedImage(int width, int height) {
        final BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                result.setRGB(x, y, Color.white.getRGB());
            }
        }

        return result;
    }

    public static BufferedImage resizeBufferedImage(int newWidth, int newHeight, BufferedImage oldImage) {
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