package net.threads;

import net.threads.model.Ball;
import net.threads.model.Bounds;
import org.junit.Test;

import java.awt.*;

public class BallAdvanceTest {
    private final double TOLERANCE = 0.001;

    @Test
    public void when_noBoundary_then_ballAdvances() {
        Bounds bounds = new Bounds(100, 100);
        Ball ball = new Ball(new Color(0, 0 ,0), 20, 30, 30, 10, -10);
        Ball next = ball.advance(bounds);

        assertDouble(next.getCx(), 40, "cx");
        assertDouble(next.getCy(), 20, "cy");
    }

    @Test
    public void when_xBoundary_then_ballRebounds() {
        Bounds bounds = new Bounds(50, 50);
        Ball ball = new Ball(new Color(0,0,0), 10, 10, 20, -10, 10);
        Ball next = ball.advance(bounds);

        assertDouble(next.getCx(), 20, "cx");
        assertDouble(next.getCy(), 30, "cy");
        assertDouble(next.getDx(), 10, "dx");
        assertDouble(next.getDy(), 10, "dy");
    }

    @Test
    public void when_yBoundary_then_ballRebounds() {
        Bounds bounds = new Bounds(50, 50);
        Ball ball = new Ball(new Color(0,0,0), 10, 20, 10, 10, -10);
        Ball next = ball.advance(bounds);

        assertDouble(next.getCx(), 30, "cx");
        assertDouble(next.getCy(), 20, "cy");
        assertDouble(next.getDx(), 10, "dx");
        assertDouble(next.getDy(), 10, "dy");
    }

    private void assertDouble(double actual, double expected, String label) {
        assert Math.abs(actual - expected) < TOLERANCE : String.format("Actual: %1$f Expected: %2$f Label: %3$s", actual, expected, label);
    }
}
