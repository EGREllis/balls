package net.threads.model.initial;

import net.threads.model.BallState;
import net.threads.model.Bounds;

public interface InitialBallStateFactory {
    BallState initialBallState(int nBalls, Bounds bounds);
}
