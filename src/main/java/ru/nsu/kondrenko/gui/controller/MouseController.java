package ru.nsu.kondrenko.gui.controller;

import ru.nsu.kondrenko.model.Context;
import ru.nsu.kondrenko.model.ContextState;
import ru.nsu.kondrenko.model.ContextTools;
import ru.nsu.kondrenko.model.ImageDrawing;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MouseController extends MouseAdapter {
    private final Context context;

    boolean hasPreviousCoordinates;
    private int prevX;
    private int prevY;

    public MouseController(Context context) {
        this.context = context;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        final ContextTools tool = context.getTool();

        if (tool == ContextTools.NONE) {
            return;
        }

        if (tool == ContextTools.DRAW_LINE) {
            handleDrawingLineState(mouseEvent);
        } else if (tool == ContextTools.DRAW_POLYGON) {
            handleDrawingPolygonState(mouseEvent);
        } else if (tool == ContextTools.DRAW_STAR) {
            handleDrawingStarState(mouseEvent);
        } else {
            handleFilling(mouseEvent);
        }

        context.setState(ContextState.REPAINTING);
    }

    private void handleDrawingLineState(MouseEvent mouseEvent) {
        if (!hasPreviousCoordinates) {
            prevX = mouseEvent.getX();
            prevY = mouseEvent.getY();
        } else {
            drawLineToContext(mouseEvent);
        }
        hasPreviousCoordinates = !hasPreviousCoordinates;
    }

    private void handleDrawingPolygonState(MouseEvent mouseEvent) {
        drawPolygonToContext(mouseEvent);
    }

    private void handleDrawingStarState(MouseEvent mouseEvent) {
        drawStarToContext(mouseEvent);
    }

    private void handleFilling(MouseEvent mouseEvent) {
        fillInContext(mouseEvent);
    }

    private void drawLineToContext(MouseEvent mouseEvent) {
        final BufferedImage image = context.getImage();
        final Color color = context.getColor();
        final int thickness = context.getThickness();
        final int curX = mouseEvent.getX();
        final int curY = mouseEvent.getY();
        ImageDrawing.drawLine(image, color, thickness, prevX, prevY, curX, curY);
    }

    private void drawPolygonToContext(MouseEvent mouseEvent) {
        final BufferedImage image = context.getImage();
        final Color color = context.getColor();
        final int thickness = context.getThickness();
        final int numberOfSides = context.getNumberOfSides();
        final int radius = context.getRadius();
        final int rotationDeg = context.getRotation();
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        ImageDrawing.drawPolygon(image, color, x, y, thickness, numberOfSides, radius, rotationDeg);
    }

    private void drawStarToContext(MouseEvent mouseEvent) {
        final BufferedImage image = context.getImage();
        final Color color = context.getColor();
        final int thickness = context.getThickness();
        final int numberOfSides = context.getNumberOfSides();
        final int radius = context.getRadius();
        final int rotationDeg = context.getRotation();
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        ImageDrawing.drawStar(image, color, x, y, thickness, numberOfSides, radius, rotationDeg);
    }

    private void fillInContext(MouseEvent mouseEvent) {
        final BufferedImage image = context.getImage();
        final Color color = context.getColor();
        ImageDrawing.fill(image, color, mouseEvent.getX(), mouseEvent.getY());
    }
}
