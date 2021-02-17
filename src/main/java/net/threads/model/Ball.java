package net.threads.model;

import java.awt.*;

public class Ball {
    private final Color color;
    private final double radius;
    private final double cx;
    private final double cy;

    public Ball(Color color, double radius, double cx, double cy) {
        this.color = color;
        this.radius = radius;
        this.cx = cx;
        this.cy = cy;
    }

    public Color getColor() {
        return color;
    }

    public double getRadius() {
        return radius;
    }

    public double getCx() {
        return cx;
    }

    public double getCy() {
        return cy;
    }
}
