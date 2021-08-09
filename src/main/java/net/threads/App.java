package net.threads;

import net.threads.model.*;
import net.threads.model.initial.InitialBallStateFactory;
import net.threads.model.initial.PrimaryColorInitialBallStateFactory;
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
        CommandLineArguments cli = CommandLineArguments.parseArgs(args);

        Bounds bounds = cli.getBounds();
        InitialBallStateFactory initialBallStateFactory = new PrimaryColorInitialBallStateFactory();
        BallState seed = initialBallStateFactory.initialBallState(cli.getNBalls(), bounds);

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
