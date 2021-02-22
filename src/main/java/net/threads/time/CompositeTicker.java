package net.threads.time;

import java.util.List;

public class CompositeTicker implements TickerListener {
    private final List<TickerListener> tickers;

    public CompositeTicker(List<TickerListener> listeners) {
        this.tickers = listeners;
    }

    @Override
    public void tick() {
        for (TickerListener ticker : tickers) {
            ticker.tick();
        }
    }
}
