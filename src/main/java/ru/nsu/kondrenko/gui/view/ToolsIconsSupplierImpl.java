package ru.nsu.kondrenko.gui.view;

import ru.nsu.kondrenko.model.ImageReader;

import javax.swing.*;
import java.io.IOException;

public class ToolsIconsSupplierImpl implements ToolsIconsSupplier {
    private final ImageReader reader;

    private final ImageIcon lineToolIcon;
    private final ImageIcon stampToolIcon;
    private final ImageIcon fillToolIcon;
    private final ImageIcon clearToolIcon;
    private final ImageIcon changeColorToolIcon;
    private final ImageIcon changeThicknessToolIcon;
    private final ImageIcon changeStampToolIcon;
    private final ImageIcon changeRadiusToolIcon;
    private final ImageIcon changeRotationToolIcon;

    public ToolsIconsSupplierImpl() throws IOException {
        reader = new ImageReader();
        lineToolIcon = loadImageIcon("/line.png");
        stampToolIcon = loadImageIcon("/hexagon.png");
        fillToolIcon = loadImageIcon("/fill.png");
        clearToolIcon = loadImageIcon("/eraser.png");
        changeColorToolIcon = loadImageIcon("/palette.png");
        changeThicknessToolIcon = loadImageIcon("/thickness.png");
        changeStampToolIcon = loadImageIcon("/swap.png");
        changeRadiusToolIcon = loadImageIcon("/radius.png");
        changeRotationToolIcon = loadImageIcon("/rotation.png");
    }

    @Override
    public ImageIcon getLineToolIcon() {
        return lineToolIcon;
    }

    @Override
    public ImageIcon getStampToolIcon() {
        return stampToolIcon;
    }

    @Override
    public ImageIcon getFillToolIcon() {
        return fillToolIcon;
    }

    @Override
    public ImageIcon getClearToolIcon() {
        return clearToolIcon;
    }

    @Override
    public ImageIcon getChangeColorToolIcon() {
        return changeColorToolIcon;
    }

    @Override
    public ImageIcon getChangeThicknessToolIcon() {
        return changeThicknessToolIcon;
    }

    @Override
    public ImageIcon getChangeStampToolIcon() {
        return changeStampToolIcon;
    }

    @Override
    public ImageIcon getChangeRadiusToolIcon() {
        return changeRadiusToolIcon;
    }

    @Override
    public ImageIcon getChangeRotationToolIcon() {
        return changeRotationToolIcon;
    }

    private ImageIcon loadImageIcon(final String path) throws IOException {
        return new ImageIcon(reader.readAsResource(path));
    }
}
