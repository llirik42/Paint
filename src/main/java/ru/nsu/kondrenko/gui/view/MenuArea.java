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

    private final List<MenuItemProperties> FILE_MENU_ITEMS_PROPERTIES = Arrays.asList(
            new MenuItemProperties("Open", ActionCommands.OPEN),
            new MenuItemProperties("Save", ActionCommands.SAVE),
            new MenuItemProperties("Exit", ActionCommands.EXIT)
    );
    private final List<MenuItemProperties> EDIT_MENU_DRAWING_ITEMS_PROPERTIES = Arrays.asList(
            new MenuItemProperties("Draw line", ActionCommands.DRAW_LINE, MenuItemType.DRAW_LINE_TOOL),
            new MenuItemProperties("Draw polygon", ActionCommands.DRAW_POLYGON, MenuItemType.DRAW_POLYGON_TOOL),
            new MenuItemProperties("Draw star", ActionCommands.DRAW_STAR, MenuItemType.DRAW_STAR_TOOL),
            new MenuItemProperties("Fill", ActionCommands.FILL, MenuItemType.FILL_TOOL)
    );
    private final List<MenuItemProperties> EDIT_MENU_OTHER_ITEMS_PROPERTIES = Arrays.asList(
            new MenuItemProperties("Clear", ActionCommands.CLEAR),
            new MenuItemProperties("Select color", ActionCommands.SELECT_COLOR),
            new MenuItemProperties("Select thickness", ActionCommands.SELECT_THICKNESS),
            new MenuItemProperties("Select number of vertices", ActionCommands.SELECT_NUMBER_OF_VERTICES),
            new MenuItemProperties("Select rotation", ActionCommands.SELECT_ROTATION),
            new MenuItemProperties("Select radius", ActionCommands.SELECT_RADIUS)
    );
    private final List<MenuItemProperties> HELP_ABOUT_MENU_ITEMS_PROPERTIES = Arrays.asList(
            new MenuItemProperties("Help", ActionCommands.SHOW_HELP),
            new MenuItemProperties("About", ActionCommands.SHOW_ABOUT)
    );
    private final JMenuBar menuBar;

    private JMenuItem drawLine;
    private JRadioButtonMenuItem drawPolygon;
    private JRadioButtonMenuItem drawStar;
    private JRadioButtonMenuItem fill;
    private ButtonGroup toolsGroup;

    public MenuArea(ActionListener actionListener) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(MENU_BACKGROUND_COLOR);

        menuBar = new JMenuBar();
        menuBar.setBackground(MENU_BACKGROUND_COLOR);

        List<Function<ActionListener, JMenu>> MENUS_CREATION_METHODS = Arrays.asList(
                this::createFileMenu,
                this::createEditMenu,
                this::createHelpMenu
        );
        for (final var it : MENUS_CREATION_METHODS) {
            final JMenu menu = it.apply(actionListener);
            menuBar.add(menu);
        }
    }

    public void setDrawLineEnabled() {
        toolsGroup.setSelected(drawLine.getModel(), true);
    }

    public void setDrawPolygonEnabled() {
        toolsGroup.setSelected(drawPolygon.getModel(), true);
    }

    public void setDrawStarEnabled() {
        toolsGroup.setSelected(drawStar.getModel(), true);
    }

    public void setFillEnabled() {
        toolsGroup.setSelected(fill.getModel(), true);
    }

    private JMenu createFileMenu(ActionListener actionListener) {
        final JMenu result = createMenu(FILE_MENU_TITLE);

        for (final var it : FILE_MENU_ITEMS_PROPERTIES) {
            final JMenuItem item = createMenuItem(it.label(), it.actionCommand(), actionListener);
            result.add(item);
        }

        return result;
    }

    private JMenu createEditMenu(ActionListener actionListener) {
        final JMenu result = createMenu(EDIT_MENU_TITLE);

        toolsGroup = new ButtonGroup();
        for (final var it : EDIT_MENU_DRAWING_ITEMS_PROPERTIES) {
            final JRadioButtonMenuItem item = createRadioButtonMenuItem(it.label(), it.actionCommand(), actionListener);
            toolsGroup.add(item);
            result.add(item);

            final MenuItemType type = it.type();
            switch (type) {
                case DRAW_LINE_TOOL -> drawLine = item;
                case DRAW_POLYGON_TOOL -> drawPolygon = item;
                case DRAW_STAR_TOOL -> drawStar = item;
                case FILL_TOOL -> fill = item;
            }
        }
        result.add(new JSeparator());

        for (final var it : EDIT_MENU_OTHER_ITEMS_PROPERTIES) {
            final JMenuItem item = createMenuItem(it.label(), it.actionCommand(), actionListener);
            result.add(item);
        }

        return result;
    }

    private JMenu createHelpMenu(ActionListener actionListener) {
        final JMenu result = createMenu(HELP_MENU_TITLE);

        for (final var it : HELP_ABOUT_MENU_ITEMS_PROPERTIES) {
            final JMenuItem item = createMenuItem(it.label(), it.actionCommand(), actionListener);
            result.add(item);
        }

        return result;
    }

    private JMenu createMenu(String label) {
        JMenu result = new JMenu(label);
        result.setBackground(MENU_BACKGROUND_COLOR);
        result.setForeground(BUTTONS_FONT_COLOR);
        result.setFont(FONT);
        return result;
    }

    private JMenuItem createMenuItem(String label, String actionCommand, ActionListener actionListener) {
        final JMenuItem result = new JMenuItem(label);
        initButton(result, actionListener);
        result.setActionCommand(actionCommand);
        return result;
    }

    private JRadioButtonMenuItem createRadioButtonMenuItem(String label, String actionCommand, ActionListener actionListener) {
        final JRadioButtonMenuItem result = new JRadioButtonMenuItem(label);
        initButton(result, actionListener);
        result.setActionCommand(actionCommand);
        return result;
    }

    private void initButton(AbstractButton button, ActionListener actionListener) {
        button.setBorderPainted(false);
        button.setFont(FONT);
        button.setForeground(BUTTONS_FONT_COLOR);
        button.addActionListener(actionListener);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
