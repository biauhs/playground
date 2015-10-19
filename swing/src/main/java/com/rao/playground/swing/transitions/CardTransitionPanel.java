package com.rao.playground.swing.transitions;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.graphics.GraphicsUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

//import org.pushingpixels.trident.Timeline;
//import org.pushingpixels.trident.callback.TimelineCallback;
//import org.pushingpixels.trident.ease.Spline;
//import org.pushingpixels.trident.interpolator.PropertyInterpolator;

/**
 * Abstract base class for a card layout based UI panel which features animated transitions
 * when moving between the display of cards.
 */

public class CardTransitionPanel extends JXPanel {


  protected ExtendedCardLayout layout;

  protected Transition transition = new FlipTransition();

  protected Component frontComponent;
  protected Component backComponent;
  protected BufferedImage frontImage;
  protected BufferedImage backImage;
  private ReentrantLock transitionLock;
  private BufferedImage mainImage;

  protected boolean useAnimation = true;
  //protected Timeline timeline;
    private Animator animator;

  protected AffineTransform transform = new AffineTransform();

  private int currentIndex = 0;

  // speed of animation in ms
  protected int animationDuration = 500;

  private static final Logger logger = Logger.getLogger(CardTransitionPanel.class.getName());

  protected Color darkBackground;

    protected boolean debug;
    private float startAccelerationFraction=0.4f;
    private float endDecelerationFraction=0.4f;

    /**
   * Private property interpolator for float properties because we can't get the Trident
   * prodivded one to work in a WebStart client!
   */
//  private static class FloatPropertyInterpolator implements PropertyInterpolator<Float> {
//    @Override
//    public Class getBasePropertyClass() {
//      return Float.class;
//    }
//
//    @Override
//    public Float interpolate(Float from, Float to, float timelinePosition) {
//      return from + (to - from) * timelinePosition;
//    }
//  }

  public CardTransitionPanel() {
    super(new ExtendedCardLayout());
    layout = (ExtendedCardLayout) getLayout();
    setOpaque(false);
    darkBackground = getBackground().darker();
      transitionLock =new ReentrantLock();
  }

  public Transition getTransition() {
    return transition;
  }

  public void setTransition(Transition transition) {
    this.transition = transition;
  }

  protected void timelineBegin() {
      int currentHeight = getHeight();
      int currentWidth = getWidth();
      if (frontImage==null || frontImage.getHeight()!= currentHeight || frontImage.getWidth()!= currentWidth)
        {
            frontImage = createComponentImage(currentWidth, currentHeight);
        }
        else
        {
           clearImage(frontImage);
        }
        paintComponentOntoImage(frontComponent,frontImage);

         if (backImage==null || backImage.getHeight()!= currentHeight || backImage.getWidth()!= currentWidth)
        {
            backImage = createComponentImage( currentWidth, currentHeight);
        }
        else
        {
            clearImage(backImage);
        }
        paintComponentOntoImage(backComponent,backImage);
  }

   public void clearImage(BufferedImage image)
   {
       int currentHeight = getHeight();
        int currentWidth = getWidth();
        Graphics2D g2D= image.createGraphics();
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
        Rectangle2D.Double rect = new Rectangle2D.Double(0,0,currentWidth,currentHeight);
        g2D.fill(rect);
        g2D.dispose();
   }

  protected void timelineEnd(String cardName) {
    setTransitionPosition(0f);
      frontComponent = layout.getComponentForCard(cardName);
      layout.show(this, cardName);
  }

