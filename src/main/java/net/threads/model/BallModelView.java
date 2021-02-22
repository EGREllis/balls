package net.threads.model;

import net.threads.time.TickerListener;

public interface BallModelView extends TickerListener {
    BallState getBallState();
}
