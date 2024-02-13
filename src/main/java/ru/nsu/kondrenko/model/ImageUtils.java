package ru.nsu.kondrenko.model;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

public final class ImageUtils {
    private ImageUtils() {

    }

    public static BufferedImage createBlankImage(int width, int height) {
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

    public static void drawPolygon(BufferedImage image, Color color, int x, int y, int thickness, int n, int radius, int rotationDeg) {
        final Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        final double radians = Math.PI * rotationDeg / 180;
        final Polygon polygon = new Polygon();

        for (int i = 0; i < n; i++) {
            final double angle = i * 2 * Math.PI / n + radians;
            polygon.addPoint((int) (x + radius * Math.cos(angle)), (int) (y + radius * Math.sin(angle)));
        }

        graphics2D.setColor(color);
        graphics2D.drawPolygon(polygon);
    }

    public static void drawStar(BufferedImage image, Color color, int x, int y, int thickness, int n, int radius, int rotationDeg) {
        final Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        final double radians = Math.PI * rotationDeg / 180;

        final Shape starShape = createStar(x, y, (double) radius / 2, radius, n, radians);

        graphics2D.setColor(color);
        graphics2D.draw(starShape);
    }

    public static void fill(BufferedImage image, Color color, int x, int y) {

    }

    public static void drawLine(BufferedImage image, Color color, int thickness, int x1, int y1, int x2, int y2) {
        drawThinLine(image, color, x1, y1, x2, y2);
    }

    private static void drawThinLine(BufferedImage image, Color color, int x1, int y1, int x2, int y2) {
        final int rgb = color.getRGB();

        if (x1 == x2) {
            final int minY = Integer.min(y1, y2);
            final int maxY = Integer.max(y1, y2);

            for (int y = minY; y <= maxY; y++) {
                image.setRGB(x1, y, rgb);
            }
        } else {
            final int dx = x2 - x1;
            final int dy = y2 - y1;

            int y = y1;
            int error = -dx;

            for (int x = 0; x <= dx; x++) {
                System.out.println(y);
                error += 2 * dy;

                if (error > 0) {
                    error -= 2 * dx;
                    y += 1;
                }

                image.setRGB(x + x1, y, color.getRGB());
            }
        }
    }

    private static Shape createStar(double centerX, double centerY,
                                    double innerRadius, double outerRadius, int numRays,
                                    double startAngleRad) {
        final Path2D path = new Path2D.Double();
        double deltaAngleRad = Math.PI / numRays;
        for (int i = 0; i < numRays * 2; i++)
        {
            double angleRad = startAngleRad + i * deltaAngleRad;
            double ca = Math.cos(angleRad);
            double sa = Math.sin(angleRad);
            double relX = ca;
            double relY = sa;
            if ((i & 1) == 0)
            {
                relX *= outerRadius;
                relY *= outerRadius;
            }
            else
            {
                relX *= innerRadius;
                relY *= innerRadius;
            }
            if (i == 0)
            {
                path.moveTo(centerX + relX, centerY + relY);
            }
            else
            {
                path.lineTo(centerX + relX, centerY + relY);
            }
        }
        path.closePath();
        return path;
    }
}
