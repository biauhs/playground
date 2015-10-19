package com.rao.playground.swing.transitions;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A <code>CardLayout</code> object is a layout manager for a
 * container. It treats each component in the container as a card.
 * Only one card is visible at a time, and the container acts as
 * a stack of cards. The first component added to a
 * <code>CardLayout</code> object is the visible component when the
 * container is first displayed.
 * <p/>
 * The ordering of cards is determined by the container's own internal
 * ordering of its component objects. <code>CardLayout</code>
 * defines a set of methods that allow an application to flip
 * through these cards sequentially, or to show a specified card.
 * The {@link ExtendedCardLayout#addLayoutComponent}
 * method can be used to associate a string identifier with a given card
 * for fast random access.
 *
 * @author Arthur van Hoff
 * @version 1.42 03/28/06
 * @see Container
 * @since JDK1.0
 */

public class ExtendedCardLayout extends CardLayout {

    private List<Card> cards = new ArrayList<Card>();

   /**
   * Returns the component with the specified card name.
   *
   * @param name the card name of the component to find
   * @return the component associated with the card name if it exists, null otherwise
   */
  public Component getComponentForCard(String name) {
    Component result = null;
    int componentCount = cards.size();
    for (int i = 0; i < componentCount; i++) {
      Card card = (Card) cards.get(i);
      if (card.name.equals(name)) {
        result = card.comp;
        break;
      }
    }
    return result;
  }

   public String getCardNameForComponent(Component component) {
    String result = null;
    int componentCount = cards.size();
    for (int i = 0; i < componentCount; i++) {
      Card card = (Card) cards.get(i);
      if (component == card.comp) {
        result = card.name;
        break;
      }
    }
    return result;
  }


    /*
    * A pair of Component and String that represents its name.
    */
    private class Card implements Serializable {
        static final long serialVersionUID = 8840330810709497518L;
        public String name;
        public Component comp;

        public Card(String cardName, Component cardComponent) {
            name = cardName;
            comp = cardComponent;
        }
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        super.addLayoutComponent(comp, constraints);
        if (constraints instanceof String) {
            String name = (String) constraints;
            synchronized (comp.getTreeLock()) {
                if (!cards.isEmpty()) {
                    comp.setVisible(false);
                }
                for (int i = 0; i < cards.size(); i++) {
                    if (((Card) cards.get(i)).name.equals(name)) {
                        ((Card) cards.get(i)).comp = comp;
                        return;
                    }
                }
                cards.add(new Card(name, comp));
            }
        }

    }

    /**
     * Removes the specified component from the layout.
     * @param   comp   the component to be removed.
     * @see     Container#remove(Component)
     * @see     Container#removeAll()
     */
    public void removeLayoutComponent(Component comp) {
        synchronized (comp.getTreeLock()) {
            for (int i = 0; i < cards.size(); i++) {
                if (((Card) cards.get(i)).comp == comp) {
                    cards.remove(i);
                    break;
                }
            }
        }
        super.removeLayoutComponent(comp);
    }
}