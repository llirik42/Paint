package ru.nsu.kondrenko.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ContextImpl implements Context {
    private final List<ContextListener> listeners = new ArrayList<>();

    private BufferedImage image;
    private ContextState state;

    public ContextImpl(BufferedImage startImage) {
        image = startImage;
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
    public void removeListener(ContextListener listener) {
        listeners.remove(listener);
    }
}
