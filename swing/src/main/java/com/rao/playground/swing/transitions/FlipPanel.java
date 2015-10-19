package com.rao.playground.swing.transitions;

/**
 * CardTransitionPanel derivation which uses a flip transition.
 * @deprecated Use <code>CardTransitionPanel</code> instead and set the transition to a <code>FlipTransition</code>.
 */

public class FlipPanel extends CardTransitionPanel {

  public FlipPanel() {
    transition = new FlipTransition();
  }

}
