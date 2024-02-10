package ru.nsu.kondrenko.gui;

import ru.nsu.kondrenko.gui.controller.Controller;
import ru.nsu.kondrenko.model.ImageReadingException;
import ru.nsu.kondrenko.gui.view.View;
import ru.nsu.kondrenko.model.Context;
import ru.nsu.kondrenko.model.ContextImpl;

public class Main {
    private static final int MIN_WIDTH = 640;
    private static final int MIN_HEIGHT = 480;

    public static void main(String[] args) {
        Context context = new ContextImpl();

        Controller controller = new Controller(context);

        try {
            final View view = new View("Paint", MIN_WIDTH, MIN_HEIGHT, controller, controller);
            context.addListener(view);
            view.show();
        } catch (ImageReadingException exception) {
            System.err.println("Cannot init view: " + exception.getLocalizedMessage());
        }
    }
}
