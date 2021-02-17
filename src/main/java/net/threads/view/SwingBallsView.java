package net.threads.view;

import javax.swing.*;
import java.awt.*;

public class SwingBallsView implements Runnable {
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

        this.canvas = new BallsCanvas();
        canvas.setBackground(new Color(254, 0, 0));
        canvas.setBounds(new Rectangle(100, 100, 200, 200));
        jFrame.add(canvas, BorderLayout.CENTER);

        jFrame.pack();
        jFrame.setVisible(true);
    }

    public BallsCanvas getCanvas() {
        return canvas;
    }
}
