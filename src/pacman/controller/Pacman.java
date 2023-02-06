package pacman.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import pacman.model.BarObstacle;
import pacman.model.GameModel;

/**
 * The class initiates pacman.fxml.
 * @see GameController
 * @author Zhangli Wang
 *
 */
public class Pacman extends Application {
	/**
	 * the background color of game board with initial value {@code Color.WHITE} 
	 */
    public static Color backgroundColor = Color.WHITE;
	
	// https://stackoverflow.com/questions/35956527/javafx-javafx-scene-layout-anchorpane-cannot-be-cast-to-javafx-scene-layout-bo
    // https://blog.csdn.net/maosijunzi/article/details/43155493
	/**
	 * Initiate the pacman.fxml.
	 * <p>Add all children of root to a created scene.
	 * Create a game canvas with certain size and set the background color of it.
	 * Receive keyboard input to handle game event. 
	 * Restarting game, moving pacman, stopping pacman can be directed by keyboard input.
	 * @param theStage the stage that holds pacman.fxml
	 */
    @Override
    public void start(Stage theStage) throws Exception{
        theStage.setTitle( "Pacman" );
    	
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Pacman.class.getResource("/pacman/view/pacman.fxml"));
        Group root = (Group) loader.load();

        Scene pacmanScene = new Scene(root);
        theStage.setScene(pacmanScene);
        
        Canvas canvas = new Canvas(1225, 600);
        root.getChildren().add(canvas);
        
        GraphicsContext gameSpace = canvas.getGraphicsContext2D();
        gameSpace.setFill(backgroundColor);
        gameSpace.fillRect(0,0,1225,600);       

        GameController gameController = new GameController(root);

        gameController.drawBoard();

        pacmanScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameController.movePacman(event));
        pacmanScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> gameController.stopPacman(event));
        pacmanScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameController.restartGameDisplay(event));

        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
	/**
	 * Call {@code start} to run on a new stage created.
	 * @throws Exception unable to show window
	 */
	public void  showWindow() throws Exception {
		Stage stage = new Stage();
		start(stage);
	}
}