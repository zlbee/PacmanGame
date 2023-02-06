package pacman.controller;

import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import pacman.model.Score;

/**
 * The class operates with controls in highscore.fxml.
 * @author Zhangli Wang
 *
 */
public class HighScoreController {

	/**
	 * the button with a text "Close" in highscore.fxml.
	 */
    @FXML
    private Button closeBtn;
    
    /**
     * the text area in the center to display high scores
     */
    @FXML
    private TextArea scoreBoard;

    /**
     * Initialize controls.
     * Before highscore.fxml is loaded, all texts in highscore.txt are read and appended to {@code scoreBoard} 
     * by the {@code TextArea.appendText} method.
     */
    @FXML
    void initialize() {
        String readString;
        Reader reader = new Reader();
        readString = reader.readScores();       
        this.scoreBoard.appendText(readString);
    }
    
    /**
     * Close the high score stage, {@code highScoreStage}.
     * @param event the button is clicked.
     */
    @FXML
    void close(ActionEvent event) {
    	HighScore.highScoreStage.close();
    }
    

}
