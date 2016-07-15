package com.sphillip.PlayingCards;

import javafx.scene.image.ImageView;

/**
 * CardView extends the javaFX.Scene's ImageView, it is a class that pairs both
 * the image of the card being viewed, and the object of the Card being viewed.  This class
 * eliminates the need to have multiple structures to house cards/their images. 
 */
public class CardView extends ImageView {

	/**
	 * @param the image to be mapped to the card.
	 * @param card the Card object to be stored alongside the image.
	 */
	public CardView(Card card) {
		super(getImage(card));
		
		//this value can be null if we are just showing an empty space or the back of a card.
		mCard = card;
		//Offset all cards by 20 so they do not hit the left sides boundary.
		super.setLayoutX(20);
	}
	
	public CardView() {
		super("/images/space.png");
		mCard = null;
		//Offset all cards by 20 so they do not hit the left sides boundary.
		super.setLayoutX(20);
	}
	
	/**
	 * @return The card which the image represents.
	 */
	public Card getBackingCard() {
		return mCard;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CardView)) {
			return false;
		}
		if (this.getBackingCard().equals(((CardView)obj).getBackingCard())) {
			return true;
		}
		
		return false;
	}
	
	private static String getImage(Card card) {
		return CardToImagePathMapTool.getImage(card);
	}
	
	private Card mCard;
}
