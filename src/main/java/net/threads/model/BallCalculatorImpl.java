package net.threads.model;

import java.util.ArrayList;
import java.util.List;

public class BallCalculatorImpl implements BallCalculator, BoundListener {
    private Bounds bounds;

    public BallCalculatorImpl(Bounds bounds) {
        this.bounds = bounds;
    }

    @Override
    public BallState advance(BallState balls) {
        List<Ball> results = new ArrayList<>(balls.getBalls().size());
        for (Ball ball : balls.getBalls()) {
            results.add(ball.advance());
        }
        return new BallState(results, bounds);
    }

    @Override
    public void boundsChanged(Bounds bounds) {
        System.out.println(String.format("Bounds have been changed: %1$s", bounds));
        this.bounds = bounds;
    }
}
