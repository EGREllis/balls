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
        buffer.append(String.format("Color %1$s (x,y): (%2$f,%3$f) (dx,dy): (%4$f %5$f) radius: %5$f", color, cx, cy, dx, dy, radius));
        return buffer.toString();
    }

    public Ball advance(Bounds bounds) {
        Ball newBall = null;
        double newX = cx+dx;
        double newY = cy+dy;
        double newDx = dx;
        double newDy = dy;
        if (cx + radius > bounds.getWidth()) {
            newX = bounds.getWidth() - radius;
            if (radius * 2 > bounds.getWidth()) {
                System.out.println(String.format("Excluding ball coloured: %1$s", color));
                System.out.flush();
                return null;
            }
        }
        if (cy + radius > bounds.getHeight()) {
            newY = bounds.getHeight() - radius;
            if (radius * 2 > bounds.getHeight()) {
                System.out.println(String.format("Excluding ball coloured: %1$s", color));
                System.out.flush();
                return null;
            }
        }
        if (newX - radius < 0) {
            // Calculate overshoot for rebound
            double overshoot = (newX - radius) * -1;
            newX = radius + overshoot;
            newDx = dx * -1;
            newBall = new Ball(color, radius, newX, newY, newDx, newDy);
            logContact("Left", newBall, bounds);
        } else if (newX + radius > bounds.getWidth()) {
            // Calculate overshoot for rebound
            double overshoot = (newX + radius) - bounds.getWidth();
            newX = bounds.getWidth() - overshoot;
            newDx = dx * -1;
            newBall = new Ball(color, radius, newX, newY, newDx, newDy);
            logContact("Right", newBall, bounds);
        }
        if (newY - radius < 0) {
            // Calculate overshoot for rebound
            double overshoot = (newY - radius) * -1;
            newY = radius + overshoot;
            newDy = dy * -1;
            newBall = new Ball(color, radius, newX, newY, newDx, newDy);
            logContact("Top", newBall, bounds);
        } else if (newY + radius > bounds.getHeight()) {
            // Calculate overshoot for rebound
            double overshoot = (newY + radius) - bounds.getHeight();
            newY = bounds.getHeight() - overshoot - radius;
            newDy = dy * -1;
            newBall = new Ball(color, radius, newX, newY, newDx, newDy);
            logContact("Bottom", newBall, bounds);
        }
        return new Ball(color, radius, newX, newY, newDx, newDy);
    }

    private void logContact(String message, Ball next, Bounds bound) {
        System.out.println(String.format("%1$s\tprev: %2$s\n\t\tnext: %3$s color: %4$s bound: (%5$d, %6$d)", message, getBallGeometry(this), getBallGeometry(next), color, bound.getWidth(), bound.getHeight()));
        System.out.flush();
    }

    private String getBallGeometry(Ball ball) {
        return String.format("(x,y): (%1$f, %2$f) (dx,dy): (%3$f, %4$f) radius: %5$f", ball.cx, ball.cy, ball.dx, ball.dy, ball.radius);
    }
}
