package net.threads.model;

import java.util.ArrayList;
import java.util.List;

public class BallCalculatorImpl implements BallCalculator, BoundListener {
    private volatile Bounds bounds;

    public BallCalculatorImpl(Bounds bounds) {
        this.bounds = bounds;
    }

    @Override
    public BallState advance(BallState balls) {
        Bounds myBounds = bounds;
        List<Ball> results = new ArrayList<>(balls.getBalls().size());
        for (Ball ball : balls.getBalls()) {
            Ball newBall = ball.advance(myBounds);
            if (newBall != null) {
                results.add(newBall);
            }
        }
        return new BallState(results, myBounds);
    }

    @Override
    public void boundsChanged(Bounds bounds) {
        System.out.println(String.format("Bounds have been changed: %1$s", bounds));
        this.bounds = bounds;
    }
}
