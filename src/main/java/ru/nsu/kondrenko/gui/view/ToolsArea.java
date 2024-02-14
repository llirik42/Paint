package ru.nsu.kondrenko.gui.view;

import ru.nsu.kondrenko.gui.ActionCommands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class ToolsArea extends JPanel {
    private static final Color AREA_BACKGROUND_COLOR = new Color(0.85f, 0.85f, 0.85f);
    private static final Color BUTTONS_BACKGROUND_COLOR = new Color(0.72f, 0.72f, 0.71f);

    private static final int TOOL_SIZE = 26;
    private static final int ICON_SIZE = 20;

    public ToolsArea(ToolsIconsSupplier toolsIconsSupplier, ActionListener actionListener) {
        final List<ToolButtonProperties> toolButtonsProperties = Arrays.asList(
                new ToolButtonProperties(toolsIconsSupplier.getDrawLineIcon(), ActionCommands.DRAW_LINE_ACTION_COMMAND, "draw line"),
                new ToolButtonProperties(toolsIconsSupplier.getDrawPolygonIcon(), ActionCommands.DRAW_POLYGON_ACTION_COMMAND, "draw polygon"),
                new ToolButtonProperties(toolsIconsSupplier.getDrawStarIcon(), ActionCommands.DRAW_STAR_ACTION_COMMAND, "draw star"),
                new ToolButtonProperties(toolsIconsSupplier.getFillIcon(), ActionCommands.FILL_ACTION_COMMAND, "fill"),
                new ToolButtonProperties(toolsIconsSupplier.getClearIcon(), ActionCommands.CLEAR_ACTION_COMMAND, "clear"),
                new ToolButtonProperties(toolsIconsSupplier.getSelectThicknessIcon(), ActionCommands.SELECT_THICKNESS_ACTION_COMMAND, "select thickness"),
                new ToolButtonProperties(toolsIconsSupplier.getSelectNumberOfVerticesIcon(), ActionCommands.SELECT_NUMBER_OF_VERTICES_ACTION_COMMAND, "select number of vertices"),
                new ToolButtonProperties(toolsIconsSupplier.getSelectRadiusIcon(), ActionCommands.SELECT_RADIUS_ACTION_COMMAND, "select radius"),
                new ToolButtonProperties(toolsIconsSupplier.getSelectRotationIcon(), ActionCommands.SELECT_ROTATION_ACTION_COMMAND, "select rotation"),

                new ToolButtonProperties(toolsIconsSupplier.getSelectColorIcon(), ActionCommands.SELECT_COLOR_ACTION_COMMAND, "choose color"),
                new ToolButtonProperties(toolsIconsSupplier.getBlackIcon(), ActionCommands.CHANGE_COLOR_TO_BLACK_ACTION_COMMAND, "change color to black"),
                new ToolButtonProperties(toolsIconsSupplier.getWhiteIcon(), ActionCommands.CHANGE_COLOR_TO_WHITE_ACTION_COMMAND, "change color to white"),
                new ToolButtonProperties(toolsIconsSupplier.getRedIcon(), ActionCommands.CHANGE_COLOR_TO_RED_ACTION_COMMAND, "change color to red"),
                new ToolButtonProperties(toolsIconsSupplier.getGreenIcon(), ActionCommands.CHANGE_COLOR_TO_GREEN_ACTION_COMMAND, "change color to green"),
                new ToolButtonProperties(toolsIconsSupplier.getBlueIcon(), ActionCommands.CHANGE_COLOR_TO_BLUE_ACTION_COMMAND, "change color to blue"),
                new ToolButtonProperties(toolsIconsSupplier.getMagentaIcon(), ActionCommands.CHANGE_COLOR_TO_MAGENTA_ACTION_COMMAND, "change color to magenta"),
                new ToolButtonProperties(toolsIconsSupplier.getPinkIcon(), ActionCommands.CHANGE_COLOR_TO_PINK_ACTION_COMMAND, "change color to pink"),
                new ToolButtonProperties(toolsIconsSupplier.getOrangeIcon(), ActionCommands.CHANGE_COLOR_TO_ORANGE_ACTION_COMMAND, "change color to orange"),
                new ToolButtonProperties(toolsIconsSupplier.getYellowIcon(), ActionCommands.CHANGE_COLOR_TO_YELLOW_ACTION_COMMAND, "change color to yellow"),
                new ToolButtonProperties(toolsIconsSupplier.getCyanIcon(), ActionCommands.CHANGE_COLOR_TO_CYAN_ACTION_COMMAND, "change color to cyan")
        );

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(AREA_BACKGROUND_COLOR);

        for (final var it : toolButtonsProperties) {
            final JButton button = createToolButton(it.icon(), it.actionCommand(), it.tip(), actionListener);
            add(button);
        }
    }

    private JButton createToolButton(ImageIcon icon, String actionCommand, String tip, ActionListener actionListener) {
        final Image image = icon.getImage();
        final Image scaledImage = image.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        final ImageIcon scaledIcon = new ImageIcon(scaledImage);
        final JButton result = new JButton(scaledIcon);
        result.setFocusPainted(false);
        result.setActionCommand(actionCommand);
        result.setToolTipText(tip);
        result.addActionListener(actionListener);
        result.setPreferredSize(new Dimension(TOOL_SIZE, TOOL_SIZE));
        result.setBackground(BUTTONS_BACKGROUND_COLOR);
        result.setBorderPainted(false);

        return result;
    }
}
