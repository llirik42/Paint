package ru.nsu.kondrenko.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ContextImpl implements Context {
    private final List<ContextListener> listeners = new ArrayList<>();

    private ContextTools tool;
    private ContextState state;
    private BufferedImage image;
    private Color color;
    private String errorMessage;
    private int thickness;
    private int radius;
    private int numberOfVertices;
    private int rotation;

    public ContextImpl(BufferedImage image, Color color, int thickness, int radius, int numberOfSides, int rotation) {
        this.tool = ContextTools.NONE;
        this.state = ContextState.IDLE;
        this.image = image;
        this.color = color;
        this.thickness = thickness;
        this.radius = radius;
        this.numberOfVertices = numberOfSides;
        this.rotation = rotation;
    }

    @Override
    public void setState(ContextState state) {
        this.state = state;

        for (final var it : listeners) {
            it.onContextStateChange(this);
        }
    }

    @Override
    public ContextState getState() {
        return state;
    }

    @Override
    public void setTool(ContextTools tool) {
        this.tool = tool;
    }

    @Override
    public ContextTools getTool() {
        return tool;
    }

    @Override
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public void addListener(ContextListener listener) {
        listeners.add(listener);
    }

    @Override
    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    @Override
    public int getThickness() {
        return thickness;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setNumberOfVertices(int numberOfSides) {
        this.numberOfVertices = numberOfSides;
    }

    @Override
    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    @Override
    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    @Override
    public int getRotation() {
        return rotation;
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
