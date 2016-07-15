package com.sphillip.PlayingCards;

import com.sphillip.PlayingCards.Card.Suit;

/**
 * Traditional Card Used for card games such as Poker/Blackjack/Solitaire.
 * Contains a Suit and a Symbol.
 */
public class Card {
	
	/**
	 * The Symbol is in the top left hand corner and bottom right hand corner of a playing card
	 * Traditional card symbols are Two->Ten and Jack/Queen/King/Ace.
	 */
	public static enum Symbol {
		ACE, TWO, THREE, FOUR,
		FIVE, SIX, SEVEN,
		EIGHT, NINE, TEN,
		JACK, QUEEN, KING;
	}
	
	/**
	 * The Suit is what type of {@code Symbol} the card is, example a Ace of Clubs or Ace of Spades.
	 */
	public static enum Suit {
		HEART, DIAMOND, SPADE, CLUB;
	}
	
	public Card(Suit suit, Symbol symbol) {
		mSuit = suit;
		mSymbol = symbol;
	}
	
	public Symbol getSymbol() {
		return mSymbol;
	}
	
	public Suit getSuit() {
		return mSuit;
	}
	
	public int getSymbolValue() {
		return mSymbol.ordinal();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Suit: " + mSuit.toString() + "Symbol: " + mSymbol.toString());
		return builder.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Card)) {
			return false;
		}
		
		Card card = (Card)obj;
		
		if (this.getSuit() != card.getSuit()) {
			return false;
		}
		
		if (this.getSymbol() != card.getSymbol()) {
			return false;
		}
		
		return true;
	}
	
	public static  boolean doesColorAlternate(Card card1, Card card2) {
		if (card1.getSuit() == Suit.HEART || card1.getSuit() == Suit.DIAMOND) {
			if (card2.getSuit() == Suit.SPADE || card2.getSuit() == Suit.CLUB) {
				return true;
			}
		}
		else if (card1.getSuit() == Suit.SPADE || card1.getSuit() == Suit.CLUB) {
			if (card2.getSuit() == Suit.HEART || card2.getSuit() == Suit.DIAMOND) {
				return true;
			}
		}
		
		return false;
	}
	
	private Suit mSuit;
	private Symbol mSymbol;
}
