package cs1302.p5;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.fxml.FXML;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.Cell;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.input.MouseEvent;

public class CheckersStage extends Application{
	
	private BorderPane checkersStage;
	private Circle[] playerOneChips = new Circle[64];
	private Circle[] playerTwoChips = new Circle[64];
	private Boolean[][] isEmptyGridArray = new Boolean [8][8];
	private Boolean [][] containsRedChip = new Boolean[8][8];
	private Boolean [][] containsWhiteChip = new Boolean[8][8];
	int redCount = 0;
	int whiteCount = 0;
	GridPane gameGrid = buildSimpleBoard();
	String redPlayer;
	String whitePlayer;
	int pl1Score = 0;
	int pl2Score = 0;
	double currentX;
	double currentY;
	double futX;
	double futY;
	boolean redWins;
	int totalPl1Chips = 12;
	int totalPl2Chips = 12;
	boolean currentPicked = false;
	boolean futPicked = false;
	
	public CheckersStage() {
		
		//Pane that contains the Checkers game
		checkersStage = new BorderPane();
		checkersStage.setPrefSize(800.00, 800.00);
		
		//Make labels to fill BorderPane
		Label black = new Label();
		black.setStyle("-fx-background-color: #000000; -fx-padding: 150px; -fx-border-width: 75px;");
		Label grey = new Label();
		grey.setStyle("-fx-background-color: #dcdcdc; -fx-padding: 25px;");
		Label grey2 = new Label();
		grey2.setStyle("-fx-background-color: #dcdcdc; -fx-padding: 25px;");
		Label black2 = new Label();
		black2.setStyle("-fx-background-color: #000000; -fx-padding: 10px; -fx-border-width: 75px;");
		
		checkersStage.setStyle("-fx-background-color: #dda0dd;"); 
		checkersStage.setBottom(black2); 
		ToolBar toolBar = buildToolBar();
		gameGrid = buildSimpleBoard();//the Checkers game board
		gameGrid = fillStartingPositions(gameGrid); //fills starting positions at beginning of game
		gameGrid.setPadding(new Insets(5,5,5,5));
		gameGrid.setGridLinesVisible(true);
		gameGrid.setPrefWidth(30.00);
		gameGrid.setPrefHeight(30.00);
		gameGrid.prefWidthProperty().bind(checkersStage.widthProperty());
		gameGrid.prefHeightProperty().bind(checkersStage.heightProperty());
		gameGrid.setStyle("-fx-background-color: #dda0dd; -fx-background-radius: 0; -fx-alignment: center; -fx-padding: 10px; -fx-background-insets: 2,2,2,2");
		makeBoardClickable();
		checkersStage.setCenter(gameGrid);
		checkersStage.setTop(toolBar);
		VBox vb = displayScores();
		checkersStage.setLeft(vb);
		checkersStage.autosize();
		startGame();
	
	} //CheckersStage()
	
	void startGame() {
		//this.redPlayer = player1;
		//this.whitePlayer = player2;
		this.pl1Score = 0;
		this.pl2Score = 0;
		currentPicked = false;
		futPicked = false;
		
		for(int i = 0; i < 100; i++) {	//gameLoop
			int iMod = i%2;
			/*
			while ((currentPicked == false) || (futPicked == false)) {
				gameGrid.setOnMouseClicked( event -> {
					double x = event.getX();
					double y = event.getY();
					futX = x;
					futY = y;
					futPicked = true;
					
					playerTwoChips[whiteCount] = new Circle(10, Color.WHITE);
					playerTwoChips[whiteCount].setStroke(Color.BLACK);
					
					gameGrid.add(playerTwoChips[whiteCount], (int)futY, (int) futX);
				});
			}
			*/
			
			//if((currentPicked == true) && (futPicked == true)) {
			//	redCheckersSlideForward(currentX, currentY, futX, futY);
				
				//red players turn
				/*
				if(iMod == 0) {
					if((redCanJumpForward(currentX, currentY, futX, futY))){
						redCheckersJumpForward(currentX, currentY, futX, futY);
						currentPicked = false;
						futPicked = false;
					}
					if(redCanSlideForward(currentX, currentY, futX, futY)){
						redCheckersSlideForward(currentX, currentY, futX, futY);
						currentPicked = false;
						futPicked = false;
					}
				//white player's turn
				} else {
					if((whiteCanJumpForward(currentX, currentY, futX, futY))){
						//whiteCheckersJumpForward(currentX, currentY, futX, futY);
						currentPicked = false;
						futPicked = false;
					}
					if(whiteCanSlideForward(currentX, currentY, futX, futY)){
						//whiteCheckersSlideForward(currentX, currentY, futX, futY);
						currentPicked = false;
						futPicked = false;
					}
				}
				*/
				
			GridPane newPane = reprintBoard();
			gameGrid = newPane;
			makeBoardClickable();
			checkersStage.setCenter(gameGrid);
		}
	} //startGame()
	
