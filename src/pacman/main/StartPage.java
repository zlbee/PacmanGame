package pacman.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pacman.controller.StartpageController;

/**
 * The class initiates startpage.fxml.
 * @see StartpageController
 * @author Zhangli Wang
 *
 */
public class StartPage extends Application {
	/**
	 * the stage of start page
	 */
	public static Stage startStage = new Stage();

	/**
	 * Initiate the startpage.fxml.
	 * <p>Load all children from startpage.fxml, create a scene that contains all these children 
	 * and show the scene in {@code theStage}.
	 * @param theStage the stage that holds startpage.fxml
	 */
	@Override
	public void start(Stage theStage) throws Exception {
		theStage.setTitle("pacman");

        Parent root = FXMLLoader.load(getClass().getResource("/pacman/view/startpage.fxml"));
        
        Scene startpageScene = new Scene(root);

        theStage.setScene(startpageScene);
        theStage.show(); 
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Call {@code start} to run on the passed stage.
	 * @throws Exception unable to show window
	 * @param stage the stage to show the content
	 */
	public void  showWindow(Stage stage) throws Exception {
		//Stage stage = new Stage();
		start(stage);
	}

}
