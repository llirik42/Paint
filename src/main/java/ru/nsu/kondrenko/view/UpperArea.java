package ru.nsu.kondrenko.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UpperArea extends JPanel {
    public UpperArea(ActionListener actionListener) {
        setLayout(new GridLayout(2, 1));
        add(new MenuArea(actionListener));
        add(new ToolsArea(actionListener));
    }
}
