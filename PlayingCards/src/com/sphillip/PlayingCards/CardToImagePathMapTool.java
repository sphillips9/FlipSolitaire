package com.sphillip.PlayingCards;

/**
 * Any card has an image stored in the JVM ready to be loaded.  This class is used to query for the image pertinent
 * to the card.
 */
public class CardToImagePathMapTool {
	
	/**
	 * Will return and image based on what suit and what symbol the Card has.
	 * If null, returns the back of a card.
	 * @param card The card which you want to acquire an image for.
	 */
	public static String getImage(Card card) {
		String path = "/images/";
		if (card == null) {
			return path + "back.png";
		}

		switch (card.getSymbol()) {
		case ACE: path += "A";
		break;
		case TWO: path += "2";
		break;
		case THREE: path += "3";
		break;
		case FOUR: path += "4";
		break;
		case FIVE: path += "5";
		break;
		case SIX: path += "6";
		break;
		case SEVEN: path += "7";
		break;
		case EIGHT: path += "8";
		break;
		case NINE: path += "9";
		break;
		case TEN: path += "10";
		break;
		case JACK: path += "J";
		break;
		case QUEEN: path += "Q";
		break;
		case KING: path += "K";
		break;
		}

		switch (card.getSuit()) {
		case CLUB: path += "C";
		break;
		case SPADE: path += "S";
		break;
		case HEART: path += "H";
		break;
		case DIAMOND: path += "D";
		break;
		}
		
		return path + ".png";
	}

}
