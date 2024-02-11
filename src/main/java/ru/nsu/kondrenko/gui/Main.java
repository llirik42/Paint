package ru.nsu.kondrenko.gui;

import ru.nsu.kondrenko.gui.controller.Controller;
import ru.nsu.kondrenko.gui.view.View;
import ru.nsu.kondrenko.model.Context;
import ru.nsu.kondrenko.model.ContextImpl;
import ru.nsu.kondrenko.model.Utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    private static final int MIN_WIDTH = 640;
    private static final int MIN_HEIGHT = 480;

    public static void main(String[] args) {
        try {
            final BufferedImage image = Utils.createWhiteBufferedImage(MIN_WIDTH, MIN_HEIGHT);
            final Context context = new ContextImpl(image);

            final Controller controller = new Controller(context);

            final View view = new View("Paint", MIN_WIDTH, MIN_HEIGHT, controller, controller, controller);
            context.addListener(view);
            view.show();
        } catch (IOException exception) {
            System.err.printf("Cannot init view: %s%n", exception.getLocalizedMessage());
        }
    }
}
