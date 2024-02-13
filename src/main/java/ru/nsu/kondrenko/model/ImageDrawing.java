package ru.nsu.kondrenko.model;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

public final class ImageDrawing {
    private ImageDrawing() {

    }

    public static void drawLine(BufferedImage image, Color color, int thickness, int x1, int y1, int x2, int y2) {
        drawThinLine(image, color, x1, y1, x2, y2);
        // TODO: add handling of thickness
    }

    public static void drawPolygon(BufferedImage image, Color color, int x, int y, int thickness, int n, int radius, int rotationDegrees) {
        drawThinPolygon(image, color, x, y, n, radius, rotationDegrees);
        // TODO: add handling of thickness
    }

    public static void drawStar(BufferedImage image, Color color, int x, int y, int thickness, int n, int radius, int rotationDegrees) {
        drawThinStar(image, color, x, y, n, radius, rotationDegrees);
        // TODO: add handling of thickness
    }

    public static void fill(BufferedImage image, Color color, int x, int y) {
        // TODO: implement
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
                error += 2 * dy;

                if (error > 0) {
                    error -= 2 * dx;
                    y += 1;
                }

                image.setRGB(x + x1, y, color.getRGB());
            }
        }
    }

    private static void drawThinPolygon(BufferedImage image, Color color, int x, int y, int n, int radius, int rotationDegrees) {
        final Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        final double radians = degreesToRadians(rotationDegrees);
        final Polygon polygon = new Polygon();

        for (int i = 0; i < n; i++) {
            final double angle = i * 2 * Math.PI / n + radians;
            polygon.addPoint((int) (x + radius * Math.cos(angle)), (int) (y + radius * Math.sin(angle)));
        }

        graphics2D.setColor(color);
        graphics2D.drawPolygon(polygon);
    }

    private static void drawThinStar(BufferedImage image, Color color, int x, int y, int n, int radius, int rotationDegrees) {
        final Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        final double radians = degreesToRadians(rotationDegrees);
        final Shape starShape = createStarShape(x, y, (double) radius / 2, radius, n, radians);
        graphics2D.setColor(color);
        graphics2D.draw(starShape);
    }

    private static Shape createStarShape(double centerX, double centerY, double innerRadius, double outerRadius, int n, double rotationDegrees) {
        final Path2D path = new Path2D.Double();

        final double deltaAngle = Math.PI / n;

        for (int i = 0; i < n * 2; i++) {
            final double angleRad = rotationDegrees + i * deltaAngle;

            double relX = Math.cos(angleRad);;
            double relY = Math.sin(angleRad);;

            if (i % 2 == 0)  {
                relX *= outerRadius;
                relY *= outerRadius;
            }
            else  {
                relX *= innerRadius;
                relY *= innerRadius;
            }
            if (i == 0)  {
                path.moveTo(centerX + relX, centerY + relY);
            }
            else  {
                path.lineTo(centerX + relX, centerY + relY);
            }
        }

        path.closePath();

        return path;
    }

    private static double degreesToRadians(int degrees) {
        return Math.PI * degrees / 180;
    }
}