package com.rao.playground.swing.transitions;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.interpolation.PropertySetter;
import org.jdesktop.swingx.painter.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 */
public class RollingDigitLabel extends JComponent {

  private JLabel currentLabel = new JLabel("0");
  private JLabel nextLabel = new JLabel("0");

  private BufferedImage fromImage;
  private BufferedImage toImage;

  private int currentDigit = 0;
  private int nextDigit = 1;

  private enum Direction {UP, DOWN}

  private Direction direction;

  private RollingDigitLabel linkedLabel;

  private Animator animator;

  private double animationPosition = 0f;

  private GradientPaint normalGradientPaint = new GradientPaint(0.0f, 0.0f, new Color(159, 176, 204),
                                                                0.0f, 40.0f, new Color(51, 51, 51));

  private CompoundPainter normalCompoundPainter = new CompoundPainter(new MattePainter(normalGradientPaint), new GlossPainter());

  public RollingDigitLabel() {

    setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51)));
    
    currentLabel.setFont(currentLabel.getFont().deriveFont(48f));
    currentLabel.setHorizontalAlignment(JLabel.CENTER);

    nextLabel.setFont(nextLabel.getFont().deriveFont(48f));
    nextLabel.setHorizontalAlignment(JLabel.CENTER);

    currentLabel.setSize(50, 50);
    nextLabel.setSize(50, 50);


//    currentLabel.setVisible(true);
//    nextLabel.setVisible(true);

    initialise();
    
    TimingTarget target = new TimingTarget() {

      public void begin() {
        animationPosition = 0f;
      }

      public void end() {
        currentDigit = nextDigit;
      }

      public void timingEvent(float v) {
      }

      public void repeat() {
      }
    };

    animator = new Animator(500, target);
    animator.addTarget(new PropertySetter(this, "animationPosition", 0f, 1f));
    animator.setAcceleration(0.2f);
    animator.setDeceleration(0.4f);
  }

  private void initialise() {
    fromImage = getComponentImage(currentLabel);
    toImage = getComponentImage(nextLabel);
  }

  public Dimension getPreferredSize() {
    return new Dimension(50, 50);
  }

  public RollingDigitLabel(RollingDigitLabel linkedLabel) {
    this();
    this.linkedLabel = linkedLabel;
  }

  public RollingDigitLabel getLinkedLabel() {
    return linkedLabel;
  }

  public void setLinkedLabel(RollingDigitLabel linkedLabel) {
    this.linkedLabel = linkedLabel;
  }

  public void setDigit(int digit) {
    currentDigit = digit;
    nextDigit = digit;
  }

  public void setAnimationPosition(double position) {
    animationPosition = position;
    repaint();
  }

  public void increment() {
    direction = Direction.DOWN;
    currentLabel.setText(Integer.toString(currentDigit));
    nextDigit = ++currentDigit % 10;
    nextLabel.setText(Integer.toString(nextDigit));
    if (nextDigit == 0 && linkedLabel != null) {
      linkedLabel.increment();
    }
    fromImage = getComponentImage(currentLabel);
    toImage = getComponentImage(nextLabel);
    animator.start();
  }

  public void decrement() {
    direction = Direction.UP;
    currentLabel.setText(Integer.toString(currentDigit));
    nextDigit = (--currentDigit + 10) % 10;
    nextLabel.setText(Integer.toString(nextDigit));
    if (nextDigit == 9 && linkedLabel != null) {
      linkedLabel.decrement();
    }
    fromImage = getComponentImage(currentLabel);
    toImage = getComponentImage(nextLabel);
    animator.start();
  }
  
  protected void paintComponent(Graphics g) {

    Graphics2D g2 = (Graphics2D) g;

    normalCompoundPainter.paint(g2, this, this.getWidth(), this.getHeight());

    BufferedImage image = new BufferedImage(getWidth(),
                                            getHeight(),
                                            BufferedImage.TRANSLUCENT);

    Graphics2D ig2 = (Graphics2D) image.getGraphics();

    int offset = (int) (getHeight() * animationPosition);
    int fromHeight = fromImage.getHeight();
    int fromWidth = fromImage.getWidth();
    int toHeight = toImage.getHeight();
    int toWidth = toImage.getWidth();

    if (direction == Direction.DOWN) {
      // draw to image at the top
      ig2.drawImage(toImage,
                    0, 0, toWidth, offset,
                    0, fromHeight - offset, fromWidth, fromHeight,
                    null);
      // draw remainder of from image at the bottom
      ig2.drawImage(fromImage,
                    0, offset, toWidth, toHeight,
                    0, 0, fromWidth, fromHeight - offset,
                    null);
    } else {
      // draw from image at the top
      ig2.drawImage(fromImage,
                    0, 0, fromWidth, fromHeight - offset,
                    0, offset, fromWidth, fromHeight,
                    null);

      // draw to image at the bottom
      ig2.drawImage(toImage,
                    0, toHeight - offset, toWidth, toHeight,
                    0, 0, toWidth, offset,
                    null);
    }

    g2.drawImage(image, 0, 0, null);
  }

  private BufferedImage getComponentImage(Component component) {
    BufferedImage image = new BufferedImage(component.getWidth(),
                                            component.getHeight(),
                                            BufferedImage.TRANSLUCENT);

    Graphics2D g2 = (Graphics2D) image.getGraphics();

    component.paint(g2);

    return image;
  }
}
