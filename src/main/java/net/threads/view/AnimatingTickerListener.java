package net.threads.view;

import net.threads.time.TickerListener;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class AnimatingTickerListener implements TickerListener {
    private final Component component;

    public AnimatingTickerListener(Component component) {
        this.component = component;
    }

    @Override
    public void tick() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    Graphics g = component.getGraphics();
                    component.update(g);
                }
            });
        } catch (InterruptedException | InvocationTargetException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
            System.err.flush();
        }
    }
}
