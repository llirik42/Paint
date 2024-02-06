package ru.nsu.kondrenko.view;

import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {
    public View(String viewName, int minWidth, int minHeight, ActionListener actionListener) {
        final JFrame jFrame = new JFrame(viewName);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.setMinimumSize(new Dimension(minWidth, minHeight));

        final DrawingArea drawingArea = new DrawingArea();
        jFrame.add(drawingArea, BorderLayout.CENTER);

        final ButtonsArea buttonsArea = new ButtonsArea(actionListener);
        jFrame.add(buttonsArea, BorderLayout.NORTH);

        final MenuArea menuArea = new MenuArea(actionListener);
        jFrame.add(menuArea.getJMenuBar());

        jFrame.setResizable(false);

        jFrame.pack();
    }
}
