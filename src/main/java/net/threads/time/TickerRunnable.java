package net.threads.time;;

public class TickerRunnable implements Runnable {
    private final long TICK_RESOLUTION;
    private final TickerListener listener;

    public TickerRunnable(long tickResolution, TickerListener listener) {
        this.TICK_RESOLUTION = tickResolution;
        this.listener = listener;
    }

    @Override
    public void run() {
        long previousTick = System.currentTimeMillis();
        while(true) {
            boolean ticked = false;
            long currentTime = System.currentTimeMillis();
            long timeDiff = currentTime - previousTick;
            if (timeDiff > TICK_RESOLUTION) {
                listener.tick();
                ticked = true;
                previousTick = currentTime;
            }
            long timeToWait = previousTick + TICK_RESOLUTION - currentTime;
            try {
                Thread.sleep(timeToWait / 2);
            } catch (InterruptedException ie) {
                System.err.println(ie.getMessage());
                ie.printStackTrace(System.err);
                System.err.flush();
            }
            System.out.println(String.format("Ticker: %1$d Tick?: %2$s Diff: %3$d Resolution: %4$d PreviousTick: %5$d", currentTime, ticked, timeDiff, TICK_RESOLUTION, previousTick));
            System.out.flush();
        }
    }
}
