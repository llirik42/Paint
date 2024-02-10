package ru.nsu.kondrenko.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageReader {
    public BufferedImage read(File file) throws ImageReadingException {
        try (InputStream inputStream = new FileInputStream(file)) {
            return ImageIO.read(inputStream);
        } catch (IOException exception) {
            throw new ImageReadingException(exception);
        }
    }

    public BufferedImage readAsResource(String resourceName) throws ImageReadingException {
        try (InputStream inputStream = this.getClass().getResourceAsStream(resourceName)) {
            if (inputStream == null) {
                throw new ImageReadingException(String.format("Resource \"%s\" not found", resourceName));
            }

            return ImageIO.read(inputStream);
        } catch (IOException exception) {
            throw new ImageReadingException(exception);
        }
    }
}
