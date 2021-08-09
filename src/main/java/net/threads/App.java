package net.threads;

import net.threads.model.*;
import net.threads.time.CompositeTicker;
import net.threads.time.TickerListener;
import net.threads.time.TickerRunnable;
import net.threads.view.SwingBallsView;
import net.threads.view.SwingResizeComponentListener;

import java.awt.*;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main( String[] args ) {
        List<Ball> balls = new ArrayList<>();

        CommandLineArguments cli = CommandLineArguments.parseArgs(args);

        if (cli.getNBalls() == 3) {
            balls.add(new Ball(1, new Color(255, 0 ,0), new Color(0,0,0), 10, 20, 20, 10, 10));
            balls.add(new Ball(2, new Color(0,255, 0), new Color(0,0,0), 20, 40, 40, 20, 5));
            balls.add(new Ball(3, new Color(0,0,255), new Color(0,0,0), 30, 60, 60, -10, -20));
        }
        Bounds bounds = cli.getBounds();
        BallState seed = new BallState(balls, bounds);
        BallCalculatorImpl calculator = new BallCalculatorImpl(bounds);
        ComponentListener boundListener = new SwingResizeComponentListener(calculator);
        BallModelView ballModelView = new CalcOnDemandBallModelView(calculator, seed);
        SwingBallsView view = new SwingBallsView(ballModelView, boundListener, cli.getBounds());
        view.run();

        try {
            Thread.sleep(cli.getTickCount());
            List<TickerListener> listeners = new ArrayList<>(2);
            listeners.add(ballModelView);
            listeners.add(view);
            TickerListener listener = new CompositeTicker(listeners);
            Thread ticker = new Thread(new TickerRunnable(cli.getTickCount(), listener));
            ticker.start();
        } catch (InterruptedException ie) {
            System.err.println(ie.getMessage());
            ie.printStackTrace(System.err);
            System.err.flush();
        }
    }
}
