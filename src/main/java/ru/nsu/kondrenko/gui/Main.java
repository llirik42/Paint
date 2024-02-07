package ru.nsu.kondrenko.gui;

import ru.nsu.kondrenko.gui.controller.Controller;
import ru.nsu.kondrenko.gui.view.View;

public class Main {
    private static final int MIN_WIDTH = 640;
    private static final int MIN_HEIGHT = 480;

    public static void main(String[] args) {
        Controller controller = new Controller();
        final View view = new View("Paint", MIN_WIDTH, MIN_HEIGHT, controller);
    }
}
