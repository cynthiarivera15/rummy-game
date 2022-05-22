/* -----------------------------------------------------------------------------
   Cynthia Marie Rivera Sánchez
   801-19-5470
   CCOM4029 - 002
   Prof. Patricia Ordonez
   Rummy Project: MyStack.java
--------------------------------------------------------------------------------
   Purpose: Represents the stack of the game.
----------------------------------------------------------------------------- */

import java.util.*;

public class MyStack {

  java.util.Stack stack;

 /**
  * Creates an empty stack of cards.
  */
  public MyStack() {
     stack = new Stack();
  }

  // Looks at the top element of the stack without removing it
  public Card peek() {
    if(stack.size() == 0)
      return null;

    else
      return (Card)stack.peek();
  }

  // Adds element at the top of the stack
  public void addCard(Card card) {
    stack.push(card);
  }

  // Removes element at the top of the stack
  public Card removeCard() {
    if (stack.size() == 0)
        return null;

    else {
      return (Card) stack.pop( );
    }
  }
}
