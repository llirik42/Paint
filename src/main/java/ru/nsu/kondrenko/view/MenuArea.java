package ru.nsu.kondrenko.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuArea extends JPanel {
    private static final Font FONT = new Font("Roboto", Font.PLAIN, 12);
    private static final Color BACKGROUND_COLOR = Color.WHITE;

    public MenuArea(ActionListener actionListener) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(BACKGROUND_COLOR);

        final JPopupMenu fileMenu = new JPopupMenu();
        fileMenu.setBackground(BACKGROUND_COLOR);
        final JButton openButton = createButton("Open", "OPEN-ACTION", actionListener);
        final JButton saveButton = createButton("Save", "SAVE ACTION", actionListener);
        fileMenu.add(openButton);
        fileMenu.add(saveButton);

        final JPopupMenu editMenu = new JPopupMenu();
        editMenu.setBackground(BACKGROUND_COLOR);
        final ButtonGroup group = new ButtonGroup();
        final JRadioButton drawLineButton = createRadionButton("Draw line", "DRAW LINE ACTION", actionListener);
        final JRadioButton drawStampButton = createRadionButton("Draw stamp", "DRAW STAMP ACTION", actionListener);
        final JRadioButton fillButton = createRadionButton("Fill", "FILL ACTION", actionListener);
        final JButton clearButton = createButton("Clear", "CLEAR ACTION", actionListener);
        final JButton changeColorButton = createButton("Change color", "CHANGE COLOR ACTION", actionListener);
        final JButton changeRotationButton = createButton("Change rotation", "CHANGE ROTATION ACTION", actionListener);
        final JButton changeRadiusButton = createButton("Change radius", "CHANGE RADIUS ACTION", actionListener);
        group.add(drawLineButton);
        group.add(drawStampButton);
        group.add(fillButton);

        editMenu.add(drawLineButton);
        editMenu.add(drawStampButton);
        editMenu.add(fillButton);
        editMenu.add(clearButton);
        editMenu.add(changeColorButton);
        editMenu.add(changeRotationButton);
        editMenu.add(changeRadiusButton);

        final JButton fileButton = createButton("File", fileMenu);
        final JButton editButton = createButton("Edit", editMenu);
        final JButton helpButton = createButton("Help", "HELP ACTION", actionListener);
        final JButton aboutButton = createButton("About", "ABOUT ACTION", actionListener);

        add(fileButton);
        add(editButton);
        add(helpButton);
        add(aboutButton);
    }

    private static JButton createButton(String label, String actionCommand, ActionListener actionListener) {
        final JButton result = new JButton(label);
        result.setBorderPainted(false);
        result.setFont(FONT);
        result.setBackground(BACKGROUND_COLOR);
        result.setActionCommand(actionCommand);
        result.addActionListener(actionListener);

        return result;
    }

    private static JRadioButton createRadionButton(String label, String actionCommand, ActionListener actionListener) {
        final JRadioButton result = new JRadioButton(label);
        result.setBorderPainted(false);
        result.setFont(FONT);
        result.setBackground(BACKGROUND_COLOR);
        result.setActionCommand(actionCommand);
        result.addActionListener(actionListener);

        return result;
    }

    private static JButton createButton(String label, JPopupMenu menu) {
        final JButton result = new JButton(label);
        result.setBorderPainted(false);
        result.setFont(FONT);
        result.setBackground(BACKGROUND_COLOR);
        result.addActionListener(actionEvent -> menu.show(result, 0, 30));

        return result;
    }
}
