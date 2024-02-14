package ru.nsu.kondrenko.gui.controller;

import ru.nsu.kondrenko.model.Context;
import ru.nsu.kondrenko.model.ContextState;
import ru.nsu.kondrenko.model.ImageReader;
import ru.nsu.kondrenko.model.ImageSaver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FilesActionsController implements ActionListener {
    private final Context context;
    private final ImageReader reader;
    private final ImageSaver saver;

    public FilesActionsController(Context context) {
        this.context = context;

        // TODO: do dependency injection
        this.reader = new ImageReader();
        this.saver = new ImageSaver();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        handleJFileChooserEvent(actionEvent, (JFileChooser)actionEvent.getSource());
    }

    private void handleJFileChooserEvent(ActionEvent actionEvent, JFileChooser fileChooser) {
        final String actionCommand = actionEvent.getActionCommand();

        if (!Objects.equals(actionCommand, JFileChooser.APPROVE_SELECTION)) {
            return;
        }

        if (context.getState() == ContextState.SAVING_FILE) {
            handleApproveSavingFile(fileChooser);
        } else {
            handleApproveOpeningFile(fileChooser);
        }
    }

    private void handleApproveOpeningFile(JFileChooser fileChooser) {
        try {
            final BufferedImage image = reader.read(fileChooser.getSelectedFile());
            context.setImage(image);
            context.setState(ContextState.REPAINTING);
        } catch (IOException exception) {
            context.setErrorMessage(exception.getLocalizedMessage());
            context.setState(ContextState.ERROR);
        }
    }

    private void handleApproveSavingFile(JFileChooser fileChooser) {
        try {
            final File pngFile = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".png");
            saver.saveAsPNG(context.getImage(), pngFile);
        } catch (IOException exception) {
            context.setErrorMessage(exception.getLocalizedMessage());
            context.setState(ContextState.ERROR);
        }
    }
}
