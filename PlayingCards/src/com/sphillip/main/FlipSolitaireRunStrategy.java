package com.sphillip.main;

import com.sphillip.PlayingCards.Card;

import javafx.scene.Node;

/**
 * Contains all functionality to play solitaire, and co-ordinates efforts
 * between the {@code SolitaireGraphicsModel} and {@code SolitaireGameModel}.
 */
public class FlipSolitaireRunStrategy {
	
	public FlipSolitaireRunStrategy() {
		mData = new SolitaireGameModel();
		mView = new SolitaireGraphicsModel();
		
		mView.setupSolitiareStart(mData.getSolitaireStacks());
	}
	
	public Node getView() {
		return mView.getView();
	}
	
	public void addCard(int stackNumber, Card card) {
		if (mData.addCardToStack(stackNumber, card)) {
			mView.addCardToStack(stackNumber, card);
		}
	}
	
	public void removeTopCard(int stackNumber) {
		if (mData.removeTopCard(stackNumber)) {
			mView.removeTopCard(stackNumber);
		}
	}
	
	public void placeCardBeneath(int stackNumber, Card card) {
		if (mData.addCardToBottomStack(stackNumber, card)) {
			mView.addCardBeneath(stackNumber, card);
		}
	}
	
	public void addToAcePile(int pileNumber, Card card) {
		if (mData.addCardToAcePile(pileNumber, card)) {
			mView.addCardToAcePile(pileNumber, card);
		}
	}
	
	SolitaireGameModel mData = new SolitaireGameModel();
	SolitaireGraphicsModel mView = new SolitaireGraphicsModel();
}
