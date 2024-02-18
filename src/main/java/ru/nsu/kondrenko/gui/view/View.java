package ru.nsu.kondrenko.gui.view;

import ru.nsu.kondrenko.gui.controller.DialogWindowController;
import ru.nsu.kondrenko.model.context.Context;
import ru.nsu.kondrenko.model.context.ContextAction;
import ru.nsu.kondrenko.model.context.ContextListener;
import ru.nsu.kondrenko.model.context.ContextTools;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

public class View implements ContextListener {
    private static final Font HELP_ABOUT_FONT = new Font("Go", Font.PLAIN, 14);
    private static final String FILE_OPENING_ERROR_MESSAGE = "Cannot open file";
    private static final String FILE_SAVING_ERROR_MESSAGE = "Cannot save file";
    private static final String COLOR_SELECTION_DIALOG_TITLE = "Color";
    private static final String THICKNESS_SELECTION_DIALOG_TITLE = "Thickness";
    private static final String NUMBER_OF_VERTICES_SELECTION_DIALOG_TITLE = "Number of vertices";
    private static final String RADIUS_SELECTION_DIALOG_TITLE = "Radius";
    private static final String ROTATION_DIALOG_TITLE = "Rotation";
    private static final String ERROR_DIALOG_TITLE = "Error";
    private static final String HELP_DIALOG_TITLE = "Help";
    private static final String ABOUT_DIALOG_TITLE = "About";

    private final Map<ContextAction, Consumer<Context>> contextStateChangeHandlers = new EnumMap<>(ContextAction.class);
    private final DialogWindowController dialogWindowController;
    private final JFileChooser imagesOpeningFileChoose;
    private final JFileChooser pngSavingFileChooser;
    private final JTextArea aboutTextArea;
    private final MenuArea menuArea;
    private final ToolsArea toolsArea;
    private final DrawingArea drawingArea;
    private final JFrame frame;
    private final int minThickness;
    private final int maxThickness;
    private final int minNumberOfVertices;
    private final int maxNumberOfVertices;
    private final int minRadius;
    private final int maxRadius;
    private final int minRotation;
    private final int maxRotation;

    public View(String viewName,
                int minFrameWidth,
                int minFrameHeight,
                int minThickness,
                int maxThickness,
                int minNumberOfVertices,
                int maxNumberOfVertices,
                int minRadius,
                int maxRadius,
                int minRotation,
                int maxRotation,
                String[] supportedImageFormats,
                ActionListener buttonsListener,
                ActionListener filesActionsListener,
                MouseListener mouseListener,
                ComponentListener drawingAreaResizingListener,
                DialogWindowController dialogWindowController,
                ToolsIconsSupplier toolsIconsSupplier
    ) {
        contextStateChangeHandlers.put(ContextAction.IDLE, View.this::onIdle);
        contextStateChangeHandlers.put(ContextAction.REPAINT, View.this::onRepainting);
        contextStateChangeHandlers.put(ContextAction.OPEN_FILE, View.this::onOpeningFile);
        contextStateChangeHandlers.put(ContextAction.SAVE_FILE, View.this::onSavingFile);
        contextStateChangeHandlers.put(ContextAction.EXIT, View.this::onExiting);
        contextStateChangeHandlers.put(ContextAction.SELECT_COLOR, View.this::onChoosingColor);
        contextStateChangeHandlers.put(ContextAction.SELECT_THICKNESS, View.this::onSelectingThickness);
        contextStateChangeHandlers.put(ContextAction.SELECT_NUMBER_OF_VERTICES, View.this::onSelectingNumberOfVertices);
        contextStateChangeHandlers.put(ContextAction.SELECT_RADIUS, View.this::onSelectingRadius);
        contextStateChangeHandlers.put(ContextAction.SELECT_ROTATION, View.this::onSelectingRotation);
        contextStateChangeHandlers.put(ContextAction.HANDLE_ERROR, View.this::onError);
        contextStateChangeHandlers.put(ContextAction.DISPLAY_HELP, View.this::onDisplayingHelp);
        contextStateChangeHandlers.put(ContextAction.DISPLAY_ABOUT, View.this::onDisplayingAbout);

        this.dialogWindowController = dialogWindowController;
        this.imagesOpeningFileChoose = createImagesOpeningChooser(filesActionsListener, supportedImageFormats);
        this.pngSavingFileChooser = createPNGSavingChooser(filesActionsListener);
        this.aboutTextArea = createAboutTextArea();
        this.menuArea = new MenuArea(buttonsListener);
        this.toolsArea = new ToolsArea(toolsIconsSupplier, buttonsListener);
        this.drawingArea = new DrawingArea(mouseListener, drawingAreaResizingListener);
        this.minThickness = minThickness;
        this.maxThickness = maxThickness;
        this.minNumberOfVertices = minNumberOfVertices;
        this.maxNumberOfVertices = maxNumberOfVertices;
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
        this.minRotation = minRotation;
        this.maxRotation = maxRotation;
        this.frame = createFrame(viewName, minFrameWidth, minFrameHeight);
    }

    private static JFileChooser createImagesOpeningChooser(ActionListener actionListener, String[] supportedImageFormats) {
        final FileFilter filesOpeningFilter = new FileNameExtensionFilter("Images", supportedImageFormats);
        final JFileChooser result = new JFileChooser();
        initFileChooser(result, filesOpeningFilter, actionListener);
        return result;
    }

    private static JFileChooser createPNGSavingChooser(ActionListener actionListener) {
        final FileFilter filesSavingFilter = new FileSavingFilter("PNG");
        final JFileChooser result = new JFileChooser();
        initFileChooser(result, filesSavingFilter, actionListener);
        return result;
    }