  /**
   * Create the animation timeline and configure aspects such as duration and ease.
   *
   * @param cardName the name of the card to display at the end of the transition
   */
  private void createTimeline(final String cardName) {
//    timeline = new Timeline(this);
//    timeline.addPropertyToInterpolate(Timeline.<Float> property("transitionPosition").interpolatedWith(new FloatPropertyInterpolator()).on(this).from(0.0f).to(1.0f));
//    timeline.setDuration(animationDuration);
//    timeline.setEase(new Spline(0.4f));
//    timeline.addCallback(new TimelineCallback() {
//
//      public void onTimelineStateChanged(Timeline.TimelineState oldState, Timeline.TimelineState newState, float v, float v1) {
//        switch (newState) {
//          case READY:
//            // animation is about to start
//            timelineBegin();
//            break;
//          case DONE:
//            // animation has completed
//            timelineEnd(cardName);
//            break;
//        }
//      }
//
//      public void onTimelinePulse(float v, float v1) {
//        // do nothing
//      }
//    });
      animator =new Animator(animationDuration,
              new TimingTarget() {
                  //@Override
                  public void timingEvent(float fraction) {
                      setTransitionPosition(fraction);
                  }

                  //@Override
                  public void begin() {
                      timelineBegin();
                  }

                  //@Override
                  public void end() {
                      timelineEnd(cardName);
                  }

                  //@Override
                  public void repeat() {
                      //To change body of implemented methods use File | Settings | File Templates.
                  }
              }
              );
      animator.setAcceleration(startAccelerationFraction);
      animator.setDeceleration(endDecelerationFraction);

  }

  public long getAnimationDuration() {
    return animationDuration;
  }

  public void setAnimationDuration(int animationDuration) {
    if (this.animationDuration != animationDuration) {
      this.animationDuration = animationDuration;
    }
  }

    public float getEndDecelerationFraction() {
        return endDecelerationFraction;
    }

    public void setEndDecelerationFraction(float endDecelerationFraction) {
        this.endDecelerationFraction = endDecelerationFraction;
    }

    public float getStartAccelerationFraction() {
        return startAccelerationFraction;
    }

    public void setStartAccelerationFraction(float startAccelerationFraction) {
        this.startAccelerationFraction = startAccelerationFraction;
    }

    public boolean isUsingAnimation() {
    return this.useAnimation;
  }

  public void setUseAnimation(boolean useAnimation) {
    this.useAnimation = useAnimation;
  }

  /**
   * Transition the panel to display the next card in the stack.
   */
  public void transitionToNext() {
    if (getComponentCount() > 1) {
      frontComponent = getComponent(currentIndex);
      backComponent = getComponent(++currentIndex >= getComponentCount() ? currentIndex = 0 : currentIndex);
      String cardName = layout.getCardNameForComponent(backComponent);
      doTransition(cardName);
    }
  }

  /**
   * Transition the panel to display the previous card in the stack.
   */
  public void transitionToPrevious() {
    if (getComponentCount() > 1) {
      frontComponent = getComponent(currentIndex);
      backComponent = getComponent(--currentIndex < 0 ? currentIndex = getComponentCount() - 1 : currentIndex);
      String cardName = layout.getCardNameForComponent(backComponent);
      doTransition(cardName);
    }
  }

  /**
   * Transition the panel to display the first card in the stack.
   */
  public void transitionToFirst() {
    frontComponent = getComponent(currentIndex);
    backComponent = getComponent(currentIndex = 0);
    String cardName = layout.getCardNameForComponent(backComponent);
    doTransition(cardName);
  }

  /**
   * Transition the panel to display the last card in the stack.
   */
  public void transitionToLast() {
    frontComponent = getComponent(currentIndex);
    backComponent = getComponent(currentIndex = getComponentCount() - 1);
    String cardName = layout.getCardNameForComponent(backComponent);
    doTransition(cardName);
  }

  /**
   * Transition the panel to display a named card in the stack.
   *
   * @param card the name of the card to flip to
   */
  public void transitionToNamed(String card) {
    Component cardComponent = layout.getComponentForCard(card);
    if (cardComponent != null && cardComponent!=frontComponent) {
      frontComponent = getComponent(currentIndex);
      backComponent = cardComponent;
      currentIndex++;
      if (currentIndex>=getComponentCount())
      {
          currentIndex=0;
      }
      doTransition(card);
    }
  }

  /**
   * Initiate the transition.
   *
   * @param cardName the name of the card name to display once the transition is complete.
   */
  private void doTransition(String cardName) {
    if (useAnimation) {
      createTimeline(cardName);
      //timeline.play();
        animator.start();
    } else {
      timelineBegin();
    }
  }

