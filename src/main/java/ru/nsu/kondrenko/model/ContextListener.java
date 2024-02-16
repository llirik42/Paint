package ru.nsu.kondrenko.model;

public interface ContextListener {
    void onContextStateChange(Context context);

    void onContextToolChange(Context context);
}
