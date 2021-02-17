package net.threads.view;

import net.threads.time.TickerListener;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class AnimatingTickerListener implements TickerListener {
    private final Container container;

    public AnimatingTickerListener(Container container) {
        this.container = container;
    }

    @Override
    public void tick() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    Graphics g = container.getGraphics();
                    container.update(g);
                }
            });
        } catch (InterruptedException | InvocationTargetException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
            System.err.flush();
        }
    }
}
