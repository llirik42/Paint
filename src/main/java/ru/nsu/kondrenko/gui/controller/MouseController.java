package ru.nsu.kondrenko.gui.controller;

import ru.nsu.kondrenko.model.Context;
import ru.nsu.kondrenko.model.ContextState;
import ru.nsu.kondrenko.model.ImageUtils;

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
        final ContextState state = context.getState();

        if (state != ContextState.DRAWING_LINE && state != ContextState.DRAWING_POLYGON && state != ContextState.DRAWING_STAR) {
            return;
        }

        if (state == ContextState.DRAWING_LINE) {
            handleDrawingLineState(mouseEvent);
        } else if (state == ContextState.DRAWING_POLYGON) {
            handleDrawingPolygonState(mouseEvent);
        } else {
            handleDrawingStarState(mouseEvent);
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

    private void drawLineToContext(MouseEvent mouseEvent) {
        final BufferedImage image = context.getImage();
        final Color color = context.getColor();
        final int thickness = context.getThickness();
        final int curX = mouseEvent.getX();
        final int curY = mouseEvent.getY();
        ImageUtils.drawLine(image, color, thickness, prevX, prevY, curX, curY);
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
        ImageUtils.drawPolygon(image, color, x, y, thickness, numberOfSides, radius, rotationDeg);
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
        ImageUtils.drawStar(image, color, x, y, thickness, numberOfSides, radius, rotationDeg);
    }
}
