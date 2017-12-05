package cs1302.p5;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class OpeningStage {
	
	/*
	 * Opening Pane for introduction
	 */
	static BorderPane openingStage = new BorderPane();
	
	
	public OpeningStage() {

		
		/*
		 * Creates Toolbar for top of opening screen
		 * 
		 */
		ToolBar toolbar = new ToolBar(
				new Button("File"),
				new Button("Help")
		);
		 
		/*
		 * Creates Color Label for introduction background 
		 */
		Label color = new Label();
		color.setStyle("-fx-background-color: #dda0dd; -fx-padding: 10px;");
		
		Label color2 = new Label();
		color2.setStyle("-fx-background-color: #dda0dd; -fx-padding: 10px;");
		
		Label color3 = new Label();
		color3.setStyle("-fx-background-color: #dda0dd; -fx-padding: 10px;");
		
		/*
		 *Stage Border Pane for the opening stage 
		 */
		openingStage.setStyle("-fx-background-color: #dda0dd;");
		openingStage.setBottom(color);
		openingStage.setRight(color2);
		openingStage.setLeft(color3);
		HBox hbox = addHBox();
		openingStage.setCenter(hbox);
		openingStage.setTop(toolbar);
		
	}
	
	/*
     * Creates Pane to be implemented in center of opening
     * Holds the Tetris and Checkers buttons
     * 
     */
    private HBox addHBox() {
    	    HBox hbox = new HBox();
    	    hbox.setPadding(new Insets(15, 12, 15, 12));
    	    hbox.setSpacing(10);
    	    hbox.setStyle("-fx-background-color: #dda0dd;");

    	    Image tetrisImage = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Emacs_Tetris_vector_based_detail.svg/288px-Emacs_Tetris_vector_based_detail.svg.png");
    	    ImageView tetrisIV = new ImageView();
    	    tetrisIV.setImage(tetrisImage);
    	    tetrisIV.setFitHeight(300);
    	    tetrisIV.setFitWidth(200);
    	    Button buttonTetris = new Button("", tetrisIV);
    	    buttonTetris.setPrefSize(200, 300);
    	    buttonTetris.setOnAction(e -> {
    	    		TetrisStage newTetris = new TetrisStage();
    	    		openingStage.getScene().setRoot(newTetris.getRoot());
    	    });
    	    

    	    Image checkersImage = new Image("http://www.margegower.com/wp-content/uploads/2013/09/checkers.jpg");
    	    ImageView checkersIV = new ImageView();
    	    checkersIV.setImage(checkersImage);
    	    checkersIV.setFitHeight(200);
    	    checkersIV.setFitWidth(200);
    	    Button buttonCheckers = new Button("", checkersIV);
    	    buttonCheckers.setPrefSize(200, 300);
    	    buttonCheckers.setOnAction(e -> {
	    		CheckersStage newCheckers = new CheckersStage();
	    		openingStage.getScene().setRoot(newCheckers.getRoot());
	    });
    	    hbox.getChildren().add(buttonTetris);
    	    hbox.getChildren().add(buttonCheckers);
    	    
    	    return hbox;
	}
	
	public static Pane getRoot() {
		return openingStage;
	}

}

