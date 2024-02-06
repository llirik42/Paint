package ru.nsu.kondrenko.controller;

import ru.nsu.kondrenko.view.MenuArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Controller implements ActionListener {
    private final Map<String, Runnable> m = new HashMap<>();

    public Controller() {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //m.get(actionEvent.getActionCommand()).run();
    }

    private void handleOpenButton() {
        System.out.println("Open");
    }

    private void handleSaveButton() {
        System.out.println("Save");
    }

    private void handleHelpButton() {
        System.out.println("Help");
    }

    private void handleAboutButton() {
        System.out.println("About");
    }
}
