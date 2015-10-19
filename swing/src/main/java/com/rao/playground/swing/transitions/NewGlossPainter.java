package com.rao.playground.swing.transitions;

import org.jdesktop.swingx.painter.GlossPainter;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 *
 */
public class NewGlossPainter extends GlossPainter {

  private int shineYOffset = 0;

  public NewGlossPainter(int shineYOffset) {
    this.shineYOffset = shineYOffset;
  }

  public int getShineYOffset() {
    return shineYOffset;
  }

  public void setShineYOffset(int shineYOffset) {
    this.shineYOffset = shineYOffset;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doPaint(Graphics2D g, Object component, int width, int height) {
    if (getPaint() != null) {
      Ellipse2D ellipse = new Ellipse2D.Double(-width / 2.0, (height / 2.7) - shineYOffset,
                                               width * 2.0, height * 2.0);

      Area gloss = new Area(ellipse);
      if (getPosition() == GlossPosition.TOP) {
        Area area = new Area(new Rectangle(0, 0,
                                           width, height));
        area.subtract(new Area(ellipse));
        gloss = area;
      }
      /*
      if(getClip() != null) {
          gloss.intersect(new Area(getClip()));
      }*/
      g.setPaint(getPaint());
      g.fill(gloss);
    }
  }


}
