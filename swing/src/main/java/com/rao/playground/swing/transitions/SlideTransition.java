package com.rao.playground.swing.transitions;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Transition implementation which provides a slide effect.
 */

public class SlideTransition extends AbstractBaseTransition {

  /**
   * Prepare and draw the image representing the current visual state.
   *
   * @param graphics the Graphics2D context used to draw the image
   */
  @Override
  public void prepareAndDrawImage(Graphics2D graphics, AffineTransform transform, BufferedImage backImage, BufferedImage frontImage) {

      if (getTransitionAxis()==TransitionAxis.HORIZONTAL)
      {
          if (getTransitionDirection()==TransitionDirection.FORWARD)
          {
            graphics.drawImage(frontImage,(int)(0 + frontImage.getWidth()*transitionPosition),(int)0,null);
            graphics.drawImage(backImage,(int)(0 - backImage.getWidth()*(1-transitionPosition)),(int)0,null);
          }
          else
          {
               graphics.drawImage(frontImage, (int)(0 - frontImage.getWidth()*transitionPosition), (int)0, null);
               graphics.drawImage(backImage, (int)(0 + backImage.getWidth()*(1-transitionPosition)), 0, null);
          }
      }
      else
      {
          if (getTransitionDirection()==TransitionDirection.FORWARD)
          {
              graphics.drawImage(frontImage,0,(int)(0+backImage.getHeight()*transitionPosition),null);
              graphics.drawImage(backImage,0,(int)(0-backImage.getHeight()*(1-transitionPosition)),null);

          }
          else
          {
              graphics.drawImage(frontImage,0,(int)(0-backImage.getHeight()*transitionPosition),null);
              graphics.drawImage(backImage,0,(int)(0+backImage.getHeight()*(1-transitionPosition)),null);
          }
      }

  }

}
