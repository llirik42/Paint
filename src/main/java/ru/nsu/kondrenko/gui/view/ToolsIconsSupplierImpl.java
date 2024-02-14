package ru.nsu.kondrenko.gui.view;

import ru.nsu.kondrenko.model.ImageReader;

import javax.swing.*;
import java.io.IOException;

public class ToolsIconsSupplierImpl implements ToolsIconsSupplier {
    private final ImageReader reader;

    private final ImageIcon drawLineIcon;
    private final ImageIcon drawPolygonIcon;
    private final ImageIcon drawStarIcon;
    private final ImageIcon fillIcon;
    private final ImageIcon clearIcon;
    private final ImageIcon chooseColorIcon;
    private final ImageIcon chooseThicknessIcon;
    private final ImageIcon chooseNumberOfSidesIcon;
    private final ImageIcon chooseRadiusIcon;
    private final ImageIcon chooseRotationIcon;

    private final ImageIcon blackIcon;
    private final ImageIcon whiteIcon;
    private final ImageIcon redIcon;
    private final ImageIcon greenIcon;
    private final ImageIcon blueIcon;
    private final ImageIcon magentaIcon;
    private final ImageIcon pinkIcon;
    private final ImageIcon orangeIcon;
    private final ImageIcon yellowIcon;
    private final ImageIcon cyanIcon;
    private final ImageIcon lightGreyIcon;
    private final ImageIcon darkGreyIcon;

    public ToolsIconsSupplierImpl() throws IOException {
        reader = new ImageReader();
        drawLineIcon = loadImageIcon("/icons/line.png");
        drawPolygonIcon = loadImageIcon("/icons/polygon.png");
        drawStarIcon = loadImageIcon("/icons/star.png");
        fillIcon = loadImageIcon("/icons/fill.png");
        clearIcon = loadImageIcon("/icons/clear.png");
        chooseColorIcon = loadImageIcon("/icons/palette.png");
        chooseThicknessIcon = loadImageIcon("/icons/thickness.png");
        chooseNumberOfSidesIcon = loadImageIcon("/icons/shapes.png");
        chooseRadiusIcon = loadImageIcon("/icons/radius.png");
        chooseRotationIcon = loadImageIcon("/icons/rotation.png");

        blackIcon = loadColorIcon("black");
        whiteIcon = loadColorIcon("white");
        redIcon = loadColorIcon("red");
        greenIcon = loadColorIcon("green");
        blueIcon = loadColorIcon("blue");
        magentaIcon = loadColorIcon("magenta");
        pinkIcon = loadColorIcon("pink");
        orangeIcon = loadColorIcon("orange");
        yellowIcon = loadColorIcon("yellow");
        cyanIcon = loadColorIcon("cyan");
        lightGreyIcon = loadColorIcon("light_grey");
        darkGreyIcon = loadColorIcon("dark_grey");
    }

    @Override
    public ImageIcon getDrawLineIcon() {
        return drawLineIcon;
    }

    @Override
    public ImageIcon getDrawPolygonIcon() {
        return drawPolygonIcon;
    }

    @Override
    public ImageIcon getDrawStarIcon() {
        return drawStarIcon;
    }

    @Override
    public ImageIcon getFillIcon() {
        return fillIcon;
    }

    @Override
    public ImageIcon getClearIcon() {
        return clearIcon;
    }

    @Override
    public ImageIcon getChooseColorIcon() {
        return chooseColorIcon;
    }

    @Override
    public ImageIcon getChooseThicknessIcon() {
        return chooseThicknessIcon;
    }

    @Override
    public ImageIcon getChooseNumberOfSidesIcon() {
        return chooseNumberOfSidesIcon;
    }

    @Override
    public ImageIcon getChooseRadiusIcon() {
        return chooseRadiusIcon;
    }

    @Override
    public ImageIcon getChooseRotationIcon() {
        return chooseRotationIcon;
    }

    @Override
    public ImageIcon getBlackIcon() {
        return blackIcon;
    }

    @Override
    public ImageIcon getWhiteIcon() {
        return whiteIcon;
    }

    @Override
    public ImageIcon getRedIcon() {
        return redIcon;
    }

    @Override
    public ImageIcon getGreenIcon() {
        return greenIcon;
    }

    @Override
    public ImageIcon getBlueIcon() {
        return blueIcon;
    }

    @Override
    public ImageIcon getMagentaIcon() {
        return magentaIcon;
    }

    @Override
    public ImageIcon getPinkIcon() {
        return pinkIcon;
    }

    @Override
    public ImageIcon getOrangeIcon() {
        return orangeIcon;
    }

    @Override
    public ImageIcon getYellowIcon() {
        return yellowIcon;
    }

    @Override
    public ImageIcon getCyanIcon() {
        return cyanIcon;
    }

    @Override
    public ImageIcon getLightGreyIcon() {
        return lightGreyIcon;
    }

    @Override
    public ImageIcon getDarkGreyIcon() {
        return darkGreyIcon;
    }

    private ImageIcon loadColorIcon(final String colorName) throws IOException {
        return new ImageIcon(reader.readAsResource(String.format("/colors/%s.png", colorName)));
    }

    private ImageIcon loadImageIcon(final String path) throws IOException {
        return new ImageIcon(reader.readAsResource(path));
    }
}
