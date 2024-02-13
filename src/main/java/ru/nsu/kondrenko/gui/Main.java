package ru.nsu.kondrenko.gui;

import ru.nsu.kondrenko.gui.controller.ButtonsController;
import ru.nsu.kondrenko.gui.controller.FilesActionsController;
import ru.nsu.kondrenko.gui.controller.FrameResizingController;
import ru.nsu.kondrenko.gui.controller.MouseController;
import ru.nsu.kondrenko.gui.view.View;
import ru.nsu.kondrenko.model.Context;
import ru.nsu.kondrenko.model.ContextImpl;
import ru.nsu.kondrenko.model.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    private static final String WINDOW_TITLE = "Paint";
    private static final int MIN_FRAME_WIDTH = 640;
    private static final int MIN_FRAME_HEIGHT = 480;

    public static void main(String[] args) {
        try {
            final BufferedImage startImage = ImageUtils.createWhiteBufferedImage(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT);
            final Context context = new ContextImpl();
            context.setImage(startImage);
            context.setNumberOfSides(5);
            context.setRadius(150);
            context.setColor(Color.GREEN);
            context.setRotation(-15);

            final ButtonsController buttonsController = new ButtonsController(context);
            final FilesActionsController filesActionsController = new FilesActionsController(context);
            final MouseController mouseController = new MouseController(context);
            final FrameResizingController frameResizingController = new FrameResizingController(context);

            final View view = new View(
                    WINDOW_TITLE,
                    MIN_FRAME_WIDTH,
                    MIN_FRAME_HEIGHT,
                    startImage,
                    buttonsController,
                    filesActionsController,
                    mouseController,
                    frameResizingController
            );

            context.addListener(view);
            view.show();
        } catch (IOException exception) {
            System.err.printf("Cannot init view: %s%n", exception.getLocalizedMessage());
        }
    }
}
