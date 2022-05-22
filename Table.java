/* -----------------------------------------------------------------------------
   Cynthia Marie Rivera Sánchez
   801-19-5470
   CCOM4029 - 002
   Prof. Patricia Ordonez
   Rummy Project: Table.java
--------------------------------------------------------------------------------
   Purpose: Represents the table of the game, is where the GUI is implemented
	 Created by Patti Ordonez
----------------------------------------------------------------------------- */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
/**
*	This GUI assumes that you are using a 52 card deck and that you have 13 sets in the deck.
*	The GUI is simulating a playing table
	@author Patti Ordonez
*/
public class Table extends JFrame implements ActionListener {
	final static int numDealtCards = 9;
	JPanel player1;
	JPanel player2;
	JPanel deckPiles;
	JLabel deck;
	JLabel stack;
	JList p1HandPile;
	JList p2HandPile;
	Deck cardDeck;
	MyStack stackDeck;

	SetPanel [] setPanels = new SetPanel[13];
	JLabel topOfStack;
	JLabel deckPile;
	JButton p1Stack;
	JButton p2Stack;

	JButton p1Deck;
	JButton p2Deck;

	JButton p1Lay;
	JButton p2Lay;

	JButton p1LayOnStack;
	JButton p2LayOnStack;

	DefaultListModel p1Hand;
	DefaultListModel p2Hand;

	int turn = 0;
	boolean draw = true;

	private void deal(Hand cards) {
		for(int i = 0; i < numDealtCards; i ++)
			cards.addCard((Card)cardDeck.dealCard());
			//cards[i] = (Card)cardDeck.dealCard();
	}