  /**
   * Create an image of the specified width and height.
   *
   * @param width     the image width
   * @param height    the image height
   * @return the resulting image
   */
  private BufferedImage createComponentImage(int width, int height) {
    return GraphicsUtilities.createCompatibleTranslucentImage(width, height);
  }

    private void paintComponentOntoImage(Component component, BufferedImage image) {
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        component.print(g2);
        g2.dispose();
    }

    /**
   * Returns whether a flip is currently animating.
   *
   * @return true if a flip is in progress
   */
  public boolean isAnimating() {
    //return timeline != null && (timeline.getState() == Timeline.TimelineState.PLAYING_FORWARD || timeline.getState() == Timeline.TimelineState.PLAYING_REVERSE);
      return animator!=null && animator.isRunning();
  }

  public TransitionAxis getTransitionAxis() {
    return transition.getTransitionAxis();
  }

  public void setTransitionAxis(TransitionAxis transitionAxis) {
    transition.setTransitionAxis(transitionAxis);
  }

  public TransitionDirection getTransitionDirection() {
    return transition.getTransitionDirection();
  }

  public void setTransitionDirection(TransitionDirection transitionDirection) {
    transition.setTransitionDirection(transitionDirection);
  }

  public double getTransitionPosition() {
    return transition.getTransitionPosition();
  }

  public void setTransitionPosition(float position) {
//    System.out.println("transitionPosition being set to [" + position + "]");
      try
      {
          transitionLock.lock();
          transition.setTransitionPosition(position);
      }
      finally
      {
        transitionLock.unlock();
      }
     repaint();
  }

  /**
   * Paint the component.
   */
  @Override
  public void paint(Graphics g) {
    if ((isAnimating() || !useAnimation) && frontImage != null && backImage != null) {

        int width = getWidth();
        int height = getHeight();
        if (mainImage==null || mainImage.getWidth()!= width || mainImage.getHeight()!= height)
         {
             mainImage=createComponentImage(width, height);
         }
         else
        {
            clearImage(mainImage);
        }
//      System.out.println("in paint, transitionPosition = " + transitionPosition);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        boolean lockAcquired =false;
        try
        {
            lockAcquired = transitionLock.tryLock(200, TimeUnit.MILLISECONDS);
            if (lockAcquired)
            {
     //          g2.setColor(darkBackground);
    //          g2.fillRect(0, 0, getWidth(), getHeight());
                Graphics2D offscreenGraphics = mainImage.createGraphics();

                offscreenGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                offscreenGraphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

              // prepare the image and draw it
                transition.prepareAndDrawImage(offscreenGraphics, transform, backImage, frontImage);
            }
            else
            {
                logger.log(Level.WARNING,"Unable to acquire lock on transition within 200 ms");
            }
            g2.drawImage(mainImage,0,0,this);
        }
        catch (InterruptedException e)
        {
            logger.log(Level.WARNING,e.getMessage(),e);
        }
        finally
        {
            if (lockAcquired)
            {
                transitionLock.unlock();
            }
            g2.dispose();
        }

    } else {
      super.paint(g);
    }
  }

  /**
   * Render the image
   * @param g2        the Graphics2D context
   * @param transform the transform to apply
   * @param image     the image to render
   */
  protected void renderImage(Graphics2D g2, AffineTransform transform, BufferedImage image) {
    g2.drawImage(image, transform, this);
  }

  public void setDebug(boolean debug) {
    this.debug = debug;
  }

  public boolean isDebug() {
    return debug;
  }

  public void debug() {
    if (!debug) {
      debug = true;
      transitionToNext();
    }
    repaint();
  }

    @Override
    public void add(Component comp, Object constraints) {
        WrapperPanel wrapper = new WrapperPanel(comp);
        if (frontComponent==null)
        {
          frontComponent=wrapper;
        }
        super.add(wrapper, constraints);
    }

    private class WrapperPanel extends JPanel
    {

        private Component wrappedComponent;

        private WrapperPanel(Component wrappedComponent) {
            super(new BorderLayout());
            this.wrappedComponent = wrappedComponent;
            add(this.wrappedComponent,BorderLayout.CENTER);
        }
    }
}
