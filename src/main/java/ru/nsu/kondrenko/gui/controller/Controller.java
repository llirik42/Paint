package ru.nsu.kondrenko.gui.controller;

import ru.nsu.kondrenko.gui.ActionCommands;
import ru.nsu.kondrenko.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Controller implements ActionListener, ComponentListener, MouseListener {
    private final Map<String, Runnable> actionCommandsMap = new HashMap<>();
    private final ImageReader reader;
    private final ImageSaver saver;
    private final Context context;
    private final boolean hasPrevousCoordinates;
    private int prevX;
    private int prevY;

    public Controller(Context context) {
        this.context = context;
        this.reader = new ImageReader();
        this.saver = new ImageSaver();
        this.hasPrevousCoordinates = false;

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
        if (actionEvent.getSource() instanceof JFileChooser fileChooser) {
            handleJFileChooserEvent(actionEvent, fileChooser);
        } else {
            actionCommandsMap.get(actionEvent.getActionCommand()).run();
        }
    }

    private void handleJFileChooserEvent(ActionEvent actionEvent, JFileChooser fileChooser) {
        final String actionCommand = actionEvent.getActionCommand();

        if (!Objects.equals(actionCommand, JFileChooser.APPROVE_SELECTION)) {
            return;
        }

        if (context.getState() == ContextState.SAVING_FILE) {
            handleApproveSavingFile(fileChooser);
        } else if (context.getState() == ContextState.OPENING_FILE) {
            handleApproveOpeningFile(fileChooser);
        } else {
            // Ignored
        }
    }

    private void handleApproveOpeningFile(JFileChooser fileChooser) {
        try {
            final BufferedImage image = reader.read(fileChooser.getSelectedFile());
            context.setImage(image);
            context.setState(ContextState.REPAINTING);
        } catch (IOException exception) {
            // TODO: handle
        }
    }

    private void handleApproveSavingFile(JFileChooser fileChooser) {
        try {
            final File pngFile = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".png");
            saver.saveAsPNG(context.getImage(), pngFile);
        } catch (IOException exception) {
            // TODO: handle
        }
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

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void componentResized(ComponentEvent componentEvent) {
        final Component drawingArea = componentEvent.getComponent();
        final int drawingAreaWidth = drawingArea.getWidth();
        final int drawingAreaHeight = drawingArea.getHeight();

        final BufferedImage oldImage = context.getImage();
        final int oldImageWidth = oldImage.getWidth();
        final int oldImageHeight = oldImage.getHeight();

        final int newImageWidth = Integer.max(drawingAreaWidth, oldImageWidth);
        final int newImageHeight = Integer.max(drawingAreaHeight, oldImageHeight);

        if (newImageWidth == oldImageWidth && newImageHeight == oldImageHeight) {
            return;
        }

        final BufferedImage newImage = Utils.resizeBufferedImage(newImageWidth, newImageHeight, oldImage);
        context.setImage(newImage);
    }

    @Override
    public void componentMoved(ComponentEvent componentEvent) {

    }

    @Override
    public void componentShown(ComponentEvent componentEvent) {

    }

    @Override
    public void componentHidden(ComponentEvent componentEvent) {

    }
}
