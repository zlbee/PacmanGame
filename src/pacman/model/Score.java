package pacman.model;



import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This class stores score of the game, including {@link Text} {@code score} and {@link Text} {@code lifes}.
 * @see GameModel
 * @author Zhangli Wang
 *
 */
public class Score {
	/**
	 * the score point
	 */
    public Text score;
    /**
     * the remaining lifes
     */
    public Text lifes;
    /**
     * count of the level in the infinite mode
     */
    public Text level;

    /**
     * Initialize {@code score} and {@code lifes}.
     * {@code score} is initialized as {@code "Score: 0"} and {@code lifes} is initialized as {@code "Lifes: 3"}.
     * Color and font of text is certain.
     */
    Score() {
        this.score = new Text(BarObstacle.THICKNESS * 4, BarObstacle.THICKNESS * 28, "Score: 0");
        this.lifes = new Text(BarObstacle.THICKNESS * 20, BarObstacle.THICKNESS * 28,"Lifes: 3");
        this.level = new Text(BarObstacle.THICKNESS * 36, BarObstacle.THICKNESS * 28,"Level: 1");
        score.setFill(Color.MAGENTA);
        score.setFont(Font.font("Arial", 30));

        lifes.setFill(Color.MAROON);
        lifes.setFont(Font.font("Arial", 30));
        
        level.setFill(Color.MAROON);
        level.setFont(Font.font("Arial", 30));
    }
}
