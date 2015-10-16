package com.rao.playground.swing.layouts;

import java.awt.*;

public class CenterLayout implements LayoutManager {

    @Override
    public void addLayoutComponent(String name, Component comp) { }

    @Override
    public void removeLayoutComponent(Component comp) { }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        Dimension d = minimumLayoutSize(parent);
        d.setSize(d.getWidth() + 30, d.getHeight() + 30);
        return d;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        Component[] c = parent.getComponents();
        Dimension low = null;
        for (Component comp : c) {
            Dimension p = comp.getPreferredSize();
            if (low == null || p.width > low.width || p.height > low.height) {
                low = p;
            }
        }
        final Insets i = parent.getInsets();
        if (low == null) {
            return new Dimension(i.left + i.right, i.top + i.bottom);
        } else {
            return new Dimension(i.left + i.right + low.width, i.top + i.bottom + low.height);
        }
    }

    @Override
    public void layoutContainer(Container parent) {
        Component[] c = parent.getComponents();
        double w = parent.getWidth() / 2, h = parent.getHeight() / 2;
        Dimension maxDim = new Dimension(
                parent.getWidth() - (parent.getInsets().left + parent.getInsets().right),
                parent.getHeight() - (parent.getInsets().top + parent.getInsets().bottom));
        Point maxPoint = new Point(parent.getInsets().left, parent.getInsets().top);

        if (c.length > 1) {
            throw new IllegalStateException("Only one component allowed!");
        }

        final Component comp = c[0];

        Dimension origin = comp.getPreferredSize();
        if (origin.width > maxDim.width && origin.height > maxDim.height) {
            comp.setSize(maxDim);
            comp.setLocation(maxPoint);
        } else {
            int x = (int) (w - origin.width / 2);
            int y = (int) (h - origin.height / 2);
            if (origin.width > maxDim.width) {
                comp.setSize(new Dimension(maxDim.width, origin.height));
                comp.setLocation(new Point(maxPoint.x, y));
            } else if (origin.height > maxDim.height) {
                comp.setSize(new Dimension(origin.width, maxDim.height));
                comp.setLocation(new Point(x, maxPoint.y));
            } else {
                comp.setSize(origin);
                comp.setLocation(new Point(x, y));
            }
        }
    }
}
