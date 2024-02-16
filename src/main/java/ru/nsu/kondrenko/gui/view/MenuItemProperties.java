package ru.nsu.kondrenko.gui.view;

public record MenuItemProperties(String label, String actionCommand, MenuItemType type) {
    public MenuItemProperties(String label, String actionCommand) {
        this(label, actionCommand, MenuItemType.DEFAULT_ITEM);
    }
}
