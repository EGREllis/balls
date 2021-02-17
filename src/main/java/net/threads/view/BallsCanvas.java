package net.threads.view;

import net.threads.model.Ball;
import net.threads.model.BallModelView;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;

import java.util.List;

public class BallsCanvas extends Canvas {
    private final BallModelView modelView;

    public BallsCanvas(BallModelView modelView) {
        this.modelView = modelView;
    }

    public void paint(Graphics graphics) {
        Rectangle bound = graphics.getClip().getBounds();
        List<Ball> balls = modelView.getBalls();
        for (Ball ball : balls) {
            int x = (int)(ball.getCx() - ball.getRadius());
            int y = (int)(ball.getCy() - ball.getRadius());
            int size = (int)(ball.getRadius() * 2);
            graphics.setColor(ball.getColor());
            graphics.drawOval(x, y, size, size);
        }
        System.out.println(String.format("Bounds (x: %1$f, y: %2$f, height: %3$d, width: %4$d)", bound.getX(), bound.getY(), bound.height, bound.width));
        System.out.flush();
    }

    public void triggerRepaint() {
        repaint();
    }
}
