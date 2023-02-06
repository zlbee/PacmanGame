package pacman.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class defines a rectangle that only 
 * has one unit width and any unit length
 * @see Maze
 * @author Zhangli Wang
 *
 */
public class BarObstacle extends Rectangle {
	/**
	 * the color of {@code BarObstacle} with an initial value {@code Color.CADETBLUE}
	 */
	public static Color obstacleColor = Color.CADETBLUE;
	/**
	 * the unit thickness of a block, {@code "25"}
	 */
    public static double THICKNESS = 25;
    
    /**
     * Initialize a bar obstacle with a certain width {@code THICKNESS} and any
     * units of length by the parameter {@code length}. 
     * <p>The orientation is either "horizontal" or "vertical". The color of the
     * bar obstacle is passed by the static parameter {@code obstacleColor}
     * @param x - the index of x in the maze
     * @param y - the index of y in the maze
     * @param orientation - "horizontal" or "vertical"
     * @param length - the length of the bar (1 == 25px)
     */
    public BarObstacle(double x, double y, String orientation, double length) {
        this.setX(x);
        this.setY(y);
        if (orientation.equals("horizontal")) {
            this.setHeight(BarObstacle.THICKNESS);
            this.setWidth(length * BarObstacle.THICKNESS);
        } else {
            this.setHeight(length * BarObstacle.THICKNESS);
            this.setWidth(BarObstacle.THICKNESS);
        }
        this.setFill(obstacleColor);
        this.setStrokeWidth(5);
    }
}
