package net.threads;

import net.threads.model.*;
import net.threads.time.TickerRunnable;
import net.threads.view.SwingBallsView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static final long DELAY = 1000L;

    public static void main( String[] args ) {
        List<Ball> seed = new ArrayList<>();
        seed.add(new Ball(new Color(255, 0 ,0), 10, 20, 20, 10, 10));
        seed.add(new Ball(new Color(0,255, 0), 20, 40, 40, 20, 5));
        seed.add(new Ball(new Color(0,0,255), 30, 60, 60, -10, -20));

        BallCalculator calculator = new BallCalculatorImpl();
        BallModelView ballModelView = new CalcOnDemandBallModelView(calculator, seed);
        SwingBallsView view = new SwingBallsView(ballModelView);

        view.run();

        try {
            Thread.sleep(DELAY);
            Thread ticker = new Thread(new TickerRunnable(500L, view));
            ticker.start();
        } catch (InterruptedException ie) {
            System.err.println(ie.getMessage());
            ie.printStackTrace(System.err);
            System.err.flush();
        }
    }
}
