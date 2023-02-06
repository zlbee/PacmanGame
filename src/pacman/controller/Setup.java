package pacman.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * The class initiates setup.fxml.
 * @see SetupController
 * @author Zhangli Wang
 *
 */
public class Setup extends Application{

	/**
	 * Initiate the setup.fxml.
	 * <p>Load all children from setup.fxml, create a scene that contains all these children 
	 * and show the scene in {@code theStage}.
	 * @param theStage the stage that holds setup.fxml
	 */
	@Override
	public void start(Stage theStage) throws Exception {
		theStage.setTitle("pacman");

        Parent root = FXMLLoader.load(getClass().getResource("/pacman/view/setup.fxml"));
        
		Scene setupScene = new Scene(root);

		theStage.setScene(setupScene);
		theStage.show(); 
		
	}
	
	public static void main(String[] args) {
		launch(args);

	}
	
	/**
	 * Call {@code start} to run on the passed stage {@code stage}.
	 * @throws Exception unable to show window
	 * @param stage the stage to load the content
	 */
	public void  showWindow(Stage stage) throws Exception {
		//Stage stage = new Stage();
		start(stage);
	}
	
}
