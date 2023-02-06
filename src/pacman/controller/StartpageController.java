package pacman.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pacman.main.StartPage;
import pacman.model.GameModel;

/**
 * The class operates with controls in startpage.fxml.
 * @author Zhangli Wang
 *
 */
public class StartpageController {

	/**
	 * the button named "Setup" on the anchor pane
	 */
    @FXML
    private Button setupBtn;
    
    /**
     * the button named "Infinite Mode" on the anchor pane
     */
    @FXML
    private Button infiniteGameBtn;
    
    /**
     * the button named "Normal Mode" on the anchor pane
     */
    @FXML
    private Button startgameBtn;

    /**
     * the image view scaled with the whole anchor pane
     * to import image
     */
    @FXML
    private ImageView imgView;
    
    /**
     * Initialize the background image by importing image to {@code imgView}.
     */
    @FXML
    void initialize() {
    	Image image = new Image("/pacman/view/pageimage.png");
		imgView.setImage(image);

    }
    
    // https://blog.csdn.net/u013975785/article/details/81262020
    /**
     * When event is passed, turn the scene to setup page.
     * @param event setup button is clicked
     * @throws Exception
     */
    @FXML
    void toSetup(ActionEvent event) throws Exception {
    	Stage theStage = (Stage) setupBtn.getScene().getWindow();
    	pacman.controller.Setup setup = new Setup();
    	setup.showWindow(theStage);
    }
    
    // https://blog.csdn.net/legendnovo/article/details/10555941
    /**
     * When event is passed, turn the scene to game page.
     * The game is set as not infinite.
     * @param event start game button is clicked
     * @throws Exception
     */
    @FXML
    void toPacman(ActionEvent event) throws Exception {
    	GameModel.getInstance().infiniteGame = false;
    	Stage theStage = (Stage) startgameBtn.getScene().getWindow();
    	theStage.hide();
    	pacman.controller.Pacman pacman = new Pacman();
    	pacman.showWindow();
    	StartPage.startStage.close();
    }
    
    /**
     * When event is passed, turn the scene to game page.
     * The game is set as infinite.
     * @param event start game button is clicked
     * @throws Exception
     */
    @FXML
    void toInfinitePacman(ActionEvent event) throws Exception {
    	GameModel.getInstance().infiniteGame = true;
    	Stage theStage = (Stage) startgameBtn.getScene().getWindow();
    	theStage.hide();
    	pacman.controller.Pacman pacman = new Pacman();
    	pacman.showWindow();
    }
}
