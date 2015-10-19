package com.rao.playground.swing.transitions;

import org.jdesktop.swingx.graphics.GraphicsUtilities;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Transition implementation which provides a flip effect.
 */

public class FlipTransition extends AbstractBaseTransition {

  private boolean shading = true;

  // if true the component will recede into the distance slightly as it flips
  private boolean flipIntoDistance = true;

  protected double scale;

  public void setTransitionPosition(float transitionPosition) {
    super.setTransitionPosition(transitionPosition);
    scale = Math.abs(transitionPosition - 0.5f) * 2.0f;
  }

  public boolean isUsingShading() {
    return shading;
  }

  public void setUseShading(boolean shading) {
    this.shading = shading;
  }

  protected void onTransitionPositionChange() {
    scale = Math.abs(this.transitionPosition - 0.5f) * 2.0f;
  }

  public boolean isFlipIntoDistance() {
    return flipIntoDistance;
  }

  public void setFlipIntoDistance(boolean flipIntoDistance) {
    this.flipIntoDistance = flipIntoDistance;
  }

  /**
   * Prepare and draw the image representing the current visual state.
   *
   * @param graphics the Graphics2D context used to draw the image
   * @param transform  the transform to be used for image transformations
   * @param backImage  the image of the back component
   * @param frontImage the image of the front component
   */
  public void prepareAndDrawImage(Graphics2D graphics, AffineTransform transform,
                                  BufferedImage backImage, BufferedImage frontImage) {

    BufferedImage image = transitionPosition > 0.5f ? backImage : frontImage;

    if (shading) {
      image = shadeImage(image, transitionPosition);
    }

    if (transitionAxis == TransitionAxis.HORIZONTAL) {
      applyHorizontalFlipTransform(transform, image.getWidth(), image.getHeight());
    } else {
      applyVerticalFlipTransform(transform, image.getWidth(), image.getHeight());
    }

    graphics.drawImage(image, transform, null);
  }

  /**
   * Setup the transform ready for use during a horizontal flip.
   *
   * @param transform the transform to modify
   * @param width     the image width
   * @param height    the image height
   * @return the modified transform
   */
  private AffineTransform applyHorizontalFlipTransform(AffineTransform transform, int width, int height) {

    // start with the identity transform...
    transform.setToIdentity();

    // calculate how much we need to scale vertically - only applicable if flipInDistance is true
    double distanceScale = flipIntoDistance ? (1.0f - ((1.0f - scale) / 5f)) : 1.0f;

    // translate according to the horizontal and vertical scaling
    transform.translate((width / 2) * (1 - scale), (height - (height * distanceScale)) / 2);

    // scale according to the horizontal flip and distance scaling required
    transform.scale(scale, distanceScale);

    return transform;
  }

  /**
   * Setup the transform ready for use during a vertical flip.
   *
   * @param transform the transform to modify
   * @param width     the image width
   * @param height    the image height
   * @return the modified transform
   */
  private AffineTransform applyVerticalFlipTransform(AffineTransform transform, int width, int height) {

    // start with the identity transform...
    transform.setToIdentity();

    // calculate how much we need to scale horizontally - only applicable if flipInDistance is true
    double distanceScale = flipIntoDistance ? (1.0f - ((1.0f - scale) / 5f)) : 1.0f;

    // translate according to the vertical and horizontal scaling
    transform.translate((width - (width * distanceScale)) / 2, (height / 2) * (1 - scale));

    // scale according to the vertical flip and distance scaling required
    transform.scale(distanceScale, scale);

    return transform;
  }

  /**
   * Shade the specified image using an alpha gradient to black so as to simulate a shadow for parts
   * of the image appearing further away.
   *
   * Where the shadow section appears is dependent on the FlipDirection property...
   *
   * For a forward flip the panel flips from left to right, so the right hand edge disappears into the distance
   * and hence should be shadowed.
   *
   * For a backward flip the panel flips from right to left, so the left hand edge disappears into the distance
   * and hence should be shadowed.
   *
   * The same applies to vertical flips with a forward flip being classed as where the top comes forward and the
   * bottom disappears into the distance, and vice versa for a backward flip.
   *
   * @param image the image to apply the shading to
   * @param angle the rotation angle
   * @return a shaded image
   */
  private BufferedImage shadeImage(BufferedImage image, double angle) {

    BufferedImage rval = GraphicsUtilities.createCompatibleTranslucentImage(image.getWidth(), image.getHeight());
    Graphics2D g = rval.createGraphics();
    g.drawImage(image, 0, 0, null);

    Color t = new Color(0, 0, 0, 0);

    double alpha = 1.0f - (Math.abs(angle - 0.5f) * 2.0f);
    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha));

    GradientPaint gradientPaint = null;
    if (transitionAxis == TransitionAxis.HORIZONTAL) {
      boolean shadowOnRight = (transitionDirection == TransitionDirection.FORWARD && angle < 0.5) ||
                              (transitionDirection == TransitionDirection.BACKWARD && angle >= 0.5);
      if (shadowOnRight) {
        // shadow on right hand side
        gradientPaint = new GradientPaint(0, 0, t, image.getWidth(), 0, Color.BLACK);
      } else {
        // shadow on left hand side
        gradientPaint = new GradientPaint(0, 0, Color.BLACK, image.getWidth(), 0, t);
      }
    } else {
      boolean shadowOnBottom = (transitionDirection == TransitionDirection.FORWARD && angle < 0.5) ||
                               (transitionDirection == TransitionDirection.BACKWARD && angle >= 0.5);

      if (shadowOnBottom) {
        // shadow on bottom
        gradientPaint = new GradientPaint(0, 0, t, 0, image.getHeight(), Color.BLACK);
      } else {
        // shadow on top
        gradientPaint = new GradientPaint(0, 0, Color.BLACK, 0, image.getHeight(), t);
      }
    }
    g.setPaint(gradientPaint);

    g.fillRect(0, 0, image.getWidth(), image.getHeight());

    g.dispose();
    return rval;
  }

}
