package ru.nsu.kondrenko.controller;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener, MenuListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println(actionEvent);
    }

    @Override
    public void menuSelected(MenuEvent menuEvent) {
        System.out.println(menuEvent);
    }

    @Override
    public void menuDeselected(MenuEvent menuEvent) {
        System.out.println(menuEvent);
    }

    @Override
    public void menuCanceled(MenuEvent menuEvent) {
        System.out.println(menuEvent);
    }
}
