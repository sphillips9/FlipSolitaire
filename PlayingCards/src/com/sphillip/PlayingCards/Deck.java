package com.sphillip.PlayingCards;

import java.util.ArrayList;
import java.util.List;

import com.sphillip.PlayingCards.Card.Suit;
import com.sphillip.PlayingCards.Card.Symbol;

/**
 * A deck contains 52 cards, 1 set of each suit's 13 symbols.
 */
public class Deck {
	
	/**
	 * Deck is not shuffled when created.
	 */
	public Deck() {
		mCards = new ArrayList<>(52);
		populateDeck(mCards);
	}
	
	/**
	 * Creates a standard 52 card deck.
	 */
	public static void populateDeck(List<Card> list) {
		for(Symbol symbol : Symbol.values()) {
			for (Suit suit : Suit.values()) {
				list.add(new Card(suit, symbol));
			}
		}
	}
	
	/**
	 * Creates a randomized traditional card deck.
	 */
	public void shuffle() {
		mCards.clear();
		List<Card> deck = new ArrayList<>();
		populateDeck(deck);
		
		for(int i = 0; i < 52; i++) {
			int ndx = (int)(Math.random() * deck.size()) % 52;
			mCards.add(deck.get(ndx));
			deck.remove(ndx);
		}
	}
	
	/**
	 * If the number of cards requested to view is greater than the amount existing in the {@code Deck}
	 * then this method will return the entire deck's list.  This method also removes those viewed cards from the deck.
	 * 
	 * @return first numCards cards will be viewed from the top of the deck, used in solitaire.
	 */
	public List<Card> removeTopCards(int numCards) {
		assert numCards > 0;

		if (numCards <= 0) {
			return null;
		}
		
		List<Card> cards = new ArrayList<>();
		if (numCards >= mCards.size()) {
			cards =  mCards.subList(0, mCards.size() - 1); 
			mCards.clear();
		}
		else {
			for (int i = 0; i < numCards; i ++) {
				cards.add(cards.remove(0));
			}
		}
		
		return cards;
	}
	
	/**
	 *  Will return the top card of the deck, and remove it.
	 *  If there are no cards left in the deck, it will return NULL.
	 */
	public Card removeTopCard() {
		if (mCards.size() == 0) {
			return null;
		}
		
		Card card = mCards.get(0);
		mCards.remove(0);
		return card;
	}
	
	/**
	 * Removes and returns a {@code Card} at a specific index. 
	 */
	public Card removeCard(int ndx) {
		Card card = mCards.get(ndx);
		mCards.remove(ndx);
		return card;
	}
	
	/**
	 * Adds the card to the bottom of the deck.
	 */
	public void addCard(Card card) {
		mCards.add(card);
	}
	
	/**
	 * Adds the card to a specific place in the deck.
	 * Putting a card back ontop of the deck would be addCard(card, 0);
	 *  
	 */
	public void addCard(Card card, int ndx) {
		mCards.add(ndx, card);
	}
	
	// The top of the deck is considered the first element of the List.
	protected List<Card> mCards;
}
