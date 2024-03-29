package net.threads.model;

import java.awt.*;

public class Ball {
    private final long id;
    private final Color color;
    private final Color outline;
    private final double radius;
    private final double cx;
    private final double cy;
    private final double dx;
    private final double dy;

    public Ball(final long id, Color color, Color outline, double radius, double cx, double cy, double dx, double dy) {
        this.id = id;
        this.color = color;
        this.outline = outline;
        this.radius = radius;
        this.cx = cx;
        this.cy = cy;
        this.dx = dx;
        this.dy = dy;
    }

    public long getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public Color getOutline() {
        return outline;
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

    private boolean leftBoundaryCollisionBeforeMove(Bounds bounds) {
        return cx - radius <= 0;    //TODO: Properly verify that if the left boundary is moved, its new location is x=0
    }

    private boolean rightBoundaryCollisionBeforeMove(Bounds bounds) {
        return cx + radius >= bounds.getWidth();
    }

    private boolean topBoundaryCollisionBeforeMove(Bounds bounds) {
        return cy - radius <= 0;    //TODO: Properly verify that if the top boundary is moved, its new location is y=0
    }

    private boolean bottomBoundaryCollisionBeforeMove(Bounds bounds) {
        return cy + radius >= bounds.getHeight();
    }

    public Ball advance(Bounds bounds) {
        Ball newBall = null;
        double newX = cx+dx;
        double newY = cy+dy;
        double newDx = dx;
        double newDy = dy;
        // Checks if the ball is overlapping or in contact with right boundary before a move
        if (leftBoundaryCollisionBeforeMove(bounds) || rightBoundaryCollisionBeforeMove(bounds)) {
            // Decided to ignore overlap on the pre-move check (if all collision checking and initial position is valid, this will only happen when the user resizes the boundary
            newDx = -dx;
            newX = cx + newDx;
        } else if (leftBoundaryCollisionBeforeMove(bounds) && rightBoundaryCollisionBeforeMove(bounds)) {
            // User has made the window so small that this ball is colliding with the left and right boundary at the same time!
            return null;
        }
        // Check if the ball is overlapping or in contact with the bottom boundary before a move
        if (topBoundaryCollisionBeforeMove(bounds) || bottomBoundaryCollisionBeforeMove(bounds)) {
            newDy = -dy;
            newY = cy + newDy;
        } else if (topBoundaryCollisionBeforeMove(bounds) && bottomBoundaryCollisionBeforeMove(bounds)) {
            return null;
        }

        if (newX - radius < 0) {
            // Calculate overshoot for rebound
            double overshoot = (newX - radius) * -1;
            newX = radius + overshoot;
            newDx = dx * -1;
            newBall = new Ball(id, color, outline, radius, newX, newY, newDx, newDy);
            logContact("Left", newBall, bounds);
        } else if (newX + radius > bounds.getWidth()) {
            // Calculate overshoot for rebound
            double overshoot = (newX + radius) - bounds.getWidth();
            newX = bounds.getWidth() - overshoot;
            newDx = dx * -1;
            newBall = new Ball(id, color, outline, radius, newX, newY, newDx, newDy);
            logContact("Right", newBall, bounds);
        }
        if (newY - radius < 0) {
            // Calculate overshoot for rebound
            double overshoot = (newY - radius) * -1;
            newY = radius + overshoot;
            newDy = dy * -1;
            newBall = new Ball(id, color, outline, radius, newX, newY, newDx, newDy);
            logContact("Top", newBall, bounds);
        } else if (newY + radius > bounds.getHeight()) {
            // Calculate overshoot for rebound
            double overshoot = (newY + radius) - bounds.getHeight();
            newY = bounds.getHeight() - overshoot - radius;
            newDy = dy * -1;
            newBall = new Ball(id, color, outline, radius, newX, newY, newDx, newDy);
            logContact("Bottom", newBall, bounds);
        }
        return new Ball(id, color, outline, radius, newX, newY, newDx, newDy);
    }

    private void logContact(String message, Ball next, Bounds bound) {
        System.out.println(String.format("%1$s\tprev: %2$s\n\t\tnext: %3$s color: %4$s bound: (%5$d, %6$d)", message, getBallGeometry(this), getBallGeometry(next), color, bound.getWidth(), bound.getHeight()));
        System.out.flush();
    }

    private String getBallGeometry(Ball ball) {
        return String.format("(x,y): (%1$f, %2$f) (dx,dy): (%3$f, %4$f) radius: %5$f", ball.cx, ball.cy, ball.dx, ball.dy, ball.radius);
    }
}
