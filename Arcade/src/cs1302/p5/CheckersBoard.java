package cs1302.p5;

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

public class CheckersBoard extends Application {
	
private Cell [][] gridArray = new Cell [8][8];
private Circle[] playerOneChips = new Circle[12];
private Circle[] playerTwoChips = new Circle[12];
private static GridPane gameBoard = new GridPane();

@Override
public void start(Stage primaryStage) throws Exception {
}

public CheckersBoard() {
	/*
	/*
	 *Build Grid for the game 
	 *
	 
	Cell[][] colorGridArray = new Cell [8][8];
	String[][] isEmptyGridArray = new String [8][8];
	String[][] whatPlayerArray = new String [8][8];
	
	for(int i = 0; i < colorGridArray.length-1; i++) {
		for(int j = 0; j < colorGridArray.length-1; j++) {
			if ((i%2) == 0) {
				int count = 0;
				if ((count%2) == 0) {
					colorGridArray[i][j] = "WHITE";
				}else {
					colorGridArray[i][j] = "BLACK";
				} //if-else
				count++;
			} else {
				int count = 0;
				if((count%2) == 0) {
					colorGridArray[i][j]= "BLACK";
				}else {
					colorGridArray[i][j]= "WHITE";
				} //if-else
				
			} //if-else
		} //for
	} //for
	
	//make grid pane
	 * 
	 */
	for(int i = 0; i < 8; i++) {
		RowConstraints rowCon = new RowConstraints();
		rowCon.setMinHeight(64);
		rowCon.setMaxHeight(64);
		rowCon.setPrefHeight(64);
		rowCon.setValignment(VPos.CENTER);
		gameBoard.getRowConstraints().add(rowCon);
		
		ColumnConstraints colCon = new ColumnConstraints();
		colCon.setMinWidth(64);
		colCon.setMaxWidth(64);
		colCon.setPrefWidth(64);
		colCon.setHalignment(HPos.CENTER);
		gameBoard.getColumnConstraints().add(colCon);
	}
	for(int i = 0; i < 8; i++) {		//row
		for(int j = 0; j < 8; j++) {		//cols
			int n = 0;
			int nMod = n%2;
			Rectangle gridColor = new Rectangle();
			gridColor.setWidth(20);
			gridColor.setHeight(20);
			if(nMod == 0) {
				gridColor.setFill(Color.WHITE);
			}
			if(nMod == 1) {
				gridColor.setFill(Color.BLACK);
			}
			
			GridPane.setRowIndex(gridColor, i);
			GridPane.setColumnIndex(gridColor, j);
			gameBoard.getChildren().addAll(gridColor);
		}
	
	}
	gameBoard.setPadding(new Insets(15,15,15,15));
	gameBoard.setGridLinesVisible(true);
		
	//fill colors
	//assignBoardGridColor(gameBoard);
	
	//gameBoard.setPadding(new Insets(15,15,15,15));
	Scene scene = new Scene(gameBoard,700,700);
	//primaryStage.setTitle("American Checkers");
	//primaryStage.setScene(scene);
	//primaryStage.show();

} //start
	
	//private void buildSimpleBoardGrid(){
	
	//}
	
	/*
	 * Assigns the GridPane colors for the board game. The board game consists of black and white tiles.
	 */
	private void assignBoardGridColor(GridPane board) {
		
		for(int i = 0; i < 8; i++) {		//row
			for(int j = 0; j < 8; j++) {		//cols
				int n = 0;
				int nMod = n%2;
				Rectangle gridColor = new Rectangle();
				gridColor.setWidth(2);
				gridColor.setHeight(2);
				if(nMod == 0) {
					gridColor.setFill(Color.WHITE);
				}
				if(nMod == 1) {
					gridColor.setFill(Color.BLACK);
				}
				
				GridPane.setRowIndex(gridColor, i);
				GridPane.setColumnIndex(gridColor, j);
				gameBoard.getChildren().addAll(gridColor);
			}
		}
	}
	
	public boolean isEmpty() {
		return false;
	} //isEmpty()

	public static Pane getRoot() {
		return gameBoard;
	}

	
	
} //CheckersBoard
