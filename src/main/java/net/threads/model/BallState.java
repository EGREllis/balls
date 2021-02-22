package net.threads.model;

import java.util.List;

public class BallState {
    private final List<Ball> balls;
    private final Bounds bounds;

    public BallState(List<Ball> balls, Bounds bounds) {
        this.balls = balls;
        this.bounds = bounds;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public Bounds getBounds() {
        return bounds;
    }
}
