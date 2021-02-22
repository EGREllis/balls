package net.threads.model;

import java.awt.*;
import java.util.Collections;

public class SingleRandomModelView implements BallModelView {
    private final Bounds bounds;

    public SingleRandomModelView(Bounds bounds) {
        this.bounds = bounds;
    }

    @Override
    public BallState getBallState() {
        return new BallState(Collections.singletonList(new Ball(randomColor(), 25, 50,50, 0, 0)), bounds);
    }

    private Color randomColor() {
        Color color = new Color(randomPigment(), randomPigment(), randomPigment());
        return color;
    }

    private int randomPigment() {
        return (int)(Math.random() * 255);
    }
}
