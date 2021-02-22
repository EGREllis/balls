package net.threads.view;

import net.threads.model.Ball;
import net.threads.model.BallModelView;

import java.awt.Canvas;
import java.awt.Graphics;

import java.util.List;

public class BallsCanvas extends Canvas {
    private final BallModelView modelView;

    public BallsCanvas(BallModelView modelView) {
        this.modelView = modelView;
    }

    public void paint(Graphics graphics) {
        List<Ball> balls = modelView.getBallState().getBalls();
        for (Ball ball : balls) {
            int x = (int)(ball.getCx() - ball.getRadius());
            int y = (int)(ball.getCy() - ball.getRadius());
            int size = (int)(ball.getRadius() * 2);
            graphics.setColor(ball.getColor());
            graphics.drawOval(x, y, size, size);
        }
    }
}
