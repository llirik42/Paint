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

    public MouseController(Context context) {
        this.context = context;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        final ContextState state = context.getState();

        if (state != ContextState.DRAWING_LINE && state != ContextState.DRAWING_POLYGON && state != ContextState.DRAWING_STAR) {
            return;
        }

        switch (state) {
            case DRAWING_LINE:
                drawLineToContext(mouseEvent);
                break;
            case DRAWING_POLYGON:
                drawPolygonToContext(mouseEvent);
                break;
            case DRAWING_STAR:
                drawStarToContext(mouseEvent);
                break;
        }

        context.setState(ContextState.REPAINTING);
    }

    private void drawLineToContext(MouseEvent mouseEvent) {

    }

    private void drawPolygonToContext(MouseEvent mouseEvent) {
        final BufferedImage image = context.getImage();
        final Color color = context.getColor();
        final int numberOfSides = context.getNumberOfSides();
        final int radius = context.getRadius();
        final int rotationDeg = context.getRotation();
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        ImageUtils.drawPolygon(image, color, x, y, numberOfSides, radius, rotationDeg);
    }

    private void drawStarToContext(MouseEvent mouseEvent) {
        final BufferedImage image = context.getImage();
        final Color color = context.getColor();
        final int numberOfSides = context.getNumberOfSides();
        final int radius = context.getRadius();
        final int rotationDeg = context.getRotation();
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        ImageUtils.drawStar(image, color, x, y, numberOfSides, radius, rotationDeg);
    }
}
