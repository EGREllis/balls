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
            long currentTime = System.currentTimeMillis();
            long timeDiff = currentTime - previousTick;
            if (timeDiff > TICK_RESOLUTION) {
                listener.tick();
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
        }
    }
}
