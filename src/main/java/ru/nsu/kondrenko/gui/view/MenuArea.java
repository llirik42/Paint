package ru.nsu.kondrenko.gui.view;

import ru.nsu.kondrenko.gui.ActionCommands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuArea extends JPanel {
    private static final Font FONT = new Font("Roboto", Font.BOLD, 14);

    private static final Color MENU_BACKGROUND_COLOR = new Color(0.85f, 0.85f, 0.85f);
    private static final Color BUTTONS_FONT_COLOR = new Color(0.16f, 0.15f, 0.15f);

    private static final String FILE_MENU_TITLE = "File";
    private static final String EDIT_MENU_TITLE = "Edit";
    private static final String HELP_MENU_TITLE = "Help";

    private static final String OPEN_BUTTON_LABEL = "Open";
    private static final String SAVE_BUTTON_LABEL = "Save";
    private static final String EXIT_BUTTON_LABEL = "Exit";
    private static final String DRAW_LINE_BUTTON_LABEL = "Draw line";
    private static final String DRAW_STAMP_BUTTON_LABEL = "Draw stamp";
    private static final String FILL_BUTTON_LABEL = "Fill";
    private static final String CLEAR_BUTTON_LABEL = "Clear";
    private static final String CHANGE_COLOR_BUTTON_LABEL = "Change color";
    private static final String CHANGE_THICKNESS_BUTTON_LABEL = "Change thickness";
    private static final String CHANGE_STAMP_BUTTON_LABEL = "Change stamp";
    private static final String CHANGE_ROTATION_BUTTON_LABEL = "Change rotation";
    private static final String CHANGE_RADIUS_BUTTON_LABEL = "Change radius";

    private static final String SHOW_HELP_BUTTON_LABEL = "Help";
    private static final String SHOW_ABOUT_BUTTON_LABEL = "About";

    private final JMenuBar menuBar;

    public MenuArea(ActionListener actionListener) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(MENU_BACKGROUND_COLOR);

        menuBar = new JMenuBar();
        menuBar.setBackground(MENU_BACKGROUND_COLOR);

        final JMenu fileMenu = createFileMenu(actionListener);
        final JMenu editMenu = createEditMenu(actionListener);
        final JMenu helpMenu = createHelpMenu(actionListener);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
    }

    private static JMenu createFileMenu(ActionListener actionListener) {
        final JMenu result = createMenu(FILE_MENU_TITLE);

        final JMenuItem openButton = createMenuItem(
                OPEN_BUTTON_LABEL,
                ActionCommands.OPEN_ACTION_COMMAND,
                actionListener
        );
        final JMenuItem saveButton = createMenuItem(
                SAVE_BUTTON_LABEL,
                ActionCommands.SAVE_ACTION_COMMAND,
                actionListener
        );
        final JMenuItem exitButton = createMenuItem(
                EXIT_BUTTON_LABEL,
                ActionCommands.EXIT_ACTION_COMMAND,
                actionListener
        );

        result.add(openButton);
        result.add(saveButton);
        result.add(exitButton);

        return result;
    }

    private static JMenu createEditMenu(ActionListener actionListener) {
        final JMenu result = createMenu(EDIT_MENU_TITLE);

        final ButtonGroup group = new ButtonGroup();
        final JRadioButtonMenuItem drawLineButton = createRadioButtonMenuItem(
                DRAW_LINE_BUTTON_LABEL,
                ActionCommands.DRAW_LINE_ACTION_COMMAND,
                actionListener
        );
        final JRadioButtonMenuItem drawStampButton = createRadioButtonMenuItem(
                DRAW_STAMP_BUTTON_LABEL,
                ActionCommands.DRAW_STAMP_ACTION_COMMAND,
                actionListener
        );
        final JRadioButtonMenuItem fillButton = createRadioButtonMenuItem(
                FILL_BUTTON_LABEL,
                ActionCommands.FILL_ACTION_COMMAND,
                actionListener
        );

        final JMenuItem clearButton = createMenuItem(
                CLEAR_BUTTON_LABEL,
                ActionCommands.CLEAR_ACTION_COMMAND,
                actionListener
        );
        final JMenuItem changeColorButton = createMenuItem(
                CHANGE_COLOR_BUTTON_LABEL,
                ActionCommands.CHANGE_COLOR_ACTION_COMMAND, actionListener);
        final JMenuItem changeThicknessButton = createMenuItem(
                CHANGE_THICKNESS_BUTTON_LABEL,
                ActionCommands.CHANGE_THICKNESS_ACTION_COMMAND,
                actionListener
        );
        final JMenuItem changeStampButton = createMenuItem(
                CHANGE_STAMP_BUTTON_LABEL,
                ActionCommands.CHANGE_STAMP_ACTION_COMMAND,
                actionListener
        );
        final JMenuItem changeRotationButton = createMenuItem(
                CHANGE_ROTATION_BUTTON_LABEL,
                ActionCommands.CHANGE_ROTATION_ACTION_COMMAND,
                actionListener
        );
        final JMenuItem changeRadiusButton = createMenuItem(
                CHANGE_RADIUS_BUTTON_LABEL,
                ActionCommands.CHANGE_RADIUS_ACTION_COMMAND,
                actionListener
        );

        group.add(drawLineButton);
        group.add(drawStampButton);
        group.add(fillButton);

        result.add(drawLineButton);
        result.add(drawStampButton);
        result.add(fillButton);
        result.add(new JSeparator());
        result.add(clearButton);
        result.add(changeColorButton);
        result.add(changeThicknessButton);
        result.add(changeStampButton);
        result.add(changeRotationButton);
        result.add(changeRadiusButton);

        return result;
    }

    private static JMenu createHelpMenu(ActionListener actionListener) {
        final JMenu result = createMenu(HELP_MENU_TITLE);

        final JMenuItem helpButton = createMenuItem(
                SHOW_HELP_BUTTON_LABEL,
                ActionCommands.SHOW_HELP_ACTION_COMMAND,
                actionListener
        );
        final JMenuItem aboutButton = createMenuItem(
                SHOW_ABOUT_BUTTON_LABEL,
                ActionCommands.SHOW_ABOUT_ACTION_COMMAND,
                actionListener
        );

        result.add(helpButton);
        result.add(aboutButton);

        return result;
    }

    private static JMenu createMenu(String label) {
        JMenu result = new JMenu(label);
        result.setBackground(MENU_BACKGROUND_COLOR);
        result.setForeground(BUTTONS_FONT_COLOR);
        result.setFont(FONT);
        return result;
    }

    private static JMenuItem createMenuItem(String label, String actionCommand, ActionListener actionListener) {
        final JMenuItem result = new JMenuItem(label);
        initButton(result, actionListener);
        result.setActionCommand(actionCommand);
        return result;
    }

    private static JRadioButtonMenuItem createRadioButtonMenuItem(String label, String actionCommand, ActionListener actionListener) {
        final JRadioButtonMenuItem result = new JRadioButtonMenuItem(label);
        initButton(result, actionListener);
        result.setActionCommand(actionCommand);
        return result;
    }

    private static void initButton(AbstractButton button, ActionListener actionListener) {
        button.setBorderPainted(false);
        button.setFont(FONT);
        button.setForeground(BUTTONS_FONT_COLOR);
        button.addActionListener(actionListener);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
