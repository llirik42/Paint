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

    private JToggleButton drawLine;
    private JToggleButton drawPolygon;
    private JToggleButton drawStar;
    private JToggleButton fill;

    public ToolsArea(ToolsIconsSupplier toolsIconsSupplier, ActionListener actionListener) {
        final List<ToolButtonProperties> toolButtonsProperties = Arrays.asList(
                new ToolButtonProperties(toolsIconsSupplier.getDrawLineIcon(), ActionCommands.DRAW_LINE, "draw line"),
                new ToolButtonProperties(toolsIconsSupplier.getDrawPolygonIcon(), ActionCommands.DRAW_POLYGON, "draw polygon"),
                new ToolButtonProperties(toolsIconsSupplier.getDrawStarIcon(), ActionCommands.DRAW_STAR, "draw star"),
                new ToolButtonProperties(toolsIconsSupplier.getFillIcon(), ActionCommands.FILL, "fill"),
                new ToolButtonProperties(toolsIconsSupplier.getClearIcon(), ActionCommands.CLEAR, "clear"),
                new ToolButtonProperties(toolsIconsSupplier.getSelectThicknessIcon(), ActionCommands.SELECT_THICKNESS, "select thickness"),
                new ToolButtonProperties(toolsIconsSupplier.getSelectNumberOfVerticesIcon(), ActionCommands.SELECT_NUMBER_OF_VERTICES, "select number of vertices"),
                new ToolButtonProperties(toolsIconsSupplier.getSelectRadiusIcon(), ActionCommands.SELECT_RADIUS, "select radius"),
                new ToolButtonProperties(toolsIconsSupplier.getSelectRotationIcon(), ActionCommands.SELECT_ROTATION, "select rotation"),

                new ToolButtonProperties(toolsIconsSupplier.getSelectColorIcon(), ActionCommands.SELECT_COLOR, "choose color"),
                new ToolButtonProperties(toolsIconsSupplier.getBlackIcon(), ActionCommands.CHANGE_COLOR_TO_BLACK, "change color to black"),
                new ToolButtonProperties(toolsIconsSupplier.getWhiteIcon(), ActionCommands.CHANGE_COLOR_TO_WHITE, "change color to white"),
                new ToolButtonProperties(toolsIconsSupplier.getRedIcon(), ActionCommands.CHANGE_COLOR_TO_RED, "change color to red"),
                new ToolButtonProperties(toolsIconsSupplier.getGreenIcon(), ActionCommands.CHANGE_COLOR_TO_GREEN, "change color to green"),
                new ToolButtonProperties(toolsIconsSupplier.getBlueIcon(), ActionCommands.CHANGE_COLOR_TO_BLUE, "change color to blue"),
                new ToolButtonProperties(toolsIconsSupplier.getMagentaIcon(), ActionCommands.CHANGE_COLOR_TO_MAGENTA, "change color to magenta"),
                new ToolButtonProperties(toolsIconsSupplier.getPinkIcon(), ActionCommands.CHANGE_COLOR_TO_PINK, "change color to pink"),
                new ToolButtonProperties(toolsIconsSupplier.getOrangeIcon(), ActionCommands.CHANGE_COLOR_TO_ORANGE, "change color to orange"),
                new ToolButtonProperties(toolsIconsSupplier.getYellowIcon(), ActionCommands.CHANGE_COLOR_TO_YELLOW, "change color to yellow"),
                new ToolButtonProperties(toolsIconsSupplier.getCyanIcon(), ActionCommands.CHANGE_COLOR_TO_CYAN, "change color to cyan")
        );

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(AREA_BACKGROUND_COLOR);

        for (final var it : toolButtonsProperties) {
            final String actionCommand = it.actionCommand();

            AbstractButton button;

            switch (actionCommand) {
                case ActionCommands.DRAW_LINE -> {
                    drawLine = createToolToggleButton(it.icon(), actionCommand, it.tip(), actionListener);
                    button = drawLine;
                }

                case ActionCommands.DRAW_POLYGON -> {
                    drawPolygon = createToolToggleButton(it.icon(), actionCommand, it.tip(), actionListener);
                    button = drawPolygon;
                }

                case ActionCommands.DRAW_STAR -> {
                    drawStar = createToolToggleButton(it.icon(), actionCommand, it.tip(), actionListener);
                    button = drawStar;
                }

                case ActionCommands.FILL -> {
                    fill = createToolToggleButton(it.icon(), actionCommand, it.tip(), actionListener);
                    button = fill;
                }

                default -> button = createToolButton(it.icon(), actionCommand, it.tip(), actionListener);
            }

            add(button);
        }
    }

    public void setDrawLine() {
        drawLine.setSelected(true);
    }

    public void setDrawPolygon() {
        drawPolygon.setSelected(true);
    }

    public void setDrawStar() {
        drawStar.setSelected(true);
    }

    public void setFill() {
        fill.setSelected(true);
    }

    public void unselectAll() {
        drawLine.setSelected(false);
        drawPolygon.setSelected(false);
        drawStar.setSelected(false);
        fill.setSelected(false);
    }

    private JToggleButton createToolToggleButton(ImageIcon icon, String actionCommand, String tip, ActionListener actionListener) {
        final Image image = icon.getImage();
        final Image scaledImage = image.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        final ImageIcon scaledIcon = new ImageIcon(scaledImage);
        final JToggleButton result = new JToggleButton(scaledIcon);
        initButton(result, actionCommand, tip, actionListener);
        return result;
    }

    private JButton createToolButton(ImageIcon icon, String actionCommand, String tip, ActionListener actionListener) {
        final Image image = icon.getImage();
        final Image scaledImage = image.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        final ImageIcon scaledIcon = new ImageIcon(scaledImage);
        final JButton result = new JButton(scaledIcon);
        initButton(result, actionCommand, tip, actionListener);
        return result;
    }

    private void initButton(AbstractButton button, String actionCommand, String tip, ActionListener actionListener) {
        button.setFocusPainted(false);
        button.setActionCommand(actionCommand);
        button.setToolTipText(tip);
        button.addActionListener(actionListener);
        button.setPreferredSize(new Dimension(TOOL_SIZE, TOOL_SIZE));
        button.setBackground(BUTTONS_BACKGROUND_COLOR);
        button.setBorderPainted(false);
    }
}
