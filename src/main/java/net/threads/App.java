package net.threads;

import net.threads.time.TickerRunnable;
import net.threads.view.SwingBallsView;

/**
 * Hello world!
 *
 */
public class App {
    private static long DELAY = 1000L;

    public static void main( String[] args ) {
        SwingBallsView view = new SwingBallsView();

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
