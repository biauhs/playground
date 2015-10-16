package com.rao.playground.swing.utils;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.awt.*;
import java.awt.event.InputEvent;
import java.beans.PropertyChangeEvent;

class DisableInputLayerUI extends LayerUI<JComponent> {
    private boolean isRunning = false;
    @Override public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        if(!isRunning) return;
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
        g2.setPaint(Color.GRAY);
        g2.fillRect(0, 0, c.getWidth(), c.getHeight());
        g2.dispose();
    }
    @Override public void installUI(JComponent c) {
        super.installUI(c);
        JLayer jlayer = (JLayer)c;
        jlayer.setLayerEventMask(
                AWTEvent.MOUSE_EVENT_MASK |
                        AWTEvent.MOUSE_MOTION_EVENT_MASK |
                        AWTEvent.KEY_EVENT_MASK);
    }
    @Override public void uninstallUI(JComponent c) {
        JLayer jlayer = (JLayer)c;
        jlayer.setLayerEventMask(0);
        super.uninstallUI(c);
    }
    @Override public void eventDispatched(AWTEvent e, JLayer l) {
        if(isRunning && e instanceof InputEvent) {
            ((InputEvent)e).consume();
        }
    }
    public void start() {
        if (isRunning) return;
        isRunning = true;
        firePropertyChange("repaint",0,1);
    }
    public void stop() {
        isRunning = false;
        firePropertyChange("repaint",0,1);
    }
    @Override public void applyPropertyChange(PropertyChangeEvent pce, JLayer l) {
        if ("repaint".equals(pce.getPropertyName())) {
            l.repaint();
        }
    }
}
