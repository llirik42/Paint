package ru.nsu.kondrenko.gui.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawingArea extends JPanel {
    private BufferedImage image;

    void setImage(BufferedImage image) {
        this.image = image;
    }

    public DrawingArea() {
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