	public Table() {
		super("The Card Game of the Century");

		setLayout(new BorderLayout());
		setSize(1200,700);


		cardDeck = new Deck();

		for(int i = 0; i < Card.suit.length; i++) {
			for(int j = 0; j < Card.rank.length; j++) {
				Card card = new Card(Card.suit[i],Card.rank[j]);
				cardDeck.addCard(card);
			}
		}

		cardDeck.shuffle();
		stackDeck = new MyStack();

		JPanel top = new JPanel();

		for (int i = 0; i < Card.rank.length;i++)
			setPanels[i] = new SetPanel(Card.getRankIndex(Card.rank[i]));


		top.add(setPanels[0]);
		top.add(setPanels[1]);
		top.add(setPanels[2]);
		top.add(setPanels[3]);

		player1 = new JPanel();

		player1.add(top);




		add(player1, BorderLayout.NORTH);
		JPanel bottom = new JPanel();


		bottom.add(setPanels[4]);
		bottom.add(setPanels[5]);
		bottom.add(setPanels[6]);
		bottom.add(setPanels[7]);
		bottom.add(setPanels[8]);

		player2 = new JPanel();




		player2.add(bottom);
		add(player2, BorderLayout.SOUTH);


		JPanel middle = new JPanel(new GridLayout(1,3));

		p1Stack = new JButton("Stack");
		p1Stack.addActionListener(this);
		p1Deck = new JButton("Deck ");
		p1Deck.addActionListener(this);
		p1Lay = new JButton("Lay  ");
		p1Lay.addActionListener(this);
		p1LayOnStack = new JButton("LayOnStack");
		p1LayOnStack.addActionListener(this);


		Hand cardsPlayer1 = new Hand();
		deal(cardsPlayer1);
		p1Hand = new DefaultListModel();

		for(int i = 0; i < cardsPlayer1.getNumberOfCards(); i++)
			p1Hand.addElement(cardsPlayer1.getCard(i));
		sortHand(p1Hand);
		System.out.println("Initial Player 1: " + p1Hand);

		p1HandPile = new JList(p1Hand);
		middle.add(new HandPanel("Player 1", p1HandPile, p1Stack, p1Deck, p1Lay, p1LayOnStack));

		deckPiles = new JPanel();
		deckPiles.setLayout(new BoxLayout(deckPiles, BoxLayout.Y_AXIS));
		deckPiles.add(Box.createGlue());
		JPanel left = new JPanel();
		left.setAlignmentY(Component.CENTER_ALIGNMENT);


		stack = new JLabel("Stack");
		stack.setAlignmentY(Component.CENTER_ALIGNMENT);

		left.add(stack);
		topOfStack = new JLabel();
		topOfStack.setIcon(new ImageIcon(Card.directory + "blank.gif"));
		topOfStack.setAlignmentY(Component.CENTER_ALIGNMENT);
		left.add(topOfStack);
		deckPiles.add(left);
		deckPiles.add(Box.createGlue());

		JPanel right = new JPanel();
		right.setAlignmentY(Component.CENTER_ALIGNMENT);

		deck = new JLabel("Deck");

		deck.setAlignmentY(Component.CENTER_ALIGNMENT);
		right.add(deck);
		deckPile = new JLabel();
		deckPile.setIcon(new ImageIcon(Card.directory + "b.gif"));
		deckPile.setAlignmentY(Component.CENTER_ALIGNMENT);
		right.add(deckPile);
		deckPiles.add(right);
		deckPiles.add(Box.createGlue());
		middle.add(deckPiles);


		p2Stack = new JButton("Stack");
		p2Stack.addActionListener(this);
		p2Deck = new JButton("Deck ");
		p2Deck.addActionListener(this);
		p2Lay = new JButton("Lay  ");
		p2Lay.addActionListener(this);
		p2LayOnStack = new JButton("LayOnStack");
		p2LayOnStack.addActionListener(this);

		Hand cardsPlayer2 = new Hand();
		deal(cardsPlayer2);
		p2Hand = new DefaultListModel();

		for(int i = 0; i < cardsPlayer2.getNumberOfCards(); i++)
			p2Hand.addElement(cardsPlayer2.getCard(i));
		sortHand(p2Hand);
		System.out.println("Initial Player 1: " + p2Hand);

		p2HandPile = new JList(p2Hand);

		middle.add(new HandPanel("Player 2", p2HandPile, p2Stack, p2Deck, p2Lay, p2LayOnStack));

		add(middle, BorderLayout.CENTER);

		JPanel leftBorder = new JPanel(new GridLayout(2,1));


		setPanels[9].setLayout(new BoxLayout(setPanels[9], BoxLayout.Y_AXIS));
		setPanels[10].setLayout(new BoxLayout(setPanels[10], BoxLayout.Y_AXIS));
		leftBorder.add(setPanels[9]);
		leftBorder.add(setPanels[10]);
		add(leftBorder, BorderLayout.WEST);

		JPanel rightBorder = new JPanel(new GridLayout(2,1));

		setPanels[11].setLayout(new BoxLayout(setPanels[11], BoxLayout.Y_AXIS));
		setPanels[12].setLayout(new BoxLayout(setPanels[12], BoxLayout.Y_AXIS));
		rightBorder.add(setPanels[11]);
		rightBorder.add(setPanels[12]);
		add(rightBorder, BorderLayout.EAST);
	}

