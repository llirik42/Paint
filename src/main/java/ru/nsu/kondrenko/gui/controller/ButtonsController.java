package ru.nsu.kondrenko.gui.controller;

import ru.nsu.kondrenko.gui.ActionCommands;
import ru.nsu.kondrenko.model.Context;
import ru.nsu.kondrenko.model.ContextState;
import ru.nsu.kondrenko.model.ContextTools;
import ru.nsu.kondrenko.model.ImageUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ButtonsController implements ActionListener {
    private final Map<String, Runnable> actionCommandsMap = new HashMap<>();
    private final Context context;

    public ButtonsController(Context context) {
        this.context = context;

        actionCommandsMap.put(ActionCommands.OPEN_ACTION_COMMAND, this::handleOpenActionCommand);
        actionCommandsMap.put(ActionCommands.SAVE_ACTION_COMMAND, this::handleSaveActionCommand);
        actionCommandsMap.put(ActionCommands.EXIT_ACTION_COMMAND, this::handleExitActionCommand);

        actionCommandsMap.put(ActionCommands.DRAW_LINE_ACTION_COMMAND, this::handleDrawLineActionCommand);
        actionCommandsMap.put(ActionCommands.DRAW_STAR_ACTION_COMMAND, this::handleDrawStarActionCommand);
        actionCommandsMap.put(ActionCommands.DRAW_POLYGON_ACTION_COMMAND, this::handleDrawPolygonActionCommand);
        actionCommandsMap.put(ActionCommands.FILL_ACTION_COMMAND, this::handleFillActionCommand);
        actionCommandsMap.put(ActionCommands.CLEAR_ACTION_COMMAND, this::handleClearActionCommand);
        actionCommandsMap.put(ActionCommands.CHANGE_COLOR_ACTION_COMMAND, this::handleChangeColorActionCommand);
        actionCommandsMap.put(ActionCommands.CHANGE_THICKNESS_ACTION_COMMAND, this::handleChangeThicknessActionCommand);
        actionCommandsMap.put(ActionCommands.CHANGE_STAMP_ACTION_COMMAND, this::handleChangeStampActionCommand);
        actionCommandsMap.put(ActionCommands.CHANGE_ROTATION_ACTION_COMMAND, this::handleChangeRotationActionCommand);
        actionCommandsMap.put(ActionCommands.CHANGE_RADIUS_ACTION_COMMAND, this::handleChangeRadiusActionCommand);

        actionCommandsMap.put(ActionCommands.SHOW_HELP_ACTION_COMMAND, this::handleShowHelpActionCommand);
        actionCommandsMap.put(ActionCommands.SHOW_ABOUT_ACTION_COMMAND, this::handleShowAboutActionCommand);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        actionCommandsMap.get(actionEvent.getActionCommand()).run();
    }

    private void handleOpenActionCommand() {
        context.setState(ContextState.OPENING_FILE);
    }

    private void handleSaveActionCommand() {
        context.setState(ContextState.SAVING_FILE);
    }

    private void handleExitActionCommand() {
        context.setState(ContextState.EXITING);
    }

    private void handleDrawLineActionCommand() {
        context.setTool(ContextTools.DRAW_LINE);
    }

    private void handleDrawPolygonActionCommand() {
        context.setTool(ContextTools.DRAW_POLYGON);
    }

    private void handleDrawStarActionCommand() {
        context.setTool(ContextTools.DRAW_STAR);
    }

    private void handleFillActionCommand() {
        context.setTool(ContextTools.FILL);
    }

    private void handleClearActionCommand() {
        final BufferedImage currentImage = context.getImage();
        final int width = currentImage.getWidth();
        final int height = currentImage.getHeight();
        context.setImage(ImageUtils.createBlankImage(width, height));
        context.setState(ContextState.REPAINTING);
    }

    private void handleChangeColorActionCommand() {
        System.out.println("Change color");
    }

    private void handleChangeThicknessActionCommand() {
        System.out.println("Change thickness");
    }

    private void handleChangeStampActionCommand() {
        System.out.println("Change stamp");
    }

    private void handleChangeRotationActionCommand() {
        System.out.println("Change rotation");
    }

    private void handleChangeRadiusActionCommand() {
        System.out.println("Change radius");
    }

    private void handleShowHelpActionCommand() {
        System.out.println("Help");
    }

    private void handleShowAboutActionCommand() {
        System.out.println("About");
    }
}
