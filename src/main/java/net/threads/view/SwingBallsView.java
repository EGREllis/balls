package net.threads.view;

import net.threads.model.BallModelView;
import net.threads.model.Bounds;
import net.threads.time.TickerListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentListener;
import java.lang.reflect.InvocationTargetException;

public class SwingBallsView implements Runnable, TickerListener {
    private final BallModelView modelView;
    private final ComponentListener resizeListener;
    private Bounds bounds;
    private JFrame jFrame;
    private BallsCanvas canvas;

    public SwingBallsView(BallModelView modelView, ComponentListener resizeListener, Bounds bounds) {
        this.bounds = bounds;
        this.modelView = modelView;
        this.resizeListener = resizeListener;
    }

    @Override
    public void run() {
        buildGui();
    }

    private void buildGui() {
        this.jFrame = new JFrame("Balls threading exercise");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new BorderLayout());

        this.canvas = new BallsCanvas(modelView);
        canvas.setBackground(new Color(0, 0, 0));
        canvas.setBounds(new Rectangle(0, 0, bounds.getWidth(), bounds.getHeight()));
        jFrame.add(canvas, BorderLayout.CENTER);

        jFrame.pack();
        jFrame.setVisible(true);
        canvas.addComponentListener(resizeListener);
    }

    @Override
    public void tick() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    Graphics graphics = canvas.getGraphics();
                    canvas.update(graphics);
                }
            });
        } catch (InterruptedException | InvocationTargetException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}
