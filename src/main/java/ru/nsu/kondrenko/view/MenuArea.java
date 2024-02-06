package ru.nsu.kondrenko.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuArea extends JPanel {
    private static final String OPEN_ACTION_COMMAND = "OPEN";
    private static final String SAVE_ACTION_COMMAND = "SAVE";
    private static final String HELP_ACTION_COMMAND = "HELP";
    private static final String ABOUT_ACTION_COMMAND = "ABOUT";

    private static final String OPEN = "Open";
    private static final String SAVE = "Save";
    private static final String HELP = "Help";
    private static final String ABOUT = "About";

    private static final Font FONT = new Font("Roboto", Font.PLAIN, 12);
    private static final Color BACKGROUND_COLOR = Color.WHITE;

    public MenuArea(ActionListener actionListener) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(BACKGROUND_COLOR);

        final JButton openButton = createButton(OPEN, OPEN_ACTION_COMMAND, actionListener);
        final JButton saveButton = createButton(SAVE, SAVE_ACTION_COMMAND, actionListener);
        final JButton helpButton = createButton(HELP, HELP_ACTION_COMMAND, actionListener);
        final JButton aboutButton = createButton(ABOUT, ABOUT_ACTION_COMMAND, actionListener);

        add(openButton);
        add(saveButton);
        add(helpButton);
        add(aboutButton);
    }

    private static JButton createButton(String label, String actionCommand, ActionListener actionListener) {
        final JButton result = new JButton(label);
        result.setActionCommand(actionCommand);
        result.addActionListener(actionListener);
        result.setBorderPainted(false);
        result.setFont(FONT);
        result.setBackground(BACKGROUND_COLOR);
        return result;
    }
}
