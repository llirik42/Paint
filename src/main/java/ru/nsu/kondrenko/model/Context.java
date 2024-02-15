package ru.nsu.kondrenko.model;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface Context {
    ContextState getState();

    void setState(ContextState state);

    ContextTools getTool();

    void setTool(ContextTools tool);

    BufferedImage getImage();

    void setImage(BufferedImage image);

    void addListener(ContextListener listener);

    int getThickness();

    void setThickness(int thickness);

    Color getColor();

    void setColor(Color color);

    int getNumberOfVertices();

    void setNumberOfVertices(int numberOfVertices);

    int getRadius();

    void setRadius(int radius);

    int getRotation();

    void setRotation(int rotation);

    String getErrorMessage();

    void setErrorMessage(String errorMessage);
}
