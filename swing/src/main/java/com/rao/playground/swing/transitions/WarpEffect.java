package com.rao.playground.swing.transitions;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.interpolation.PropertySetter;
import org.jdesktop.swingx.graphics.GraphicsUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 */
public class WarpEffect {

  private GlassPane glassPane;

  private Component component;
  private BufferedImage componentImage;

  private Animator animator;

  private AffineTransform transform = new AffineTransform();

  // position of the warp along it's timeline - from 0 to 1
  private double warpPosition = 0f;
  
  private int animationDuration = 500;

  public WarpEffect() {

    TimingTarget target = new TimingTarget() {

      public void begin() {
        componentImage = getImage(component);
      }

      public void end() {
      }

      public void repeat() {
      }

      public void timingEvent(float fraction) {
      }

    };

    animator = new Animator(animationDuration, target);
    animator.addTarget(new PropertySetter(this, "warpPosition", 0f, 1f));
  }

  public Component getComponent() {
    return component;
  }

  public GlassPane getGlassPane() {
    return glassPane;
  }

  public void setComponent(Component component) {
    this.component = component;
  }

  public BufferedImage getComponentImage() {
    return componentImage;
  }

  private BufferedImage getImage(Component component) {
    BufferedImage image = GraphicsUtilities.createCompatibleTranslucentImage(component.getWidth(), component.getHeight());
    Graphics2D g2 = (Graphics2D) image.getGraphics();
    component.paint(g2);
    return image;
  }

  public void doWarp() {
    animator.start();
  }

  public AffineTransform getTransform() {
    return transform;
  }

  public double getWarpPosition() {
    return warpPosition;
  }

  public void setWarpPosition(double warpPosition) {
    this.warpPosition = warpPosition;

    if (glassPane == null && component != null) {
      SwingUtilities.getRootPane(component).setGlassPane(glassPane = new GlassPane(this));
      glassPane.setVisible(true);
    }

    if (warpPosition == 0) {
      if (!component.isVisible()) {
        component.setVisible(true);
        componentImage = null;
      }
      if (glassPane.isVisible()) {
        glassPane.setVisible(false);
      }
    } else {
      if (componentImage == null) {
        componentImage = getImage(component);
      }
      if (component.isVisible()) {
        component.setVisible(false);
      }
      if (!glassPane.isVisible()) {
        glassPane.setVisible(true);
      }
    }

    glassPane.repaint();
  }

  public void reset() {
    setWarpPosition(0);
  }

  public static class GlassPane extends JComponent {

    private WarpEffect warpEffect;

    public GlassPane(WarpEffect warpEffect) {
      this.warpEffect = warpEffect;
    }

    public void paint(Graphics g) {

      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

      AffineTransform transform = warpEffect.getTransform();

      transform.setToIdentity();

      transform.scale(1.0f - warpEffect.getWarpPosition(), 1.0f - warpEffect.getWarpPosition());
//      transform.rotate(warpEffect.getWarpPosition());

      double translationY = warpEffect.getWarpPosition() * (getHeight() - warpEffect.getComponent().getHeight());
//      System.out.println("translationY = " + translationY);
      transform.translate(0, translationY);

//    g2.clearRect(0, 0, rootPane.getWidth(), rootPane.getHeight());

      g2.drawImage(warpEffect.getComponentImage(), transform, null);
    }

  }

}