	private GridPane reprintBoard() {
		GridPane gameGrid = new GridPane();
		RowConstraints rowCon = new RowConstraints();
		ColumnConstraints colCon = new ColumnConstraints();
		for(int i = 0; i < 8; i++) {
			rowCon.setPrefHeight(64);
			rowCon.setValignment(VPos.CENTER);
			gameGrid.getRowConstraints().add(rowCon);
			
			colCon.setPrefWidth(64);
			colCon.setHalignment(HPos.CENTER);
			gameGrid.getColumnConstraints().add(colCon);
		}
		
		redCount = 0;
		whiteCount = 0;
		for(int i = 0; i < 8; i++) {	
			int count = i;
			
			int countMod = count%2;
			for(int j = 0; j < 8; j++) {		
				int jMod = j%2;
			
				Rectangle gridColor = new Rectangle();
				gridColor.setWidth(64);
				gridColor.setHeight(64);
				
				if(countMod == 0) {
					if ((jMod%2) == 0) {
						gridColor.setFill(Color.WHITE);
					}else {
						gridColor.setFill(Color.BLACK);
					} //if-else
				} 
				if (countMod == 1){
					if ((jMod%2) == 0) {
						gridColor.setFill(Color.BLACK);
					} else {
						gridColor.setFill(Color.WHITE);
					} //if-else
				} //if-else
				GridPane.setRowIndex(gridColor, i);
				GridPane.setColumnIndex(gridColor, j);
				gameGrid.getChildren().addAll(gridColor);
				
				if(containsRedChip[i][j] == true) {
					playerOneChips[redCount] = new Circle(10, Color.RED);
					playerOneChips[redCount].setStroke(Color.BLACK);
					
					gameGrid.add(playerOneChips[redCount], j, i);
					redCount++;
				}
				if(containsWhiteChip[i][j] == true) {
					playerTwoChips[whiteCount] = new Circle(10, Color.WHITE);
					playerTwoChips[whiteCount].setStroke(Color.BLACK);
					
					gameGrid.add(playerTwoChips[whiteCount], j, i);
					whiteCount++;
				}
				
			}//for
		} //for
		gameGrid.setPadding(new Insets(5,5,5,5));
		gameGrid.setGridLinesVisible(true);
		gameGrid.setPrefWidth(30.00);
		gameGrid.setPrefHeight(30.00);
		gameGrid.prefWidthProperty().bind(checkersStage.widthProperty());
		gameGrid.prefHeightProperty().bind(checkersStage.heightProperty());
		gameGrid.setStyle("-fx-background-color: #dda0dd; -fx-background-radius: 0; -fx-alignment: center; -fx-padding: 10px; -fx-background-insets: 2,2,2,2");
		
		return gameGrid;
	} //reprintBoard()
	
	void redCheckersSlideForward(double row, double col, double futRow, double futCol) {
		if(redCanSlideForward(row,col,futRow,futCol) == true)  {
			isEmptyGridArray[(int) futRow][(int) futCol] = false;
			containsRedChip[(int) futRow][(int) futCol] = true;
			containsRedChip[(int) row][(int) col] = false;
			
			playerOneChips[redCount] = new Circle(10, Color.RED);
			playerOneChips[redCount].setStroke(Color.BLACK);
			gameGrid.add(playerOneChips[redCount], (int) futRow, (int)futCol);
			addGridColor(row,col);
		
		}else {
			System.out.println("Invalid Move");
		}
		
	} //redCheckersSlideForward()
	
	void redCheckersJumpForward(double row, double col, double futRow, double futCol) {
		if(redCanJumpForward(row,col,futRow,futCol) == true)  {
			isEmptyGridArray[(int) futRow][(int) futCol] = false;
			containsRedChip[(int) futRow][(int) futCol] = true;
			containsRedChip[(int) row][(int) col] = false;
			double colNum = futCol - col;
			containsWhiteChip[(int) (row+1)][(int) (col+(colNum))] = false;
			pl1Score++;
			totalPl2Chips--;
			playerOneChips[redCount] = new Circle(10, Color.RED);
			playerOneChips[redCount].setStroke(Color.BLACK);
			gameGrid.add(playerOneChips[redCount], (int) futRow, (int)futCol);
			addGridColor(row,col);
			addGridColor(row+1, (col + (colNum)));
		
		}else {
			System.out.println("Invalid Move");
		}
		
	} //redCheckersJumpForward()

