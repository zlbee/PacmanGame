package pacman.model;

import javafx.scene.paint.Color;

/**
 * This class is a special bar obstacle for ghost but not the pacman.
 * @see Maze
 * @see BarObstacle
 * @author Zhangli Wang
 *
 */
public class GhostBarObstacle extends BarObstacle {

	/**
	 * Initialize a ghost bar obstacle with a new color {@code Color.LIGHTSKYBLUE}.
	 * Other properties are the same as {@code BarObstacle}
	 * @param x index of x
	 * @param y index of y
	 * @param orientation orientation of the bar obstacle
	 * @param length length of the bar obstacle
	 */
	public GhostBarObstacle(double x, double y, String orientation, double length) {
		super(x, y, orientation, length);
		this.setFill(Color.LIGHTSKYBLUE);
		this.setStrokeWidth(5);
	}

}
