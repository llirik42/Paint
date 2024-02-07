package ru.nsu.kondrenko.gui.controller;

import ru.nsu.kondrenko.gui.ActionCommands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Controller implements ActionListener {
    private final Map<String, Runnable> actionCommandsMap = new HashMap<>();

    public Controller() {
        actionCommandsMap.put(ActionCommands.OPEN_ACTION_COMMAND, this::handleOpenActionCommand);
        actionCommandsMap.put(ActionCommands.SAVE_ACTION_COMMAND, this::handleSaveActionCommand);
        actionCommandsMap.put(ActionCommands.EXIT_ACTION_COMMAND, this::handleExitActionCommand);

        actionCommandsMap.put(ActionCommands.DRAW_LINE_ACTION_COMMAND, this::handleDrawLineActionCommand);
        actionCommandsMap.put(ActionCommands.DRAW_STAMP_ACTION_COMMAND, this::handleDrawStampActionCommand);
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
        System.out.println("Open");
    }

    private void handleSaveActionCommand() {
        System.out.println("Save");
    }

    private void handleExitActionCommand() {
        System.out.println("Exit");
    }

    private void handleDrawLineActionCommand() {
        System.out.println("Draw line");
    }

    private void handleDrawStampActionCommand() {
        System.out.println("Draw stamp");
    }

    private void handleFillActionCommand() {
        System.out.println("Fill");
    }

    private void handleClearActionCommand() {
        System.out.println("Clear");
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
