package com.rao.playground.swing.transitions;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Created by shuaibrao on 19/10/2015.
 */
public class ZoomTransition extends AbstractBaseTransition {

    @Override
    public void prepareAndDrawImage(Graphics2D graphics, AffineTransform transform, BufferedImage fromImage, BufferedImage toImage) {
        double fromScale;
        double toScale;

        if (transitionDirection == TransitionDirection.FORWARD) {
            fromScale = 1.0f + (transitionPosition * 4.0f);
            toScale = 0.1f + (transitionPosition * 0.9f);
        } else {
            fromScale = 0.1f + ((1.0f - transitionPosition) * 0.9f);
            toScale = 1.0f + ((1.0f - transitionPosition * 4.0f));
        }

        double fromAlpha = 1.0f - transitionPosition;
        double toAlpha = transitionPosition;

        doImage(graphics, transform, fromImage, fromScale, fromAlpha);
        doImage(graphics, transform, toImage, toScale, toAlpha);
    }

    private void doImage(Graphics2D graphics, AffineTransform transform, BufferedImage image, double scale, double alpha) {
        transform.setToIdentity();
        int fromWidth = image.getWidth();
        int xOffset = (int) (fromWidth - fromWidth * scale) / 2;
        int fromHieght = image.getHeight();
        int yOffset = (int) (fromHieght - fromHieght * scale) / 2;
        transform.translate(xOffset, yOffset);
        transform.scale(scale, scale);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha));
        graphics.drawImage(image, transform, null);
    }
}
