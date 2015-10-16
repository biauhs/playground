package com.rao.playground.swing.layouts;

import java.awt.*;

public class FillLayout implements LayoutManager {

    @Override
    public void addLayoutComponent(String str, Component comp) { }

    @Override
    public void removeLayoutComponent(Component co) { }

    @Override
    public Dimension preferredLayoutSize(Container co) {
        int width = 0, height = 0;

        for (Component comp : co.getComponents()) {
            final Point p = comp.getLocation();
            final Dimension dim = comp.getPreferredSize();

            if (dim.width + p.x > width)
                width = dim.width + p.x;

            if (dim.height + p.y > height)
                height = dim.height + p.y;
        }

        return new Dimension(width, height);
    }

    @Override
    public Dimension minimumLayoutSize(Container co) {
        int width = 0, height = 0;

        for (Component comp : co.getComponents()) {
            final Point p = comp.getLocation();
            final Dimension dim = comp.getMinimumSize();

            if (dim.width + p.x > width)
                width = dim.width + p.x;

            if (dim.height + p.y > height)
                height = dim.height + p.y;
        }
        return new Dimension(width, height);
    }

    @Override
    public void layoutContainer(Container co) {
        final Dimension dim = co.getSize();
        for (Component comp : co.getComponents()) {
            final Point p = comp.getLocation();
            comp.setSize(new Dimension(dim.width - p.x, dim.height - p.y));
        }
    }
}
