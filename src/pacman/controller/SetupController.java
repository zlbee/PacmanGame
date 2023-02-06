package pacman.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pacman.main.StartPage;
import pacman.model.BarObstacle;

/**
 * The class operates with controls in setup.fxml.
 * @author Zhangli Wang
 *
 */
public class SetupController {

	/**
	 * the upper color picker on the anchor pane
	 * to pick color and set background color of the game page
	 */
    @FXML
    private ColorPicker backgroundColorPicker;

    /**
     * the bottom color picker on the anchor pane 
     * to pick color and set obstacle color of the game page
     */
    @FXML
    private ColorPicker wallColorPicker;
    
    /**
     * the button named "Back" on the anchor pane
     */
    @FXML
    private Button backBtn;
    
    /**
     * the image view scaled with the whole anchor pane placed at the lowest layer
     * to import an image.
     */
    @FXML
    private ImageView imgView;
    
    /**
     * When event is passed, set the color and switch scene.
     * <p>The background color of {@code Pacman} is changed to the color {@code backgroundColorPicker.getValue()}.
     * The obstacle color of {@code BarObstacle} is changed to the color {@code wallColorPicker.getValue()}.
     * The scene is switched to the start page.
     * @param event - back button is clicked
     * @throws Exception
     */
    @FXML
    void toStartPage(ActionEvent event) throws Exception {
    	Pacman.backgroundColor = backgroundColorPicker.getValue();
    	BarObstacle.obstacleColor = wallColorPicker.getValue();
    	
    	Stage theStage = (Stage) backBtn.getScene().getWindow();
    	StartPage startpage = new StartPage();
    	startpage.showWindow(theStage);
    }
    
    /**
     * Initialize the background image by importing image to {@code imgView}.
     */
    @FXML
    void initialize() {
    	Image image = new Image("/pacman/view/pageimage.png");
		imgView.setImage(image);

    }
}
