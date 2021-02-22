package net.threads;

import net.threads.model.*;
import net.threads.time.CompositeTicker;
import net.threads.time.TickerListener;
import net.threads.time.TickerRunnable;
import net.threads.view.SwingBallsView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static final long DELAY = 1000L;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;
    private static final String CLI_WIDTH_KEY = "-w";
    private static final String CLI_HEIGHT_KEY = "-h";

    public static void main( String[] args ) {
        List<Ball> balls = new ArrayList<>();
        balls.add(new Ball(new Color(255, 0 ,0), 10, 20, 20, 10, 10));
        balls.add(new Ball(new Color(0,255, 0), 20, 40, 40, 20, 5));
        balls.add(new Ball(new Color(0,0,255), 30, 60, 60, -10, -20));

        Bounds bounds = getBoundsFromCommandLineArgs(args);
        BallState seed = new BallState(balls, bounds);
        BallCalculator calculator = new BallCalculatorImpl(bounds);
        BallModelView ballModelView = new CalcOnDemandBallModelView(calculator, seed);
        SwingBallsView view = new SwingBallsView(ballModelView);

        view.run();

        try {
            Thread.sleep(DELAY);
            List<TickerListener> listeners = new ArrayList<>(2);
            listeners.add(ballModelView);
            listeners.add(view);
            TickerListener listener = new CompositeTicker(listeners);
            Thread ticker = new Thread(new TickerRunnable(500L, listener));
            ticker.start();
        } catch (InterruptedException ie) {
            System.err.println(ie.getMessage());
            ie.printStackTrace(System.err);
            System.err.flush();
        }
    }

    private static Bounds getBoundsFromCommandLineArgs(String[] args) {
        int heightIndex = indexOf(CLI_HEIGHT_KEY, args);
        int widthIndex = indexOf(CLI_WIDTH_KEY, args);

        Bounds bounds = null;
        if (heightIndex > 0 && widthIndex > 0) {
            heightIndex = Integer.parseInt(args[heightIndex+1]);
            widthIndex = Integer.parseInt(args[widthIndex+1]);
            bounds = new Bounds(heightIndex, widthIndex);
        } else {
            bounds = new Bounds(DEFAULT_HEIGHT, DEFAULT_WIDTH);
        }
        return bounds;
    }

    private static int indexOf(String key, String[] args) {
        int index = -1;
        for (int i = 0; i < args.length; i++) {
            if (key.equals(args[i])) {
                index = i;
                break;
            }
        }
        return index;
    }
}
