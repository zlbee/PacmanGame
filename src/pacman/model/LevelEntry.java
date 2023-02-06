package pacman.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class defines a rectangular level entry
 * that can be set visible or not.
 * @see GameModel
 * @author Zhangli Wang
 *
 */
public class LevelEntry extends Rectangle {
	GameModel gameModel = GameModel.getInstance();
	/**
	 * Initialize the level entry with a position.
	 * The size is 50*50 and color is {@code Color.GREENYELLOW}.
	 * The level entry is initialized invisible.
	 * @param x index of x of the rectangular level entry
	 * @param y index of y of the rectangular level entry
	 */
	public LevelEntry(double x, double y) {
		this.setFill(Color.GREENYELLOW);
        this.setX(x);
        this.setY(y);
        this.setHeight(50);
        this.setWidth(50);
        this.hide();
	}
	
	/**
	 * Hide the level entry.
	 */
    public void hide() {
        this.setVisible(false);
    }

    /**
     * Show the level entry.
     */
    public void show() {
        this.setVisible(true);
    }

}
