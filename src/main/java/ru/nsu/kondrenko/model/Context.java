package ru.nsu.kondrenko.model;

import java.awt.image.BufferedImage;

public interface Context {
    void setState(ContextState state);

    ContextState getState();

    void setImage(BufferedImage image);

    BufferedImage getImage();

    void addListener(ContextListener listener);

    void removeListener(ContextListener listener);
}
