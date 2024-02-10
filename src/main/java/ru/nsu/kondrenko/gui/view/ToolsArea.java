package ru.nsu.kondrenko.gui.view;

import ru.nsu.kondrenko.gui.ActionCommands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class ToolsArea extends JPanel {
    private static final Color BACKGROUND_COLOR = new Color(0.85f, 0.85f, 0.85f);
    private static final int ICON_SIZE = 18;

    public ToolsArea(ToolsIconsSupplier toolsIconsSupplier, ActionListener actionListener) {
        final List<ToolButtonProperties> toolButtonsProperties = Arrays.asList(
                new ToolButtonProperties(toolsIconsSupplier.getLineToolIcon(), ActionCommands.DRAW_LINE_ACTION_COMMAND, "draw line"),
                new ToolButtonProperties(toolsIconsSupplier.getStampToolIcon(), ActionCommands.DRAW_STAMP_ACTION_COMMAND, "draw stamp"),
                new ToolButtonProperties(toolsIconsSupplier.getFillToolIcon(), ActionCommands.FILL_ACTION_COMMAND, "fill"),
                new ToolButtonProperties(toolsIconsSupplier.getClearToolIcon(), ActionCommands.CLEAR_ACTION_COMMAND, "clear"),
                new ToolButtonProperties(toolsIconsSupplier.getChangeColorToolIcon(), ActionCommands.CHANGE_COLOR_ACTION_COMMAND, "change color"),
                new ToolButtonProperties(toolsIconsSupplier.getChangeThicknessToolIcon(), ActionCommands.CHANGE_THICKNESS_ACTION_COMMAND, "change thickness"),
                new ToolButtonProperties(toolsIconsSupplier.getChangeStampToolIcon(), ActionCommands.CHANGE_STAMP_ACTION_COMMAND, "change stamp"),
                new ToolButtonProperties(toolsIconsSupplier.getChangeRadiusToolIcon(), ActionCommands.CHANGE_RADIUS_ACTION_COMMAND, "change radius"),
                new ToolButtonProperties(toolsIconsSupplier.getChangeRotationToolIcon(), ActionCommands.CHANGE_ROTATION_ACTION_COMMAND, "change rotation")
        );

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(BACKGROUND_COLOR);

        for (final var it : toolButtonsProperties) {
            final JButton button = createToolButton(it.icon(), it.actionCommand(), it.tip(), actionListener);
            add(button);
        }
    }

    private static JButton createToolButton(ImageIcon icon, String actionCommand, String tip, ActionListener actionListener) {
        final Image image = icon.getImage();
        final Image scaledImage = image.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        final ImageIcon scaledIcon = new ImageIcon(scaledImage);
        final JButton result = new JButton(scaledIcon);
        result.setFocusPainted(false);
        result.setActionCommand(actionCommand);
        result.setToolTipText(tip);
        result.addActionListener(actionListener);
        return result;
    }
}
