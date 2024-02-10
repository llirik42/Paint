package ru.nsu.kondrenko.model;

public class ImageReadingException extends Exception {
    public ImageReadingException(String message) {
        super(message);
    }

    public ImageReadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageReadingException(Throwable cause) {
        super(cause);
    }

    protected ImageReadingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
