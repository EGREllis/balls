package net.threads.view;

import java.awt.*;

public class BallsCanvas extends Canvas {
    public void paint(Graphics graphics) {
        Rectangle bound = graphics.getClip().getBounds();
        System.out.println(String.format("Bounds (x: %1$f, y: %2$f, height: %3$d, width: %4$d)", bound.getX(), bound.getY(), bound.height, bound.width));
        System.out.flush();
    }

    public void triggerRepaint() {
        repaint();
    }
}
