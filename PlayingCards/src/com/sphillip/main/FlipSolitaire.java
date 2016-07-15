package com.sphillip.main;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Round 2 with just FXML elements
 */
public class FlipSolitaire extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setOnCloseRequest(event -> {
			System.exit(0);
		});
		
		FlipSolitaireRunStrategy runStrategy = new FlipSolitaireRunStrategy();
		
		Pane pane = new Pane();
		Scene scene = new Scene(pane, 800, 490, true);
		scene.setFill(Color.GHOSTWHITE);
		
		pane.getChildren().add(runStrategy.getView());
		primaryStage.setTitle("Flip Solitaire");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
