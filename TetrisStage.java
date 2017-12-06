package cs1302.p5;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color; 
import javafx.scene.layout.HBox;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class TetrisStage {
	
	private int pl1Score = 0;
	
	/*
	 * Sets Pane for Tetris
	 */
	BorderPane tetrisStage;
	
	public TetrisStage() {
		
		//initializes rootPane
		tetrisStage = new BorderPane();
		
		ToolBar toolBar = buildToolBar();
		
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
		
		
		GridPane gameGrid = buildSimpleBoard();
		tetrisStage.setStyle("-fx-background-color: #dda0dd;");
		tetrisStage.setTop(toolBar); 
		tetrisStage.setCenter(gameGrid);
		VBox newBox = displayScores();
		tetrisStage.setLeft(newBox);
		
		
		
	} //TetrisStage()
	
	/*
	 * Creates Tool bar for top of playing screen
	 * Tool bar includes: "New Game" button in which the user can start a new game
	 * 					"Quit Game" button in which the user can exit current game and go back to home screen
	 * 
	 */
	private ToolBar buildToolBar() {
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
		
		return toolbar;
	}
	
	private GridPane buildSimpleBoard() {
		GridPane gameGrid = new GridPane();
		RowConstraints rowCon = new RowConstraints();
		ColumnConstraints colCon = new ColumnConstraints();
		gameGrid.setPadding(new Insets(15,15,15,15));
		gameGrid.setGridLinesVisible(true);
		for(int i = 0; i < 6; i++) {
			//rowCon.setPercentHeight(60.0);
			//rowCon.setMinHeight(64);
			//rowCon.setMaxHeight(64);
			rowCon.setPrefHeight(64);
			rowCon.setValignment(VPos.CENTER);
			gameGrid.getRowConstraints().add(rowCon);
			
			//colCon.setPercentWidth(60.0);
			//colCon.setMinWidth(64);
			//colCon.getMaxWidth();
			//colCon.setMaxWidth(64);
			colCon.setPrefWidth(64);
			colCon.setHalignment(HPos.CENTER);
			gameGrid.getColumnConstraints().add(colCon);
		}
		return gameGrid;
	
	}
	
	/*
	 * Creates display for user that projects the welcome and their scores
	 */
	private VBox displayScores() {
		VBox vb = new VBox();
		vb.setPadding(new Insets(10));
	    vb.setSpacing(40);
		//Rectangle playerScore = new Rectangle(150.0,800.0);
		//playerScore.setStroke(Color.BLACK);
		
		Text welc = new Text("  Welcome to ");
		welc.setFont(Font.font("Verdana", 18));
		welc.setFill(Color.BLACK);
		welc.setStroke(Color.BLACK); 
		
		Text welc2 = new Text("     Tetris!!");
		welc2.setFont(Font.font("Verdana", 18));
		welc2.setFill(Color.BLACK);
		welc2.setStroke(Color.BLACK); 
		
		Text emptyText = new Text(" ");
		emptyText.setFont(Font.font("Verdana", FontWeight.BOLD, 36));
		emptyText.setFill(Color.WHITE);
		emptyText.setStroke(Color.BLACK);
		
		Text scoreText1 = new Text("PLAYER'S SCORE: ");
		scoreText1.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		scoreText1.setFill(Color.WHITE);
		scoreText1.setStroke(Color.BLACK); 
		
		Text display = new Text("         " + pl1Score);
		display.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		display.setFill(Color.WHITE);
		display.setStroke(Color.BLACK);
		
		Text empty2 = new Text("");
		empty2.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		empty2.setFill(Color.WHITE);
		empty2.setStroke(Color.BLACK);
		
		vb.getChildren().addAll(empty2, welc, welc2, emptyText, scoreText1, display);
		vb.setAlignment(Pos.TOP_LEFT);
		
		return vb;
	} //displayScores()

	/*
	 * Returns the current working Scene of the App.
	 *
	 */
	public Pane getRoot() {
		return tetrisStage;
	} //getRoot()
	
}