	private void addGridColor(double row, double col) {
		Rectangle gridColor = new Rectangle();
		gridColor.setWidth(64);
		gridColor.setHeight(64);
		if(((row%2) == 0) && ((col%2) == 0)){
			gridColor.setFill(Color.WHITE);
		} if(((row%2) == 0) && ((col%2) == 1)){
			gridColor.setFill(Color.BLACK);
		} if(((row%2) == 1) && ((col%2) == 0)){
			gridColor.setFill(Color.BLACK);
		} if(((row%2) == 1) && ((col%2) == 1)){
			gridColor.setFill(Color.WHITE);
		}
		
		gameGrid.add(gridColor,(int) col, (int)row);
		makeBoardClickable();
		
	} //addGridColor()
	
	
	boolean redCanSlideForward(double row, double col, double futRow, double futCol){
		if((containsRedChip[(int) row][(int) col] == true) && (isEmptyGridArray[(int) futRow][(int) futCol] == true)) {
			if (isRedDiagnolForward(row,col,futRow,futCol) == true) {
				return true;
			}
		}
		return false;
	} //redCanSlideForward()
	
	boolean whiteCanSlideForward(double row, double col, double futRow, double futCol){
		if((containsWhiteChip[(int) row][(int) col] == true) && (isEmptyGridArray[(int) futRow][(int) futCol] == true)) {
			if (isWhiteDiagnolForward(row,col,futRow,futCol) == true) {
				return true;
			}
		}
		return false;
	} //whiteCanSlideForward
	
	boolean isRedDiagnolForward(double row, double col, double futRow, double futCol) {
		if(((futRow-row) == 1) && (((futCol-col) == 1) || ((futCol-col) == -1))){
			return true;
		}
		return false;
	} //isRedDiagnolForward
	
	boolean isWhiteDiagnolForward(double row, double col, double futRow, double futCol) {
		if(((futRow-row) == -1) && (((futCol-col) == 1) || ((futCol-col) == -1))){
			return true;
		}
		return false;
	} //isWhiteDiagnolForward
	
	boolean isRedDiagnolJumpForward(double row, double col, double futRow, double futCol) {
		if(((futRow-row) == 2) && (((futCol-col) == 2) || ((futCol-col) == -2))){
			return true;
		}
		return false;
	} //isRedDiagnolJumpForward
	
	boolean isWhiteDiagnolJumpForward(double row, double col, double futRow, double futCol) {
		if(((futRow-row) == -2) && (((futCol-col) == 2) || ((futCol-col) == -2))){
			return true;
		}
		return false;
	} //isWhiteDiagnolJumpForward
	
	private boolean redCanJumpForward(double row, double col, double futRow, double futCol) {
		double rowNum = futRow - row;
		double colNum = futCol - col;
		if((containsRedChip[(int) row][(int) col] == true) && (isEmptyGridArray[(int) futRow][(int) futCol] == true) && (isRedDiagnolJumpForward(row,col,futRow,futCol) == true)) {
			if(containsWhiteChip[(int) (row+1)][(int) (col+(colNum))] == true){
				return true;
			} //if
		} //if
		return false;
	} //redCanJumpForward
	
	private boolean whiteCanJumpForward(double row, double col, double futRow, double futCol) {
		double rowNum = futRow - row;
		double colNum = futCol - col;
		if((containsWhiteChip[(int) row][(int) col] == true) && (isEmptyGridArray[(int) futRow][(int) futCol] == true) && (isWhiteDiagnolJumpForward(row,col,futRow,futCol) == true)) {
			if(containsRedChip[(int) (row-1)][(int) (col+(colNum))] == true){
				return true;
			} //if
		} //if
		return false;
	} //whiteCanJumpForward
	
	private boolean isEmpty(double x, double y) {
		if(isEmptyGridArray[(int) x][(int) y] == true) {
			return true;
		} else {
			return false;
		} //if-e;se
	} //isEmpty()
	
	private boolean isValidMove() {
		return false;
	} //validMove()
	
