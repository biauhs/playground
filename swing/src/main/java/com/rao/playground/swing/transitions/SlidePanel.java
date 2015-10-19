package com.rao.playground.swing.transitions;

/**
 * CardTransitionPanel derivation which uses a slide transition.
 * @deprecated Use <code>CardTransitionPanel</code> instead and set the transition to a <code>SlideTransition</code>.
 */

public class SlidePanel extends CardTransitionPanel {

  public SlidePanel() {
    transition = new SlideTransition();
  }

}
