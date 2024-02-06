package ru.nsu.kondrenko.view;

import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.event.ActionListener;

public class MenuArea {
    private final JPopupMenu menu;

    public MenuArea(ActionListener actionListener) {
        menu = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("New Project...");

        menu.add(menuItem);
    }

    JPopupMenu getJMenuBar() {
        return menu;
    }
}