	private Node getGridContents(GridPane gridPane, int col, int row) {
		for (Node node : gameGrid.getChildren()) {
			if((gameGrid.getColumnIndex(node) == col) && (gameGrid.getRowIndex(node) == row)) {
				return node;
			}
		}
		return null;
	}
	
	
	/*
	 * Creates Tool bar for top of playing screen
	 * Tool bar includes: "New Game" button in which the user can start a new game
	 * 					"Quit Game" button in which the user can exit current game and go back to home screen
	 * 
	 */
	private ToolBar buildToolBar() {
		Button btnNew = new Button("New Game");
        Button btnQuit = new Button("Quit Game");
		
		ToolBar toolbar = new ToolBar(
				btnNew,
				btnQuit
		);
		
		//NEW button
		btnNew.setOnAction(e -> {
    		CheckersStage newGame = new CheckersStage();
    		checkersStage.getScene().setRoot(newGame.getRoot());
		});
		
		//QUIT button
		btnQuit.setOnAction(e -> {
	    		OpeningStage openingStage = new OpeningStage();
	    		checkersStage.getScene().setRoot(OpeningStage.getRoot());
	    });
		return toolbar;
	} //buildToolBar()
	
	/*
	 * Creates an 8x8 black and white Checkers board 
	 *
	 */
	private GridPane buildSimpleBoard() {
		GridPane gameGrid = new GridPane();
		RowConstraints rowCon = new RowConstraints();
		ColumnConstraints colCon = new ColumnConstraints();
		for(int i = 0; i < 8; i++) {
			rowCon.setPrefHeight(64);
			rowCon.setValignment(VPos.CENTER);
			gameGrid.getRowConstraints().add(rowCon);
			
			colCon.setPrefWidth(64);
			colCon.setHalignment(HPos.CENTER);
			gameGrid.getColumnConstraints().add(colCon);
		}
		
		for(int i = 0; i < 8; i++) {	
			int count = i;
			int countMod = count%2;
			for(int j = 0; j < 8; j++) {		//cols
				isEmptyGridArray[i][j] = true;
				containsRedChip[i][j] = false;
				containsWhiteChip[i][j] = false;
				int jMod = j%2;
			
				Rectangle gridColor = new Rectangle();
				gridColor.setWidth(64);
				//gridColor.widthProperty().bind(colCon.getMaxWidth());
				gridColor.setHeight(64);
				
				if(countMod == 0) {
					if ((jMod%2) == 0) {
						gridColor.setFill(Color.WHITE);
					}else {
						gridColor.setFill(Color.BLACK);
					} //if-else
				} 
				if (countMod == 1){
					if ((jMod%2) == 0) {
						gridColor.setFill(Color.BLACK);
					} else {
						gridColor.setFill(Color.WHITE);
					} //if-else
				} //if-else
				GridPane.setRowIndex(gridColor, i);
				GridPane.setColumnIndex(gridColor, j);
				gameGrid.getChildren().addAll(gridColor);
				
			}//for
		} //for
		return gameGrid;
	} //buildSimpleBoard()
	
	/*
	 * Adds pieces in the traditional starting positions of American Checkers.
	 *
	 */
	private GridPane fillStartingPositions(GridPane gameGrid) {
		
		int rowCount = 0;
		int playerOneTotalOnBoard = 0;
		for(int i = 0; playerOneTotalOnBoard < 12; i++) {
			int iMod = i%2;
			int colCount = 0;
			if(iMod == 0) {
				colCount = 1;
			} else {
				colCount = 0;
			}
			for(int j = 0; j < 4; j++) {
				playerOneChips[i] = new Circle(10, Color.RED);
				playerOneChips[i].setStroke(Color.BLACK);
				
				gameGrid.add(playerOneChips[i], colCount, rowCount);
				redCount++;
				isEmptyGridArray[rowCount][colCount] = false;
				containsRedChip[rowCount][colCount] = true;
				playerOneTotalOnBoard++;
				colCount = colCount+2;
			}
			rowCount++;
		}
		
		int rowsCount = 5;
		int playerTwoTotalOnBoard = 0;
		for(int i = 0; playerTwoTotalOnBoard < 12; i++) {
			int iMod = i%2;
			int colCount = 0;
			if(iMod == 0) {
				colCount = 0;
			} else {
				colCount = 1;
			}
			for(int j = 0; j < 4; j++) {
				playerTwoChips[i] = new Circle(10, Color.WHITE);
				playerTwoChips[i].setStroke(Color.BLACK);
				
				gameGrid.add(playerTwoChips[i], colCount, rowsCount);
				whiteCount++;
				isEmptyGridArray[rowsCount][colCount] = false;
				containsWhiteChip[rowsCount][colCount] = true;
				playerTwoTotalOnBoard++;
				colCount = colCount+2;
			}
			rowsCount++;
		}
		return gameGrid;
		
	} //fillStartingPositions()
	
