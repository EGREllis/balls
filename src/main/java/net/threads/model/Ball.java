package net.threads.model;

import java.awt.*;

public class Ball {
    private final Color color;
    private final double radius;
    private final double cx;
    private final double cy;
    private final double dx;
    private final double dy;

    public Ball(Color color, double radius, double cx, double cy, double dx, double dy) {
        this.color = color;
        this.radius = radius;
        this.cx = cx;
        this.cy = cy;
        this.dx = dx;
        this.dy = dy;
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

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public Ball advance() {
        return new Ball(color, radius, cx+dx, cy+dy, dx, dy);
    }
}
