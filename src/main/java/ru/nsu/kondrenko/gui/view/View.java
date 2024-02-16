package ru.nsu.kondrenko.gui.view;

import ru.nsu.kondrenko.model.Context;
import ru.nsu.kondrenko.model.ContextListener;
import ru.nsu.kondrenko.model.ContextState;
import ru.nsu.kondrenko.model.ContextTools;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class View implements ContextListener {
    // TODO: вынести поддерживаемые типы в модель и читать оттуда
    private static final FileFilter filesOpeningFilter = new FileNameExtensionFilter("Images", "png", "bmp", "jpeg", "jpg", "gif");

    private final Map<ContextState, Consumer<Context>> contextStateChangeHandlers;

    private final JFileChooser openingFileChooser;
    private final JFileChooser savingFileChooser;
    private final JFrame frame;
    private final DrawingArea drawingArea;
    private final ToolsArea toolsArea;

    private MenuArea menuArea;

    public View(String viewName,
                int minWidth,
                int minHeight,
                BufferedImage startImage,
                ActionListener buttonsListener,
                ActionListener filesActionsListener,
                MouseListener mouseListener,
                ComponentListener drawingAreaListener
    ) throws IOException {

        contextStateChangeHandlers = new HashMap<>() {{
            put(ContextState.IDLE, View.this::onIdle);
            put(ContextState.REPAINTING, View.this::onRepainting);
            put(ContextState.OPENING_FILE, View.this::onOpeningFile);
            put(ContextState.SAVING_FILE, View.this::onSavingFile);
            put(ContextState.EXITING, View.this::onExiting);
            put(ContextState.CHOOSING_COLOR, View.this::onChoosingColor);
            put(ContextState.CHOOSING_THICKNESS, View.this::onSelectingThickness);
            put(ContextState.CHOOSING_NUMBER_OF_SIDES, View.this::onSelectingNumberOfVertices);
            put(ContextState.CHOOSING_RADIUS, View.this::onSelectingRadius);
            put(ContextState.CHOOSING_ROTATION, View.this::onSelectingRotation);
            put(ContextState.ERROR, View.this::onError);
            put(ContextState.DISPLAYING_HELP, View.this::onDisplayingHelp);
            put(ContextState.DISPLAYING_ABOUT, View.this::onDisplayingAbout);
        }};

        final ToolsIconsSupplier toolsIconsSupplier = new ToolsIconsSupplierImpl();
        toolsArea = new ToolsArea(toolsIconsSupplier, buttonsListener);

        drawingArea = new DrawingArea(startImage.getWidth(), startImage.getHeight(), mouseListener);
        drawingArea.addComponentListener(drawingAreaListener);

        openingFileChooser = new JFileChooser();
        initFileChooser(filesActionsListener);

        savingFileChooser = new JFileChooser();
        savingFileChooser.addActionListener(filesActionsListener);

        frame = new JFrame(viewName);
        initFrame(minWidth, minHeight, buttonsListener);
    }

    public void show() {
        frame.setVisible(true);
    }

    @Override
    public void onContextStateChange(Context context) {
        contextStateChangeHandlers.get(context.getState()).accept(context);
    }

    @Override
    public void onContextToolChange(Context context) {
        final ContextTools tool = context.getTool();

        toolsArea.unselectAll();

        switch (tool) {
            case DRAW_LINE -> {
                this.menuArea.setDrawLineEnabled();
                this.toolsArea.setDrawLine();
            }

            case DRAW_POLYGON -> {
                this.menuArea.setDrawPolygonEnabled();
                this.toolsArea.setDrawPolygon();
            }

            case DRAW_STAR -> {
                this.menuArea.setDrawStarEnabled();
                this.toolsArea.setDrawStar();
            }

            case FILL -> {
                this.menuArea.setFillEnabled();
                this.toolsArea.setFill();
            }
        }
    }

    private void onIdle(Context context) {

    }

    private void onRepainting(Context context) {
        repaint(context.getImage());
    }

    private void onOpeningFile(Context context) {
        final int code = openingFileChooser.showOpenDialog(frame);

        if (code == JFileChooser.ERROR_OPTION) {
            showError("Cannot open file");
        }
    }

    private void onSavingFile(Context context) {
        final int code = savingFileChooser.showSaveDialog(frame);

        if (code == JFileChooser.ERROR_OPTION) {
            showError("Cannot save file");
        }
    }

    private void onExiting(Context context) {
        frame.setVisible(false);
        frame.dispose();
    }

    private void onChoosingColor(Context context) {
        final Color newContextColor = showChooseColorDialogWindow(context.getColor());

        if (newContextColor != null) {
            context.setColor(newContextColor);
        }
    }

    private void onSelectingThickness(Context context) {
        context.setThickness(showSelectThicknessDialogWindow(context.getThickness()));
    }

    private void onSelectingNumberOfVertices(Context context) {
        context.setNumberOfVertices(showSelectNumberOfVerticesDialogWindow(context.getNumberOfVertices()));
    }

    private void onSelectingRadius(Context context) {
        context.setRadius(showSelectRadiusDialogWindow(context.getRadius()));
    }

    private void onSelectingRotation(Context context) {
        context.setRotation(showSelectRotationDialogWindow(context.getRotation()));
    }

    private void onError(Context context) {
        showError(context.getErrorMessage());
    }

    private void onDisplayingHelp(Context context) {
        // TODO: сделать нормальный help

        JOptionPane.showMessageDialog(
                null,
                "Help",
                "Help",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void onDisplayingAbout(Context context) {
        // TODO: сделать нормальный help

        JOptionPane.showMessageDialog(
                null,
                "About",
                "About",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void initFileChooser(ActionListener actionListener) {
        openingFileChooser.setAcceptAllFileFilterUsed(false);
        openingFileChooser.addChoosableFileFilter(filesOpeningFilter);
        openingFileChooser.addActionListener(actionListener);
    }

    private void initFrame(int minWidth, int minHeight, ActionListener actionListener) {
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(minWidth, minHeight));

        final JScrollPane scrollPane = new JScrollPane(drawingArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane, BorderLayout.CENTER);

        menuArea = new MenuArea(actionListener);
        frame.setJMenuBar(menuArea.getMenuBar());

        frame.add(toolsArea, BorderLayout.NORTH);

        frame.pack();
    }

    private Color showChooseColorDialogWindow(Color startColor) {
        return JColorChooser.showDialog(null, "Color selection", startColor);
    }

    private int showSelectThicknessDialogWindow(int startThickness) {
        return selectInteger("Select thickness", 1, 20, startThickness);
    }

    private int showSelectNumberOfVerticesDialogWindow(int startNumberOfVertices) {
        return selectInteger("Select number of vertices", 3, 20, startNumberOfVertices);
    }

    private int showSelectRadiusDialogWindow(int startRadius) {
        return selectInteger("Select radius", 10, 1000, startRadius);
    }

    private int showSelectRotationDialogWindow(int startRotation) {
        return selectInteger("Select rotation", 0, 360, startRotation);
    }

    private int selectInteger(String message, int minValue, int maxValue, int startValue) {
        final String incorrectValueMessage = String.format("Incorrect value! It must be in [%d, %d]", minValue, maxValue);

        final int[] result = {startValue};

        final JSlider slider = new JSlider(minValue, maxValue, startValue);
        final JTextField textField = new JTextField(Integer.toString(startValue));

        slider.addChangeListener(changeEvent -> {
            final JSlider source = (JSlider)changeEvent.getSource();
            result[0] = source.getValue();
            textField.setText(Integer.toString(result[0]));
        });
        textField.addActionListener(actionEvent -> {
            final String actionCommand = actionEvent.getActionCommand();

            try {
                result[0] = Integer.parseInt(actionCommand);

                if (result[0] < minValue || result[0] > maxValue) {
                    throw new Exception();
                }
            } catch (Exception exception) {
                showError(incorrectValueMessage);
            }
        });

        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(textField);
        panel.add(slider);

        JOptionPane.showConfirmDialog(null,
                panel,
                message,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        try {
            result[0] = Integer.parseInt(textField.getText());

            if (result[0] < minValue || result[0] > maxValue) {
                throw new Exception();
            }
        } catch (Exception exception) {
            showError(incorrectValueMessage);
        }

        return result[0];
    }

    private void showError(String errorMessage) {
        JOptionPane.showMessageDialog(
                null,
                errorMessage,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private void repaint(BufferedImage image) {
        drawingArea.resizeSoftly(image.getWidth(), image.getHeight());
        drawingArea.setImage(image);
        drawingArea.repaint();
        drawingArea.revalidate();
        frame.pack();
    }
}
