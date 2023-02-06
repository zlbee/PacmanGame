package pacman.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The class initiates highscore.fxml.
 * @see HighScoreController
 * @author Zhangli Wang
 *
 */
public class HighScore extends Application {
	/**
	 * a new stage to show high score
	 */
	public static Stage highScoreStage = new Stage();

	/**
	 * Initiate the highscore.fxml.
	 * <p>Load all children from highscore.fxml, create a scene that contains all these children 
	 * and show the scene in {@code theStage}.
	 * @param theStage the stage that holds highscore.fxml
	 */
	@Override
	public void start(Stage theStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/pacman/view/highscore.fxml"));
		Scene setupScene = new Scene(root);

		theStage.setScene(setupScene);
		theStage.show(); 
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Call {@code start} to run on a new stage named {@code highScoreStage}.
	 * @throws Exception unable to show window
	 */
	public void  showWindow() throws Exception {
		start(highScoreStage);
	}

}
