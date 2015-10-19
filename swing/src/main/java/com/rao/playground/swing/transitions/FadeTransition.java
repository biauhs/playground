package com.rao.playground.swing.transitions;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Created by IntelliJ IDEA.
 * User: choudmf
 * Date: 29/04/11
 * Time: 17:00
 * To change this template use File | Settings | File Templates.
 */
public class FadeTransition extends AbstractBaseTransition{
    @Override
    public void prepareAndDrawImage(Graphics2D graphics, AffineTransform transform, BufferedImage backImage, BufferedImage frontImage) {
        graphics.drawImage(frontImage, 0, 0, null);
        AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transitionPosition);graphics.setComposite(composite);
        graphics.drawImage(backImage, (int)0, (int)0, null);
    }
}