    private static void initFileChooser(JFileChooser fileChooser, FileFilter fileFilter, ActionListener actionListener) {
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(fileFilter);
        fileChooser.addActionListener(actionListener);
    }

    private static JTextArea createAboutTextArea() {
        final JTextArea result = new JTextArea();

        result.setFont(HELP_ABOUT_FONT);
        result.setBackground(null);
        result.append("""
                Simple Paint 1.0.0
                
                Created by Kondrenko Kirill in February 2024 as task for the course "engineering and computer graphics" in Novosibirsk State University""");


        return result;
    }

    private static String getIncorrectValueMessage(int minValue, int maxValue) {
        return String.format("Incorrect value! It must be in [%d, %d]", minValue, maxValue);
    }

    public void show() {
        frame.setVisible(true);
    }

    @Override
    public void onContextActionChange(Context context) {
        contextStateChangeHandlers.get(context.getAction()).accept(context);
    }

    @Override
    public void onContextToolChange(Context context) {
        final ContextTools tool = context.getTool();

        toolsArea.unselectAll();

        switch (tool) {
            case DRAW_LINE -> {
                this.menuArea.setDrawLineSelected();
                this.toolsArea.setDrawLineSelected();
            }

            case DRAW_POLYGON -> {
                this.menuArea.setDrawPolygonSelected();
                this.toolsArea.setDrawPolygonSelected();
            }

            case DRAW_STAR -> {
                this.menuArea.setDrawStarSelected();
                this.toolsArea.setDrawStarSelected();
            }

            case FILL -> {
                this.menuArea.setFillSelected();
                this.toolsArea.setFillSelected();
            }
        }
    }

    private void onIdle(Context context) {
        // Method is empty because we don't want to do anything with action "IDLE" of context
    }

    private void onRepainting(Context context) {
        repaint(context.getImage());
    }

    private void onOpeningFile(Context context) {
        final int code = imagesOpeningFileChoose.showOpenDialog(frame);

        if (code == JFileChooser.ERROR_OPTION) {
            showError(FILE_OPENING_ERROR_MESSAGE);
        }
    }

    private void onSavingFile(Context context) {
        final int code = pngSavingFileChooser.showSaveDialog(frame);

        if (code == JFileChooser.ERROR_OPTION) {
            showError(FILE_SAVING_ERROR_MESSAGE);
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

    private void appendToPane(JTextPane tp, String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }

    private void onDisplayingHelp(Context context) {

        // TODO:
        final SimpleTextPane textPane = new SimpleTextPane(HELP_ABOUT_FONT);
        textPane.appendPlain("""
                Menu
                File
                Open — opens file
                Save — save file
                Exit — exit
                Tools
                Line
                Polygon
                Star
                Fill
                Clear
                Select color
                Select thickness
                Select number of vertices
                Select radius
                Select rotation

                Info
                Help — show help
                About — show about""");

        JOptionPane.showMessageDialog(
                null,
                textPane,
                HELP_DIALOG_TITLE,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void onDisplayingAbout(Context context) {
        JOptionPane.showMessageDialog(
                null,
                aboutTextArea,
                ABOUT_DIALOG_TITLE,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private Color showChooseColorDialogWindow(Color startColor) {
        return JColorChooser.showDialog(null, COLOR_SELECTION_DIALOG_TITLE, startColor);
    }

    private int showSelectThicknessDialogWindow(int startThickness) {
        return selectInteger(THICKNESS_SELECTION_DIALOG_TITLE, minThickness, maxThickness, startThickness);
    }

    private int showSelectNumberOfVerticesDialogWindow(int startNumberOfVertices) {
        return selectInteger(NUMBER_OF_VERTICES_SELECTION_DIALOG_TITLE, minNumberOfVertices, maxNumberOfVertices, startNumberOfVertices);
    }

    private int showSelectRadiusDialogWindow(int startRadius) {
        return selectInteger(RADIUS_SELECTION_DIALOG_TITLE, minRadius, maxRadius, startRadius);
    }

    private int showSelectRotationDialogWindow(int startRotation) {
        return selectInteger(ROTATION_DIALOG_TITLE, minRotation, maxRotation, startRotation);
    }

    private int selectInteger(String message, int minValue, int maxValue, int startValue) {
        final String incorrectValueMessage = getIncorrectValueMessage(minValue, maxValue);
        final JSlider slider = new JSlider(minValue, maxValue, startValue);
        final JTextField textField = new JTextField(Integer.toString(startValue));

        dialogWindowController.reset();
        dialogWindowController.setSlider(slider);
        dialogWindowController.setTextField(textField);
        slider.addChangeListener(dialogWindowController);
        textField.getDocument().addDocumentListener(dialogWindowController);
        textField.addActionListener(dialogWindowController);

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

        if (dialogWindowController.hasError()) {
            showError(incorrectValueMessage);
            return startValue;
        }

        return dialogWindowController.hasValue() ? dialogWindowController.getValue() : startValue;
    }

    private void showError(String errorMessage) {
        JOptionPane.showMessageDialog(
                null,
                errorMessage,
                ERROR_DIALOG_TITLE,
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

    private JFrame createFrame(String frameName, int minFrameWidth, int minFrameHeight) {
        final JFrame result = new JFrame(frameName);
        result.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        result.setMinimumSize(new Dimension(minFrameWidth, minFrameHeight));

        final JScrollPane scrollPane = new JScrollPane(drawingArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        result.add(scrollPane, BorderLayout.CENTER);
        result.setJMenuBar(menuArea.getMenuBar());
        result.add(toolsArea, BorderLayout.NORTH);
        result.pack();

        return result;
    }
}
