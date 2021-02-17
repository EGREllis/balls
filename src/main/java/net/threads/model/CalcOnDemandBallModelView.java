package net.threads.model;

import java.util.List;

public class CalcOnDemandBallModelView implements BallModelView {
    private final BallCalculator calculator;
    private final List<Ball> seed;

    public CalcOnDemandBallModelView(BallCalculator calculator, List<Ball> seed) {
        this.calculator = calculator;
        this.seed = seed;
    }

    @Override
    public List<Ball> getBalls() {
        List<Ball> balls = calculator.advance(seed);
        seed.clear();
        seed.addAll(balls);
        return balls;
    }
}
