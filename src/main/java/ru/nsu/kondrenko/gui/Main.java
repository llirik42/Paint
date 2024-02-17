package ru.nsu.kondrenko.gui;

import ru.nsu.kondrenko.gui.controller.*;
import ru.nsu.kondrenko.gui.view.View;
import ru.nsu.kondrenko.model.context.Context;
import ru.nsu.kondrenko.model.context.ContextImpl;
import ru.nsu.kondrenko.model.image.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    private static final String WINDOW_TITLE = "Paint";
    private static final int MIN_FRAME_WIDTH = 640;
    private static final int MIN_FRAME_HEIGHT = 480;

    private static final BufferedImage START_IMAGE = ImageUtils.createBlankImage(
            MIN_FRAME_WIDTH,
            MIN_FRAME_HEIGHT
    );

    private static final Color START_COLOR = Color.BLACK;

    private static final int START_THICKNESS = 1;
    private static final int START_RADIUS = 100;
    private static final int START_NUMBER_OF_SIDES = 4;
    private static final int START_ROTATION = 0;

    public static void main(String[] args) {
        try {
            final Context context = new ContextImpl(
                    START_IMAGE,
                    START_COLOR,
                    START_THICKNESS,
                    START_RADIUS,
                    START_NUMBER_OF_SIDES,
                    START_ROTATION
            );

            final ImageReader imageReader = new ImageReaderImpl();
            final ImageSaver imageSaver = new ImageSaverImpl();

            final ButtonsController buttonsController = new ButtonsController(context);
            final FilesActionsController filesActionsController = new FilesActionsController(context, imageReader, imageSaver);
            final MouseController mouseController = new MouseController(context);
            final FrameResizingController frameResizingController = new FrameResizingController(context);
            final DialogWindowController dialogWindowController = new DialogWindowController();

            final int MIN_THICKNESS = 1;
            final int MAX_THICKNESS = 20;

            final int MIN_NUMBER_OF_VERTICES = 3;
            final int MAX_NUMBER_OF_VERTICES = 20;

            final int MIN_RADIUS = 1;
            final int MAX_RADIUS = 200;

            final int MIN_ROTATION = 0;
            final int MAX_ROTATION = 360;

            final View view = new View(
                    WINDOW_TITLE,
                    MIN_FRAME_WIDTH,
                    MIN_FRAME_HEIGHT,
                    START_IMAGE,
                    buttonsController,
                    filesActionsController,
                    mouseController,
                    frameResizingController,
                    dialogWindowController
            );

            context.addListener(view);
            view.show();
        } catch (IOException exception) {
            System.err.printf("Cannot init view: %s%n", exception.getLocalizedMessage());
        }
    }
}
