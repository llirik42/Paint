package ru.nsu.kondrenko.model;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface Context {
    void setState(ContextState state);

    ContextState getState();

    void setImage(BufferedImage image);

    BufferedImage getImage();

    void addListener(ContextListener listener);

    void removeListener(ContextListener listener);

    void setThickness(int thickness);

    int getThickness();

    void setColor(Color color);

    Color getColor();

    void setNumberOfSides(int numberOfSides);

    int getNumberOfSides();

    void setRadius(int radius);

    int getRadius();

    void setRotation(int rotation);

    int getRotation();

    void setErrorMessage(String errorMessage);

    String getErrorMessage();
}
