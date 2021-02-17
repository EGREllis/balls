package net.threads.view;

import net.threads.time.TickerListener;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class AnimatingTickerListener implements TickerListener {
    private final BallsCanvas canvas;

    public AnimatingTickerListener(BallsCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void tick() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    canvas.triggerRepaint();
                }
            });
        } catch (InterruptedException | InvocationTargetException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
            System.err.flush();
        }
    }
}
