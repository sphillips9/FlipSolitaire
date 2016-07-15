package com.sphillip.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sphillip.PlayingCards.Card;
import com.sphillip.PlayingCards.CardView;
import com.sphillip.PlayingCards.Deck;
import com.sphillip.PlayingCards.SolitaireAcePile;
import com.sphillip.PlayingCards.SolitaireStack;

/**
 * Stores all the state for a generic Solitaire Game.
 */
public class SolitaireGameModel {

	
	public SolitaireGameModel() {
		mDeck = new Deck();
		mSolitaireAcePiles = new ArrayList<>();
		mSolitaireStacks = new ArrayList<>();
		mDiscardPile = new SolitaireStack();
		
		createDeckPiles();
	}
	
	/**
	 * @return Unmodifiable Arraylist which represents each Stack.
	 */
	public List<SolitaireStack> getSolitaireStacks() {
		return Collections.unmodifiableList(mSolitaireStacks);
	}
	
	/**
	 * @return Unmodifiable ArrayList which represents each Ace Stack.
	 */
	public List<SolitaireAcePile> getAcePiles() {
		return Collections.unmodifiableList(mSolitaireAcePiles);
	}
	
	/**
	 * @return SolitaireStack which represents the discard pile.
	 */
	public SolitaireStack getDiscardPile() {
		return mDiscardPile;
	}
	
	/**
	 * @return The deck being used to play the game.
	 */
	public Deck getDeck() {
		return mDeck;
	}
	
	public boolean addCardToAcePile(int pileNumber, Card card) {
		return mSolitaireAcePiles.get(pileNumber).addToStack(card);
	}
	
	public boolean addCardToStack(int stackNumber, Card card) {
		return mSolitaireStacks.get(stackNumber).addCard(card);
	}
	
	public boolean removeTopCard(int stackNumber) {
		return mSolitaireStacks.get(stackNumber).removeTopCard();
	}
	
	public boolean addCardToBottomStack(int stackNumber, Card card) {
		return mSolitaireStacks.get(stackNumber).placeBeneath(card);
	}
	
	private void createDeckPiles() {
		mDeck.shuffle();
		
		setupSolitaireStacks();
		setupSolitaireAcePiles();
	}
	
	private void setupSolitaireAcePiles() {
		for (int i = 0; i < 4; i++) {
			mSolitaireAcePiles.add(new SolitaireAcePile());
		}
	}
	
	private void setupSolitaireStacks() {
		for (int i = 0; i < 7; i++) {
			mSolitaireStacks.add(new SolitaireStack());
			List<Card> cards = new ArrayList<>();

			for (int j = 0; j < i+1; j++) {
				cards.add(mDeck.removeTopCard());
			}

			mSolitaireStacks.get(i).setupStack(cards);
		}
	}
	
	private List<SolitaireStack> mSolitaireStacks;
	private List<SolitaireAcePile> mSolitaireAcePiles;
	private SolitaireStack mDiscardPile;
	private Deck mDeck;
}
