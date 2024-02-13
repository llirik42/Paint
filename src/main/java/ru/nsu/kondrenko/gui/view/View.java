package ru.nsu.kondrenko.gui.view;

import ru.nsu.kondrenko.model.Context;
import ru.nsu.kondrenko.model.ContextListener;
import ru.nsu.kondrenko.model.ContextState;

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
    private static final FileFilter filesOpeningFilter = new FileNameExtensionFilter("Images", "png", "bmp", "jpeg", "jpg", "gif");

    private final Map<ContextState, Consumer<Context>> contextStateChangeHandlers;

    private final JFileChooser openingFileChooser;
    private final JFileChooser savingFileChooser;
    private final JFrame frame;
    private final DrawingArea drawingArea;
    private final ToolsArea toolsArea;

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
            put(ContextState.OPENING_FILE, View.this::onOpeningFile);
            put(ContextState.SAVING_FILE, View.this::onSavingFile);
            put(ContextState.EXITING, View.this::onExiting);
            put(ContextState.REPAINTING, View.this::onRepainting);
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

    private void onRepainting(Context context) {
        repaint(context.getImage());
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

        final MenuArea menuArea = new MenuArea(actionListener);
        frame.setJMenuBar(menuArea.getMenuBar());

        frame.add(toolsArea, BorderLayout.NORTH);

        frame.pack();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private void repaint(BufferedImage image) {
        drawingArea.resizeSoftly(image.getWidth(), image.getHeight());
        drawingArea.setImage(image);
        drawingArea.repaint();
        frame.pack();
    }
}
