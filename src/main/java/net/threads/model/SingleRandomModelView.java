package net.threads.model;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class SingleRandomModelView implements BallModelView {
    @Override
    public List<Ball> getBalls() {
        return Collections.singletonList(new Ball(randomColor(), 25, 50,50, 0, 0));
    }

    private Color randomColor() {
        Color color = new Color(randomPigment(), randomPigment(), randomPigment());
        return color;
    }

    private int randomPigment() {
        return (int)(Math.random() * 255);
    }
}
