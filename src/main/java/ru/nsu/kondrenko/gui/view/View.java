package ru.nsu.kondrenko.gui.view;

import ru.nsu.kondrenko.model.Context;
import ru.nsu.kondrenko.model.ContextListener;
import ru.nsu.kondrenko.model.ContextState;
import ru.nsu.kondrenko.model.ImageReadingException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class View implements ContextListener {
    private static final FileFilter fileFilter = new FileNameExtensionFilter("Images", "png", "bmp", "jpeg", "jpg", "gif");

    private final Map<ContextState, Consumer<Context>> contextStateChangeHandlers;

    private final JFileChooser fileChooser;
    private final JFrame frame;
    private final DrawingArea drawingArea;

    public View(String viewName, int minWidth, int minHeight, ActionListener actionListener, MouseListener mouseListener) throws ImageReadingException {
        contextStateChangeHandlers = new HashMap<>() {{
            put(ContextState.OPENING_FILE, View.this::onOpeningFile);
            put(ContextState.SAVING_FILE, View.this::onSavingFile);
            put(ContextState.EXITING, View.this::onExiting);
            put(ContextState.REPAINTING, View.this::onRepainting);
        }};

        drawingArea = new DrawingArea();

        fileChooser = new JFileChooser();
        initFileChooser(actionListener);

        frame = new JFrame(viewName);
        initFrame(minWidth, minHeight, actionListener, mouseListener);

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                System.out.println(frame.getWidth() + " " + frame.getHeight());
            }
        });

    }

    public void show() {
        frame.setVisible(true);
    }

    @Override
    public void onContextStateChange(Context context) {
        contextStateChangeHandlers.get(context.getState()).accept(context);
    }

    private void onOpeningFile(Context context) {
        final int code = fileChooser.showOpenDialog(frame);

        if (code == JFileChooser.ERROR_OPTION) {
            showError("Cannot open file");
        }
    }

    private void onSavingFile(Context context) {

    }

    private void onExiting(Context context) {
        frame.setVisible(false);
        frame.dispose();
    }

    private void onRepainting(Context context) {
        final BufferedImage image = context.getImage();
        drawingArea.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        drawingArea.setImage(image);
        drawingArea.repaint();
        frame.pack();
    }

    private void initFileChooser(ActionListener actionListener) {
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(fileFilter);
        fileChooser.addActionListener(actionListener);
    }

    private void initFrame(int minWidth, int minHeight, ActionListener actionListener, MouseListener mouseListener) throws ImageReadingException {
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(minWidth, minHeight));
        frame.addMouseListener(mouseListener);

        final JScrollPane scrollPane = new JScrollPane(drawingArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane, BorderLayout.CENTER);

        final MenuArea menuArea = new MenuArea(actionListener);
        frame.setJMenuBar(menuArea.getMenuBar());

        frame.add(new ToolsArea(new ToolsIconsSupplierImpl(), actionListener), BorderLayout.NORTH);

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
}
