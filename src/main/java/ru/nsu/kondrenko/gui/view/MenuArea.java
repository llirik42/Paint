package ru.nsu.kondrenko.gui.view;

import ru.nsu.kondrenko.gui.ActionCommands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class MenuArea extends JPanel {
    private static final Font FONT = new Font("Go", Font.BOLD, 14);

    private static final Color MENU_BACKGROUND_COLOR = new Color(0.85f, 0.85f, 0.85f);
    private static final Color BUTTONS_FONT_COLOR = new Color(0.14f, 0.13f, 0.13f);

    private static final String FILE_MENU_TITLE = "File";
    private static final String EDIT_MENU_TITLE = "Edit";
    private static final String HELP_MENU_TITLE = "Info";
    private static final List<MenuItemProperties> FILE_MENU_ITEMS_PROPERTIES = Arrays.asList(
            new MenuItemProperties("Open", ActionCommands.OPEN_ACTION_COMMAND),
            new MenuItemProperties("Save", ActionCommands.SAVE_ACTION_COMMAND),
            new MenuItemProperties("Exit", ActionCommands.EXIT_ACTION_COMMAND)
    );
    private static final List<MenuItemProperties> EDIT_MENU_DRAWING_ITEMS_PROPERTIES = Arrays.asList(
            new MenuItemProperties("Draw line", ActionCommands.DRAW_LINE_ACTION_COMMAND),
            new MenuItemProperties("Draw polygon", ActionCommands.DRAW_POLYGON_ACTION_COMMAND),
            new MenuItemProperties("Draw star", ActionCommands.DRAW_STAR_ACTION_COMMAND),
            new MenuItemProperties("Fill", ActionCommands.FILL_ACTION_COMMAND)
    );
    private static final List<MenuItemProperties> EDIT_MENU_OTHER_ITEMS_PROPERTIES = Arrays.asList(
            new MenuItemProperties("Clear", ActionCommands.CLEAR_ACTION_COMMAND),
            new MenuItemProperties("Change color", ActionCommands.CHANGE_COLOR_ACTION_COMMAND),
            new MenuItemProperties("Change thickness", ActionCommands.CHANGE_THICKNESS_ACTION_COMMAND),
            new MenuItemProperties("Change stamp", ActionCommands.CHANGE_STAMP_ACTION_COMMAND),
            new MenuItemProperties("Change rotation", ActionCommands.CHANGE_ROTATION_ACTION_COMMAND),
            new MenuItemProperties("Change radius", ActionCommands.CHANGE_RADIUS_ACTION_COMMAND)
    );
    private static final List<MenuItemProperties> HELP_ABOUT_MENU_ITEMS_PROPERTIES = Arrays.asList(
            new MenuItemProperties("Help", ActionCommands.SHOW_HELP_ACTION_COMMAND),
            new MenuItemProperties("About", ActionCommands.SHOW_ABOUT_ACTION_COMMAND)
    );
    private static final List<Function<ActionListener, JMenu>> MENUS_CREATION_METHODS = Arrays.asList(
            MenuArea::createFileMenu,
            MenuArea::createEditMenu,
            MenuArea::createHelpMenu
    );
    private final JMenuBar menuBar;

    public MenuArea(ActionListener actionListener) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(MENU_BACKGROUND_COLOR);

        menuBar = new JMenuBar();
        menuBar.setBackground(MENU_BACKGROUND_COLOR);

        for (final var it : MENUS_CREATION_METHODS) {
            final JMenu menu = it.apply(actionListener);
            menuBar.add(menu);
        }
    }

    private static JMenu createFileMenu(ActionListener actionListener) {
        final JMenu result = createMenu(FILE_MENU_TITLE);

        for (final var it : FILE_MENU_ITEMS_PROPERTIES) {
            final JMenuItem item = createMenuItem(it.label(), it.actionCommand(), actionListener);
            result.add(item);
        }

        return result;
    }

    private static JMenu createEditMenu(ActionListener actionListener) {
        final JMenu result = createMenu(EDIT_MENU_TITLE);

        final ButtonGroup group = new ButtonGroup();
        for (final var it : EDIT_MENU_DRAWING_ITEMS_PROPERTIES) {
            final JRadioButtonMenuItem item = createRadioButtonMenuItem(it.label(), it.actionCommand(), actionListener);
            group.add(item);
            result.add(item);
        }
        result.add(new JSeparator());

        for (final var it : EDIT_MENU_OTHER_ITEMS_PROPERTIES) {
            final JMenuItem item = createMenuItem(it.label(), it.actionCommand(), actionListener);
            result.add(item);
        }

        return result;
    }

    private static JMenu createHelpMenu(ActionListener actionListener) {
        final JMenu result = createMenu(HELP_MENU_TITLE);

        for (final var it : HELP_ABOUT_MENU_ITEMS_PROPERTIES) {
            final JMenuItem item = createMenuItem(it.label(), it.actionCommand(), actionListener);
            result.add(item);
        }

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
