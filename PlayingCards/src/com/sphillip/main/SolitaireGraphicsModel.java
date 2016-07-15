package com.sphillip.main;

import java.util.ArrayList;
import java.util.List;

import com.sphillip.PlayingCards.Card;
import com.sphillip.PlayingCards.CardView;
import com.sphillip.PlayingCards.SolitaireStack;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Stores the state of the GridPane which is used for the main Solitaire View.  This does not
 * have any information as to the game-state other than what images are being stored on the pane,
 * and their relevant positions.
 */
public class SolitaireGraphicsModel {

	public SolitaireGraphicsModel() {
	    mView = new GridPane();
	    mFirstRow = new ArrayList<>();
	    mSecondRow = new ArrayList<>();
	    
	    mView.setHgap(10);
	    mView.setVgap(10);
	    mView.setPadding(new Insets(0, 10, 0, 10));
	    
	    createGridPaneRows();
	}
	
	public Node getView() {
		return mView;
	}
	
	/**
	 * Piles are 0 indexed.
	 */
	public void addCardToAcePile(int pileNumber, Card card) {
		mFirstRow.get(pileNumber + 3).getChildren().add(new CardView(card));
	}
	
	public void addCardToStack(int stackNumber, Card card) {
		int stackSize = mSecondRow.get(stackNumber).getChildren().size();
		CardView view = new CardView(card);
		view.setLayoutY(SECOND_ROW_START + (stackSize * HEIGHT_OFFSET));
		mSecondRow.get(stackNumber).getChildren().add(view);
	}
	
	public void addCardBeneath(int stackNumber, Card card) {
		List<Node> nodes = new ArrayList<>(mSecondRow.get(stackNumber).getChildren());
		mSecondRow.get(stackNumber).getChildren().clear();
		CardView view = new CardView(card);
		view.setLayoutY(SECOND_ROW_START);
		mSecondRow.get(stackNumber).getChildren().add(view);
		for (int i = 0; i < nodes.size(); i++) {
			view = new CardView(((CardView)nodes.get(i)).getBackingCard());
			view.setLayoutY(SECOND_ROW_START + (HEIGHT_OFFSET * (i + 1)));
			mSecondRow.get(stackNumber).getChildren().add(view);
		}
	}
	
	public void removeTopCard(int stackNumber) {
		int last = mSecondRow.get(stackNumber).getChildren().size();
		mSecondRow.get(stackNumber).getChildren().remove(last - 1);
	}
	
	public void setupSolitiareStart(List<SolitaireStack> stacks) {
		//setup Deck placement
		mFirstRow.get(0).getChildren().add(new CardView(null));
		//setup ace piles
		for (int i = 3; i < 7; i++) {
			mFirstRow.get(i).getChildren().add(new CardView());
		}
		
		//seteup second row of cards.
		for (int k = 0; k < stacks.size(); k++) {
			for (int i = 0; i < stacks.get(k).hiddenSize(); i++) {
				addCardToStack(k, null);
			}
			
			List<Card> cards = stacks.get(k).getVisibleCards();
			for (int i = 0; i < cards.size(); i++) {
				addCardToStack(k, cards.get(i));
			}
		}
	}
	
	private void createGridPaneRows() {
		for (int i = 0; i < 7; i++) {
			Pane pane = new Pane();
			pane.setLayoutX((COLUMN_WIDTH * i) + COLUMN_WIDTH);
			mFirstRow.add(pane);
			mView.add(mFirstRow.get(i), i, 0);
		}
		
		for (int i = 0; i < 7; i++) {
			Pane pane = new Pane();
			pane.setLayoutX((COLUMN_WIDTH * i) + COLUMN_WIDTH);
			mSecondRow.add(pane);
			mView.add(mSecondRow.get(i), i, 1);
		}
	}
	
	private static final int HEIGHT_OFFSET = 30;
	private static final int SECOND_ROW_START = 50;
	private static final int COLUMN_WIDTH = 100;
	
	private List<Pane> mFirstRow;
	private List<Pane> mSecondRow;
	private GridPane mView;
}
