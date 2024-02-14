package ru.nsu.kondrenko.gui.controller;

import ru.nsu.kondrenko.gui.ActionCommands;
import ru.nsu.kondrenko.model.Context;
import ru.nsu.kondrenko.model.ContextState;
import ru.nsu.kondrenko.model.ContextTools;
import ru.nsu.kondrenko.model.ImageUtils;

import java.awt.*;
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

        actionCommandsMap.put(ActionCommands.OPEN_ACTION_COMMAND, () -> context.setState(ContextState.OPENING_FILE));
        actionCommandsMap.put(ActionCommands.SAVE_ACTION_COMMAND, () -> context.setState(ContextState.SAVING_FILE));
        actionCommandsMap.put(ActionCommands.EXIT_ACTION_COMMAND, () -> context.setState(ContextState.EXITING));

        actionCommandsMap.put(ActionCommands.DRAW_LINE_ACTION_COMMAND, () -> context.setTool(ContextTools.DRAW_LINE));
        actionCommandsMap.put(ActionCommands.DRAW_POLYGON_ACTION_COMMAND, () -> context.setTool(ContextTools.DRAW_POLYGON));
        actionCommandsMap.put(ActionCommands.DRAW_STAR_ACTION_COMMAND, () -> context.setTool(ContextTools.DRAW_STAR));
        actionCommandsMap.put(ActionCommands.FILL_ACTION_COMMAND, () -> context.setTool(ContextTools.FILL));
        actionCommandsMap.put(ActionCommands.CLEAR_ACTION_COMMAND, this::handleClearActionCommand);
        actionCommandsMap.put(ActionCommands.SELECT_THICKNESS_ACTION_COMMAND, () -> context.setState(ContextState.CHOOSING_THICKNESS));
        actionCommandsMap.put(ActionCommands.SELECT_NUMBER_OF_VERTICES_ACTION_COMMAND, () -> context.setState(ContextState.CHOOSING_NUMBER_OF_SIDES));
        actionCommandsMap.put(ActionCommands.SELECT_ROTATION_ACTION_COMMAND, () -> context.setState(ContextState.CHOOSING_ROTATION));
        actionCommandsMap.put(ActionCommands.SELECT_RADIUS_ACTION_COMMAND, () -> context.setState(ContextState.CHOOSING_RADIUS));

        actionCommandsMap.put(ActionCommands.SELECT_COLOR_ACTION_COMMAND, () -> context.setState(ContextState.CHOOSING_COLOR));
        actionCommandsMap.put(ActionCommands.CHANGE_COLOR_TO_BLACK_ACTION_COMMAND, () -> context.setColor(Color.BLACK));
        actionCommandsMap.put(ActionCommands.CHANGE_COLOR_TO_WHITE_ACTION_COMMAND, () -> context.setColor(Color.WHITE));
        actionCommandsMap.put(ActionCommands.CHANGE_COLOR_TO_RED_ACTION_COMMAND, () -> context.setColor(Color.RED));
        actionCommandsMap.put(ActionCommands.CHANGE_COLOR_TO_GREEN_ACTION_COMMAND, () -> context.setColor(Color.GREEN));
        actionCommandsMap.put(ActionCommands.CHANGE_COLOR_TO_BLUE_ACTION_COMMAND, () -> context.setColor(Color.BLUE));
        actionCommandsMap.put(ActionCommands.CHANGE_COLOR_TO_MAGENTA_ACTION_COMMAND, () -> context.setColor(Color.MAGENTA));
        actionCommandsMap.put(ActionCommands.CHANGE_COLOR_TO_PINK_ACTION_COMMAND, () -> context.setColor(Color.PINK));
        actionCommandsMap.put(ActionCommands.CHANGE_COLOR_TO_ORANGE_ACTION_COMMAND, () -> context.setColor(Color.ORANGE));
        actionCommandsMap.put(ActionCommands.CHANGE_COLOR_TO_YELLOW_ACTION_COMMAND, () -> context.setColor(Color.YELLOW));
        actionCommandsMap.put(ActionCommands.CHANGE_COLOR_TO_CYAN_ACTION_COMMAND, () -> context.setColor(Color.CYAN));

        actionCommandsMap.put(ActionCommands.SHOW_HELP_ACTION_COMMAND, () -> context.setState(ContextState.DISPLAYING_HELP));
        actionCommandsMap.put(ActionCommands.SHOW_ABOUT_ACTION_COMMAND, () -> context.setState(ContextState.DISPLAYING_ABOUT));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        actionCommandsMap.get(actionEvent.getActionCommand()).run();
    }

    private void handleClearActionCommand() {
        final BufferedImage currentImage = context.getImage();
        final int width = currentImage.getWidth();
        final int height = currentImage.getHeight();
        context.setImage(ImageUtils.createBlankImage(width, height));
        context.setState(ContextState.REPAINTING);
    }
}
