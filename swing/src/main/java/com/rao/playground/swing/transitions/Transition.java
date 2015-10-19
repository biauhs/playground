package com.rao.playground.swing.transitions;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Interface to something which implements a transition effect associated with a CardTransitionPanel.
 */

public interface Transition {

  /**
   * Prepare and draw the image representing the current visual state.
   *
   * @param graphics   the Graphics2D context used to draw the image
   * @param transform  the transform to be used for image transformations
   * @param backImage  the image of the back component
   * @param frontImage the image of the front component
   */
  void prepareAndDrawImage(Graphics2D graphics, AffineTransform transform,
                           BufferedImage backImage, BufferedImage frontImage);

  float getTransitionPosition();

  void setTransitionPosition(float position);

  TransitionAxis getTransitionAxis();

  void setTransitionAxis(TransitionAxis transitionAxis);

  TransitionDirection getTransitionDirection();

  void setTransitionDirection(TransitionDirection transitionDirection);

}
