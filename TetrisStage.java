package cs1302.p5;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color; 
import javafx.scene.layout.HBox;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class TetrisStage {
	
	/*
	 * Sets Pane for Tetris
	 */
	BorderPane tetrisStage;
	
	public TetrisStage() {
		
		//initializes rootPane
		tetrisStage = new BorderPane();
		
		/*
		 * Creates Toolbar for top of playing screen
		 * 
		 */
		Button btnNew = new Button("New Game");
        Button btnQuit = new Button("Quit Game");
		
		ToolBar toolbar = new ToolBar(
				btnNew,
				btnQuit
		);
		
		//NEW button
		btnNew.setOnAction(e -> {
    		TetrisStage newGame = new TetrisStage();
    		tetrisStage.getScene().setRoot(newGame.getRoot());
		});
		
		//QUIT button
		btnQuit.setOnAction(e -> {
	    		OpeningStage openingStage = new OpeningStage();
	    		tetrisStage.getScene().setRoot(OpeningStage.getRoot());
	    });
		
		/*
		 * Sets Background color for Tetris is black
		 */
		Label black = new Label();
		black.setStyle("-fx-background-color: #000000; -fx-padding: 150px; -fx-border-width: 75px;");
		
		Label grey = new Label();
		grey.setStyle("-fx-background-color: #dcdcdc; -fx-padding: 25px;");
		
		Label grey2 = new Label();
		grey2.setStyle("-fx-background-color: #dcdcdc; -fx-padding: 25px;");
		
		Label black2 = new Label();
		black2.setStyle("-fx-background-color: #000000; -fx-padding: 10px; -fx-border-width: 75px;");
		
		tetrisStage.setStyle("-fx-background-color: #dda0dd;");
		tetrisStage.setTop(toolbar); 
		tetrisStage.setCenter(black);
		//tetrisStage.setRight(grey);
		//tetrisStage.setLeft(grey2);
		//tetrisStage.setBottom(black2);
		//tetrisStage.setPrefSize(500,400);
		
		
	} //TetrisStage()
	
	public Pane getRoot() {
		return tetrisStage;
	} //getRoot()
	
}
