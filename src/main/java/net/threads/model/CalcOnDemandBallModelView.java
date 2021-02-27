package net.threads.model;

public class CalcOnDemandBallModelView implements BallModelView {
    private final BallCalculator calculator;
    private volatile BallState seed;

    public CalcOnDemandBallModelView(BallCalculator calculator, BallState seed) {
        this.calculator = calculator;
        this.seed = seed;
    }

    @Override
    public BallState getBallState() {
        return seed;
    }

    @Override
    public void tick() {
        this.seed = calculator.advance(seed);
    }
}
