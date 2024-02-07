package ru.nsu.kondrenko.gui.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ToolsArea extends JPanel {
    private static final Color BACKGROUND_COLOR = new Color(0.85f, 0.85f, 0.85f);

    public ToolsArea(ActionListener actionListener) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(BACKGROUND_COLOR);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridheight = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        final JButton button1 = new JButton("color1");
        final JButton button2 = new JButton("color2");

        add(button1, gridBagConstraints);
        add(button2, gridBagConstraints);
    }
}
