package net.threads.view;

import net.threads.model.BallModelView;
import net.threads.time.TickerListener;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class SwingBallsView implements Runnable, TickerListener {
    private final BallModelView modelView;
    private JFrame jFrame;
    private BallsCanvas canvas;

    public SwingBallsView(BallModelView modelView) {
        this.modelView = modelView;
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
        canvas.setBounds(new Rectangle(0, 0, 400, 400));
        jFrame.add(canvas, BorderLayout.CENTER);

        jFrame.pack();
        jFrame.setVisible(true);
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
