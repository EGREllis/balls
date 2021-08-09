package net.threads.model.initial;

import net.threads.model.Ball;
import net.threads.model.BallState;
import net.threads.model.Bounds;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PrimaryColorInitialBallStateFactory implements InitialBallStateFactory {
    @Override
    public BallState initialBallState(int nBalls, Bounds bounds) {
        List<Ball> balls = new ArrayList<>(nBalls);

        Color[] colors = {
                new Color(255,255,255),
                new Color(255, 0 ,0),
                new Color(0,255,0),
                new Color(0,0,255),
                new Color(255,255,0),
                new Color(255,0,255),
                new Color(0,255,255)
        };
        int maxBalls = colors.length * colors.length;
        if (nBalls >= maxBalls) {
            throw new IllegalArgumentException("Currently nBalls must be positive and less than "+maxBalls);
        }

        for (int i = 0; i < nBalls; i++) {
            int colorIndex = i % colors.length;
            int outlineIndex = i / colors.length;
            int radius = 10 + (int)(Math.random() * 20);
            int dx = (int)(Math.random() * 20) - 10;
            int dy = (int)(Math.random() * 20) - 10;
            int cx = (int)(Math.random() * (bounds.getWidth() - 2 * radius)) + radius;
            int cy = (int)(Math.random() * (bounds.getHeight() - 2 * radius)) + radius;
            balls.add(new Ball(i, colors[colorIndex], colors[outlineIndex], radius, cx, cy, dx, dy));
        }

        return new BallState(balls, bounds);
    }
}
