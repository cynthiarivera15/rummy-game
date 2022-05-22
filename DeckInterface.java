/* -----------------------------------------------------------------------------
   Cynthia Marie Rivera Sánchez
   801-19-5470
   CCOM4029 - 002
   Prof. Patricia Ordonez
   Rummy Project: DeckInterface.java
--------------------------------------------------------------------------------
   Purpose: Represents how the deck of the card game would work.
   Not created by me
----------------------------------------------------------------------------- */

import java.util.*;

public interface DeckInterface {

 /**
   * Creates an empty deck of cards.
   */
   public Deck();
  /**
   *  returns next Card in a deck that is facing down without removing it
   */
	public Card peek();
 /**
   * this method is used to add Cards to a Deck.  The Deck is completely empty when it is initialized.
   */

   public void addCard(Card card) ;

 /**
   * returns number of cards on the deck
   * @return int
   */
   public int getSizeOfDeck() ;

   /**
   * removes first card on a deck so equivalent to flipping the card off of a eck that is faced down
   * @return <code>null</code> if there are no cards left on the Stack. Otherwise returns Card
   */
   public Card dealCard() ;

 /**
   * removes Card last card placed on a deck so equivalent toremoving card from deck that is faced up
   * @return <code>null</code> if there are no cards left on the deck. Otherwise removes Card
   */
   public Card removeCard() ;

  /**
   * Shuffles the cards present in the deck.
   */
   public void shuffle() ;

  /**
   * Looks for an empty deck.
   * @return <code>true</code> if there are no cards left to be dealt from the deck.
   */
   public boolean isEmpty();

  /**
   * Restores the deck to being empty and ready to add Cards to
   */
  public void restoreDeck() ;


}