	private void makeBoardClickable() {
		double tempX = currentX;
		double tempY = currentY;
		gameGrid.getChildren().forEach(item -> {
			item.setOnMouseClicked(new EventHandler<MouseEvent>(){
				boolean hasBeenLabeledSingle;
				boolean printedDouble = false;
				@Override
				public void handle(MouseEvent event) {
					Node source = (Node) event.getSource();
					Integer x = gameGrid.getRowIndex(source);
					Integer y = gameGrid.getColumnIndex(source);
					System.out.println(x);
					System.out.println(y);
					futX = x;
					futY = y;
					futPicked = true;
					
					playerTwoChips[whiteCount] = new Circle(10, Color.WHITE);
					playerTwoChips[whiteCount].setStroke(Color.BLACK);
					
					gameGrid.add(playerTwoChips[whiteCount], (int) futY, (int) futX);
				}
				});
				});
					/*
					int count = 0;
					if (hasBeenLabeledSingle) {
						System.out.println("Double");
						count++;
						printedDouble = true;
						hasBeenLabeledSingle = false;

					} else {
						hasBeenLabeledSingle = true;
						printedDouble = false;
						
						Timer t = new Timer("doubleclickTimer", false);
				        t.schedule(new TimerTask() {
				            public void run() {
				            		hasBeenLabeledSingle = false;
				            		printedDouble = false;
				            }
				        }, (long)500);
				    }	
					
					if(printedDouble == true) {
						double x = event.getX();
						double y = event.getY();
						futX = x;
						futY = y;
						futPicked = true;
						currentX = tempX;
						currentY = tempY;
						if(tempX == Double.NaN) {
							currentPicked = false;
						} else {
							currentPicked = true;
						} //if-else
						
					} else {
						double x = event.getX();
						double y = event.getY();
						currentX = x;
						currentY = y;
						currentPicked = true;
					} //if-else
				
				} //handle()
			}); //setMouseOnClicked()
		}); //forEach()	
		*/			
	} //makeBoardClickable()
	
	/*
	 * Creates display for user that projects the welcome and their scores
	 */
	private VBox displayScores() {
		VBox vb = new VBox();
		vb.setPadding(new Insets(10));
	    vb.setSpacing(40);
		//Rectangle playerScore = new Rectangle(150.0,800.0);
		//playerScore.setStroke(Color.BLACK);
		
		Text welc = new Text("      Welcome to ");
		welc.setFont(Font.font("Verdana", 18));
		welc.setFill(Color.BLACK);
		welc.setStroke(Color.BLACK); 
		
		Text welc2 = new Text("American Checkers!!");
		welc2.setFont(Font.font("Verdana", 18));
		welc2.setFill(Color.BLACK);
		welc2.setStroke(Color.BLACK); 
		
		Text emptyText = new Text(" ");
		emptyText.setFont(Font.font("Verdana", FontWeight.BOLD, 36));
		emptyText.setFill(Color.WHITE);
		emptyText.setStroke(Color.BLACK);
		
		Text scoreText1 = new Text("RED PLAYER'S SCORE: ");
		scoreText1.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		scoreText1.setFill(Color.WHITE);
		scoreText1.setStroke(Color.BLACK); 
		
		Text display = new Text("            "+pl1Score);
		display.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		display.setFill(Color.WHITE);
		display.setStroke(Color.BLACK);
		
		Text scoreText2 = new Text("WHITE PLAYER'S SCORE: ");
		scoreText2.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		scoreText2.setFill(Color.WHITE);
		scoreText2.setStroke(Color.BLACK); 
		
		Text display2 = new Text("            "+pl2Score);
		display2.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		display2.setFill(Color.WHITE);
		display2.setStroke(Color.BLACK);
		
		vb.getChildren().addAll(welc, welc2, emptyText, scoreText1, display, scoreText2, display2);
		vb.setAlignment(Pos.CENTER_LEFT);
		
		return vb;
	}
	
	/*
	 * Returns the current working Scene of the App.
	 *
	 */
	public Pane getRoot() {
		return checkersStage;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
