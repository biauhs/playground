package com.rao.playground.swing.transitions;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Abstract base implementation of Transition effects.
 */

public abstract class AbstractBaseTransition implements Transition {

  // current position of the transition in the range of 0.0 - 1.0
  protected float transitionPosition = 0f;
  protected TransitionAxis transitionAxis = TransitionAxis.HORIZONTAL;
  protected TransitionDirection transitionDirection = TransitionDirection.FORWARD;

  public float getTransitionPosition() {
    return transitionPosition;
  }

  public void setTransitionPosition(float transitionPosition) {
    this.transitionPosition = transitionPosition;
  }

  public TransitionAxis getTransitionAxis() {
    return transitionAxis;
  }

  public void setTransitionAxis(TransitionAxis transitionAxis) {
    this.transitionAxis = transitionAxis;
  }

  public TransitionDirection getTransitionDirection() {
    return transitionDirection;
  }

  public void setTransitionDirection(TransitionDirection transitionDirection) {
    this.transitionDirection = transitionDirection;
  }

  public abstract void prepareAndDrawImage(Graphics2D graphics, AffineTransform transform,
                                           BufferedImage backImage, BufferedImage frontImage);
}
