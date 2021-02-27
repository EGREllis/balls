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

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(String.format("Color %1$s (x,y): (%2$f,%3$f) (dx,dy): (%4$f %5$f)", color, cx, cy, dx, dy));
        return buffer.toString();
    }

    public Ball advance(Bounds bounds) {
        double newX = cx+dx;
        double newY = cy+dy;
        double newDx = dx;
        double newDy = dy;
        if (newX - radius < 0) {
            // Calculate overshoot for rebound
            double overshoot = (newX - radius) * -1;
            newX = radius + overshoot;
            newDx = dx * -1;
            logContact("Left");
        } else if (newX + radius > bounds.getWidth()) {
            // Calculate overshoot for rebound
            double overshoot = (newX + radius) - bounds.getWidth();
            newX = bounds.getWidth() - overshoot;
            newDx = dx * -1;
            logContact("Right");
        }
        if (newY - radius < 0) {
            // Calculate overshoot for rebound
            double overshoot = (newY - radius) * -1;
            newY = radius + overshoot;
            newDy = dy * -1;
            logContact("Top");
        } else if (newY + radius > bounds.getHeight()) {
            // Calculate overshoot for rebound
            double overshoot = (newY + radius) - bounds.getHeight();
            newY = bounds.getHeight() - overshoot - radius;
            newDy = dy * -1;
            logContact("Bottom");
        }
        return new Ball(color, radius, newX, newY, newDx, newDy);
    }

    private void logContact(String message) {
        System.out.println(String.format("%1$s %2$s", message, this.toString()));
        System.out.flush();
    }
}
