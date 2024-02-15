package ru.nsu.kondrenko.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public interface ImageSaver {
    void saveAsPNG(BufferedImage image, File file) throws IOException;
}
