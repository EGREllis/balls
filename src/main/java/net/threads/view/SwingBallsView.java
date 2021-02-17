package net.threads.view;

import net.threads.model.BallModelView;
import net.threads.model.SingleRandomModelView;
import net.threads.time.TickerListener;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class SwingBallsView implements Runnable, TickerListener {
    private JFrame jFrame;
    private BallsCanvas canvas;

    @Override
    public void run() {
        buildGui();
    }

    private void buildGui() {
        this.jFrame = new JFrame("Balls threading exercise");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new BorderLayout());

        BallModelView modelView = new SingleRandomModelView();
        this.canvas = new BallsCanvas(modelView);
        canvas.setBackground(new Color(0, 0, 0));
        canvas.setBounds(new Rectangle(100, 100, 200, 200));
        jFrame.add(canvas, BorderLayout.CENTER);

        jFrame.pack();
        jFrame.setVisible(true);
    }

    public BallsCanvas getCanvas() {
        return canvas;
    }

    public JFrame getJFrame() {
        return jFrame;
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
