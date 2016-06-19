package com.sphillip.PlayingCards;

import java.util.Stack;

import com.sphillip.PlayingCards.Card.Suit;
import com.sphillip.PlayingCards.Card.Symbol;

/**
 * Ace piles must start with aces and continue in order from ace->two->King as the last card.
 */
public class SolitaireAcePile {
	
	/**
	 * @param suit Piles can only contain 1 suit.
	 */
	public SolitaireAcePile(Suit suit) {
		mSuit = suit;
		mDiscard = new Stack<>();
	}
	
	/**
	 * Take top card off the stack to use in a game of solitaire.  Returns null if there are no cards in the Ace pile.
	 */
	public Card takeTopCard() {
		if (mDiscard.isEmpty()) {
			return null;
		}
		
		return mDiscard.pop();
	}
	
	/**
	 * Will return false if the card cannot be added to the stack.  Will return true if card successfully added to stack.
	 */
	public boolean addToStack(Card card) {
		if (mSuit != card.getSuit()) {
			return false;
		}
		
		if (mDiscard.isEmpty()) {
			if (card.getSymbol() == Symbol.ACE) {
				mDiscard.add(card);
				return true;
			}
		}
		else if (mDiscard.peek().getSymbolValue() + 1 == card.getSymbolValue()) {
			mDiscard.push(card);
			return true;
		}

		return false;
	}
	
	private final Suit mSuit;
	private final Stack<Card> mDiscard;
}