	/*
		Once a button is pressed it checks wheter is turn of player 1 or 2
		Then it checks if the player that turn it is has taken a card either from
		the Stack or the Deck, it would not let the player place a card if they
		haven't taken one.
		After taken a card, then the player can decide whether to layOnStack or
		lay a set
		Once the player is done with its turn, the other player can play and the
		process will repeat itself
	*/
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(turn == 0) {
			if((p1Deck == src || p1Stack == src) && draw == true) {
				System.out.println("Player 1");
				if(p1Deck == src) {
					Card card = cardDeck.dealCard();
					if (card != null) {
						p1Hand.addElement(card);
						draw = false;
					}
					System.out.println("	Added: " + card);
					sortHand(p1Hand);
				}

				else {
					if(draw == true) {
						Card card = stackDeck.removeCard();

						if(card != null) {
							draw = false;
							Card topCard = stackDeck.peek();

							if (topCard != null)
								topOfStack.setIcon(topCard.getCardImage());

							else
								topOfStack.setIcon(new ImageIcon(Card.directory + "blank.gif"));

							p1Hand.addElement(card);
							sortHand(p1Hand);
							System.out.println("	Added: " + card);
						}
					}
				}
			}

			if((p1Lay == src || p1LayOnStack == src) && draw == false) {
				if(p1Lay == src) {
					if(turn == 0) {
						Object [] cards = p1HandPile.getSelectedValues();

						if(cards.length >= 3) {
							Card item = (Card)cards[0];
							boolean keep = true;

							for(int i = 0; i < cards.length; i++) {
								Card compare_item = (Card)cards[i];
								if(compare_item.getRank() != item.getRank()) {
									keep = false;
								}
							}

							if (keep != false) {
								System.out.print("	Discarded: ");
								for(int i = 0; i < cards.length; i++) {
									Card card = (Card)cards[i];
									layCard(card);
									p1Hand.removeElement(card);
									System.out.print(card + " ");
						    }

								System.out.println();
								System.out.println("	Hand now: " + p1Hand);

								if(cardDeck.isEmpty() || p1Hand.isEmpty()) {
									Hand hand1 = new Hand();
									for (int i = 0; i < p1Hand.size(); i++) {
											hand1.addCard((Card)p1Hand.get(i));
									}
									int p1Points = hand1.evaluateHand();

									Hand hand2 = new Hand();
									for (int i = 0; i < p2Hand.size(); i++) {
											hand2.addCard((Card)p2Hand.get(i));
									}
									int p2Points = hand2.evaluateHand();

									if(p1Points > p2Points && p1Hand.isEmpty() == false) {
										System.out.println("Points: " + p1Points + " to " + p2Points);
										System.out.println("Player 1 Wins");
										JOptionPane.showMessageDialog(null, "Player 1 Wins", "Game Over", JOptionPane.PLAIN_MESSAGE);
										System.exit(0);
									}

									else if(p2Points > p1Points && p1Hand.isEmpty() == false) {
										System.out.println("Points: " + p2Points + " to " + p1Points);
										System.out.println("Player 2 Wins");
										JOptionPane.showMessageDialog(null, "Player 2 Wins", "Game Over", JOptionPane.PLAIN_MESSAGE);
										System.exit(0);
									}

									else {
										System.out.println("Player 1 out of cards, Player 1 Wins!");
										JOptionPane.showMessageDialog(null, "Player 1 Wins", "Game Over", JOptionPane.PLAIN_MESSAGE);
										System.exit(0);
									}
								}
								turn = 1;
							}
						}
					}
				}

				else {
					if(p1LayOnStack == src && turn == 0) {
						int [] num  = p1HandPile.getSelectedIndices();

						if (num.length == 1) {
							Object obj = p1HandPile.getSelectedValue();

							if (obj != null) {
								Card card = (Card)obj;
								p1Hand.removeElement(card);
								stackDeck.addCard(card);
								topOfStack.setIcon(card.getCardImage());
								System.out.println("	Discarded: " + card);
								System.out.println("	Hand now: " + p1Hand);
							}

							turn = 1;
						}
					}
				}
			}
		}

