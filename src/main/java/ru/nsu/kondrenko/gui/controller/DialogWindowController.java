package ru.nsu.kondrenko.gui.controller;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogWindowController implements ChangeListener, ActionListener, DocumentListener {
    private JTextField textField;
    private JSlider slider;
    private int minValue;
    private int maxValue;
    private int value;
    private boolean hasError;

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public void setSlider(JSlider slider) {
        this.slider = slider;
        minValue = slider.getMinimum();
        maxValue = slider.getMaximum();
    }

    public int getValue() {
        return value;
    }

    public void resetError() {
        hasError = false;
    }

    public boolean hasError() {
        return hasError;
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        final int oldValue = value;
        final int newValue = slider.getValue();
        value = newValue;

        if (oldValue != newValue) {
            updateValueOfTextArea();
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        readValueFromString(actionEvent.getActionCommand());
    }

    @Override
    public void insertUpdate(DocumentEvent documentEvent) {
        handleDocumentTextChange(documentEvent.getDocument());
    }

    @Override
    public void removeUpdate(DocumentEvent documentEvent) {
        handleDocumentTextChange(documentEvent.getDocument());
    }

    @Override
    public void changedUpdate(DocumentEvent documentEvent) {
        // Method is empty because JTextField doesn't change it's state (only text)
    }

    private void handleDocumentTextChange(Document document) {
        try {
            readValueFromString(document.getText(0, document.getLength()));
        } catch (BadLocationException ignored) {
            // Impossible case
        }
    }

    private void readValueFromString(String string) {
        try {
            final int tmp = Integer.parseInt(string);
            hasError = tmp < minValue || tmp > maxValue;

            if (!hasError) {
                value = tmp;
                updateValueOfSlider();
            }
        } catch (Exception exception) {
            hasError = true;
        }
    }

    private void updateValueOfSlider() {
        slider.setValue(value);
    }

    private void updateValueOfTextArea() {
        textField.setText(Integer.toString(value));
    }
}
