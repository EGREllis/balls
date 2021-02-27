package net.threads.view;

import net.threads.model.BoundListener;
import net.threads.model.Bounds;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class SwingResizeComponentListener implements ComponentListener {
    private BoundListener listener;

    public SwingResizeComponentListener(BoundListener listener) {
        this.listener = listener;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Rectangle rect = e.getComponent().getBounds();
        Bounds bounds = new Bounds(rect.height, rect.width);
        this.listener.boundsChanged(bounds);
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
