package ru.nsu.kondrenko.gui.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class View {
    private static final FileFilter fileFilter = new FileNameExtensionFilter("Images", "png", "bmp", "jpeg", "jpg", "gif");

    private final JFileChooser fileChooser;
    private final JFrame frame;

    public View(String viewName, int minWidth, int minHeight, ActionListener actionListener) {
        fileChooser = new JFileChooser();
        initFileChooser();

        frame = new JFrame(viewName);
        initFrame(minWidth, minHeight, actionListener);
    }

    public File selectImageFile() {
        final int code = fileChooser.showOpenDialog(frame);

        if (code == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }

        return null;
    }

    private void initFileChooser() {
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(fileFilter);
    }

    private void initFrame(int minWidth, int minHeight, ActionListener actionListener) {
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(minWidth, minHeight));
        frame.setResizable(false);

        final DrawingArea drawingArea = new DrawingArea();
        frame.add(drawingArea, BorderLayout.CENTER);

        final UpperArea toolsArea = new UpperArea(actionListener);
        frame.add(toolsArea, BorderLayout.NORTH);

        frame.pack();
        frame.setVisible(true);
    }
}
