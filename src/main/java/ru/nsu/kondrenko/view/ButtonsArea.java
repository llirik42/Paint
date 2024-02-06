package ru.nsu.kondrenko.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonsArea extends JPanel {
    private static final Color BACKGROUND_COLOR = new Color(0.85f, 0.85f, 0.85f);

    public ButtonsArea(ActionListener actionListener) {
        setBackground(BACKGROUND_COLOR);

        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridheight = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        final JButton button1 = new JButton("color1");
        final JButton button2 = new JButton("color2");

        add(button1, gridBagConstraints);
        add(button2, gridBagConstraints);
    }
}
