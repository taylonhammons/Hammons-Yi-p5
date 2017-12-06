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
import javafx.scene.layout.Pane;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;


public class ArcadeApp extends Application {
	
	/*
	 * Opening Pane for introduction
	 */
	BorderPane openingStage = new BorderPane();
	
	
    @Override    
    public void start(Stage stage) {  
    	
    		openingStage.setPrefSize(800.00, 800.00);
    		
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
		openingStage.setTop(toolbar);
		openingStage.setRight(color2);
		openingStage.setLeft(color3);
		HBox hbox = addHBox();
		openingStage.setCenter(hbox);
		hbox.prefHeightProperty().bind(openingStage.heightProperty());
		
    		
    		Group group = new Group(); // main container 
    		group.getChildren().add(openingStage);
    		Scene scene = new Scene(group);        
    		stage.setTitle("Csci1302-Arcade!!");         
    		stage.setScene(scene);         
    		stage.sizeToScene();    
    		stage.centerOnScreen();
    		stage.show(); 
    		
    		// the group must request input focus to receive key events
    		// @see https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#requestFocus--
    		group.requestFocus();
    	}// start    

	public static void main(String[] args) {        
		try {
			Application.launch(args);
		} catch (UnsupportedOperationException e) {
			System.out.println(e);
			System.err.println("If this is a DISPLAY problem, then your X server connection");
			System.err.println("has likely timed out. This can generally be fixed by logging");
			System.err.println("out and logging back in.");
			System.exit(1);
		} // try
	} // main
	
	
	public Pane getRoot() {
		return openingStage;
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
	
	}// Arcade Opening


