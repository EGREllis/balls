package net.threads.model;

import java.util.ArrayList;
import java.util.List;

public class BallCalculatorImpl implements BallCalculator {
    @Override
    public List<Ball> advance(List<Ball> balls) {
        List<Ball> results = new ArrayList<>(balls.size());
        for (Ball ball : balls) {
            results.add(ball.advance());
        }
        return results;
    }
}
