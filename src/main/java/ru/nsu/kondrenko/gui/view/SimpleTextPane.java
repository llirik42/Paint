package ru.nsu.kondrenko.gui.view;

import javax.swing.*;
import java.awt.*;

public class SimpleTextPane extends JTextPane {
    public SimpleTextPane(Font font) {
        setFont(font);
        setEditable(false);
        setBackground(null);
    }

    public void appendPlain(String text) {
        final String oldText = getText();
        setText(oldText + text);
    }
}