		else {
			if((p2Deck == src || p2Stack == src) && turn == 1) {
				System.out.println("Player 2");
				if(p2Deck == src) {
					if(draw == false) {
						Card card = cardDeck.dealCard();
						if (card != null && draw == false) {
							p2Hand.addElement(card);
							sortHand(p2Hand);
							draw = true;
						}
						System.out.println("	Added: " + card);
					}
				}

				else {
					if(draw == false) {
						Card card = stackDeck.removeCard();

						if(card != null) {
							draw = true;
							Card topCard = stackDeck.peek();

							if (topCard != null)
								topOfStack.setIcon(topCard.getCardImage());

							else
								topOfStack.setIcon(new ImageIcon(Card.directory + "blank.gif"));

							p2Hand.addElement(card);
							sortHand(p2Hand);
							System.out.println("	Added: " + card);
						}
					}
				}
			}

			if((p2Lay == src || p2LayOnStack == src) && draw == true) {
				if(p2Lay == src) {
					if(turn == 1) {
						Object [] cards = p2HandPile.getSelectedValues();

						if(cards.length >= 3) {
							Card item = (Card)cards[0];
							boolean keep = true;

							for(int i = 0; i < cards.length; i++) {
								Card compare_item = (Card)cards[i];
								if(compare_item.getRank() != item.getRank()) {
									keep = false;
								}
							}

							if (keep != false) {
								System.out.print("	Discarded: ");
								for(int i = 0; i < cards.length; i++) {
									Card card = (Card)cards[i];
									layCard(card);
									p2Hand.removeElement(card);
									System.out.print(card + " ");
						    }

								System.out.println();
								System.out.println("	Hand now: " + p2Hand);

								if(cardDeck.isEmpty() || p2Hand.isEmpty()) {
									Hand hand1 = new Hand();
									for (int i = 0; i < p1Hand.size(); i++) {
											hand1.addCard((Card)p1Hand.get(i));
									}
									int p1Points = hand1.evaluateHand();

									Hand hand2 = new Hand();
									for (int i = 0; i < p2Hand.size(); i++) {
											hand2.addCard((Card)p2Hand.get(i));
									}
									int p2Points = hand2.evaluateHand();

									if(p1Points > p2Points && p2Hand.isEmpty() == false) {
										System.out.println("Points: " + p1Points + " to " + p2Points);
										System.out.println("Player 1 Wins");
										JOptionPane.showMessageDialog(null, "Player 1 Wins", "Game Over", JOptionPane.PLAIN_MESSAGE);
										System.exit(0);
									}

									else if(p2Points > p1Points && p2Hand.isEmpty() == false) {
										System.out.println("Points: " + p2Points + " to " + p1Points);
										System.out.println("Player 2 Wins");
										JOptionPane.showMessageDialog(null, "Player 2 Wins", "Game Over", JOptionPane.PLAIN_MESSAGE);
										System.exit(0);
									}

									else {
										System.out.println("Player 2 out of cards, Player 2 Wins!");
										JOptionPane.showMessageDialog(null, "Player 2 Wins", "Game Over", JOptionPane.PLAIN_MESSAGE);
										System.exit(0);
									}
								}

								turn = 0;
							}
						}
					}
				}

				else {
					if(p2LayOnStack == src && turn == 1) {
						int [] num  = p2HandPile.getSelectedIndices();

						if (num.length == 1) {
							Object obj = p2HandPile.getSelectedValue();

							if (obj != null) {
								Card card = (Card)obj;
								p2Hand.removeElement(card);
								stackDeck.addCard(card);
								topOfStack.setIcon(card.getCardImage());
								System.out.println("	Discarded: " + card);
								System.out.println("	Hand now: " + p2Hand);
							}

							turn = 0;
						}
					}
				}
			}
		}
	}

	void layCard(Card card) {
		char rank = card.getRank();
		char suit = card.getSuit();
		int suitIndex =  Card.getSuitIndex(suit);
		int rankIndex =  Card.getRankIndex(rank);
		//setPanels[rankIndex].array[suitIndex].setText(card.toString());
		//System.out.println("	Discarded: " + card);
		setPanels[rankIndex].array[suitIndex].setIcon(card.getCardImage());
	}

  void sortHand(DefaultListModel hand) {
		ArrayList<Card> list = new ArrayList<Card>();
		for (int i = 0; i < hand.size(); i++) {
				list.add((Card)hand.get(i));
		}

		int index = 0;
		for (int i = 0; i < list.size(); i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if(list.get(i).getRank() > list.get(j).getRank()) {
					Card tmp = list.get(i);
          list.set(i, list.get(j));
          list.set(j, tmp);
				}
			}
		}

		hand.removeAllElements();
		for (Card s : list) {
				hand.addElement(s);
		}
	}
}

class HandPanel extends JPanel {

	public HandPanel(String name,JList hand, JButton stack, JButton deck, JButton lay, JButton layOnStack) {
		//model = hand.createSelectionModel();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		add(Box.createGlue());
		JLabel label = new JLabel(name);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(label);
		stack.setAlignmentX(Component.CENTER_ALIGNMENT);
//		add(Box.createGlue());
		add(stack);
		deck.setAlignmentX(Component.CENTER_ALIGNMENT);
//		add(Box.createGlue());
		add(deck);
		lay.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lay);
		layOnStack.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(layOnStack);
		add(Box.createGlue());
		add(hand);
		add(Box.createGlue());
	}
}

class SetPanel extends JPanel {
	private Set data;
	JButton [] array = new JButton[4];

	public SetPanel(int index) {
		super();
		data = new Set(Card.rank[index]);

		for(int i = 0; i < array.length; i++) {
			array[i] = new JButton("   ");
			add(array[i]);
		}
	}
}
