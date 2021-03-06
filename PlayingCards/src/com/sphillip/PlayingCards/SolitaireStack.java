package com.sphillip.PlayingCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.sphillip.PlayingCards.Card.Suit;

/**
 * Class which is used for Solitaire Stacks which contain 
 * 2 Data structures to house both shown cards and hiden cards.
 * They contain the logic as to whether they can or cannot except cards.
 * They also maintain their own data with a contract that after any operation which requests
 * cards from the stack, must have the previousActionValid() method for the Stack called.
 */
public class SolitaireStack {
	
	public SolitaireStack() {
		mHidden = new Stack<>();
		mPublic = new ArrayList<>();
		mPreviousNdx = -1;
		
		//TODO: Verify this works as intended with a Stack using an ArrayList backing.
		transferFromHidden();
	}
	
	/**
	 * @return returns a list of cards that are on top of the hidden stack.
	 */
	public List<Card> getVisibleCards() {
		return Collections.unmodifiableList(mPublic);
	}
	
	/**
	 * See's if the Stack is capable of accepting a King in its place.
	 */
	public boolean isKingPlaceable() {
		if (mHidden.isEmpty() && mPublic.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public void setupStack(List<Card> cards) {
		for (int i = 0; i < cards.size(); i++) {
			mHidden.add(cards.get(i));
		}
		transferFromHidden();
	}
	
	/**
	 * Must be an integer >= 0;
	 */
	public List<Card> moveStack(int ndx) {
		mPreviousNdx = ndx;
		return mPublic.subList(ndx, mPublic.size() - 1);
	}
	
	/**
	 * Must be sent after verifying a moving of a card or card stack was successful.
	 */
	public void previousActionValid() {
		if (mPreviousNdx < 0) {
			return;
		}
		
		int toRemove = mPublic.size() - mPreviousNdx;
		for (int i = 0; i < toRemove; i ++) {
			mPublic.remove(mPublic.size() - 1);
		}
		
		transferFromHidden();
	}
	
	/**
	 * Verifies to see if the last card in the Stack can accept the starting card of the stack being added.
	 * If the action is invalid, this method will return false, otherwise will return true.
	 */
	public boolean acceptStack(List<Card> partialStack) {
		if (partialStack == null || partialStack.size() <= 0) {
			return false;
		}
		if (mPublic.size() == 0) {
			mPublic.addAll(partialStack);
			return true;
		}
		
		Card topPublicCard = mPublic.get(mPublic.size() - 1 );
		if (topPublicCard.getSymbolValue() == partialStack.get(0).getSymbolValue() + 1) {
			if (Card.doesColorAlternate(topPublicCard, partialStack.get(0))) {
				mPublic.addAll(partialStack);
				return true;
			}
		}
		
		return false;
	}
	
	public boolean addCard(Card card) {
		if (card == null) {
			return false;
		}
		if (mPublic.size() == 0) {
			mPublic.add(card);
			return true;
		}
		
		Card topCard = mPublic.get(mPublic.size() - 1);
		if (topCard.getSymbolValue() == card.getSymbolValue() + 1) {
			if (Card.doesColorAlternate(topCard, card)) {
				mPublic.add(topCard);
			}
		}
		
		return true;
	}
	
	public boolean removeTopCard() {
		if (mPublic.size() <= 0) {
			return false;
		}
		
		mPublic.remove(mPublic.size() - 1);
		transferFromHidden();
		return true;
	}
	
	/**
	 * Verifies a card can be placed underneath a stack, returns false if the operation is not possible, or true if the operation
	 * correctly goes through.
	 */
	public boolean placeBeneath(Card card) {
		if (card == null) {
			return false;
		}
		if (mPublic.size() <= 0) {
			mPublic.add(card);
			return true;
		}
		
		Card bottomPublicCard = mPublic.get(0);
		if (Card.doesColorAlternate(card, bottomPublicCard)) {
			if (bottomPublicCard.getSymbolValue() + 1 == card.getSymbolValue()) {
				mPublic.add(0, card);
				return true;
			}
		}
		
		return false;
	}
	
	public int publicSize() {
		return mPublic.size();
	}
	
	public int hiddenSize() {
		return mHidden.size();
	}
	
	public int totalSize() {
		return mPublic.size() + mHidden.size() - 2;
	}
	
	private void transferFromHidden() {
		if (mPublic.isEmpty() && !mHidden.isEmpty()) {
			mPublic.add(mHidden.pop());
		}
	}
	
	private int mPreviousNdx;
	private Stack<Card> mHidden;
	private List<Card> mPublic;
}
