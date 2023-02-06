package pacman.model;



import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/**
 * This class contains a set of bar obstacles {@code obstacles} 
 * and a set of ghost bar obstacles {@code ghostObstacles}.
 * This class stores the data for the maze, which are all rectangles that can be displayed.
 * @see GameModel
 */
public class Maze {

	/**
	 * the set of {@code BarObstacle}
	 */
    public ArrayList<BarObstacle> obstacles;
    /**
     * the set of {@code ghostBarObstacle}
     */
    public ArrayList<GhostBarObstacle> ghostObstacles;

    /**
     * Initialize the two sets {@code obstacles}, {@code ghostObstacles}.
     */
    Maze() {
        obstacles = new ArrayList<>();
        ghostObstacles = new ArrayList<>();
    }

    /**
     * Checks if point is touching obstacles.
     * @param x - index of x
     * @param y - index of y
     * @param padding - gap between the point and obstacles
     * @return {@code true} if point is touching {@code obstacles},
     * {@code false} if point is not touching {@code obstacles}
     */
    public Boolean isTouching(double x, double y, double padding) {
        for (BarObstacle barObstacle:obstacles) {
            if (
                x >= barObstacle.getX() - padding &&
                x <= barObstacle.getX() + padding + barObstacle.getWidth() &&
                y >= barObstacle.getY() - padding &&
                y <= barObstacle.getY() + padding + barObstacle.getHeight())
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if point is touching ghost obstacles.
     * @param x - index of x
     * @param y - index of y
     * @param padding the gap between the ghost edge and ghost obstacles
     * @return {@code true} if point is touching {@code ghostObstacles},
     * {@code false} if point is not touching {@code ghsotObstacles}
     */
    public Boolean isGhostTouching(double x, double y, double padding) {
        for (BarObstacle barObstacle:ghostObstacles) {
            if (
                x >= barObstacle.getX() - padding &&
                x <= barObstacle.getX() + padding + barObstacle.getWidth() &&
                y >= barObstacle.getY() - padding &&
                y <= barObstacle.getY() + padding + barObstacle.getHeight())
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if there's an obstacle in the rectangular area.
     * In the rectangular area designated by {@code fromX}, {@code fromY}, {@code toX}, {@code toY},
     * check whether all points in this area is not touching obstacles by calling {@code isTouching} method.
     * @param fromX left edge of the rectangular area
     * @param toX right edge of the rectangular area
     * @param fromY upper edge of the rectangular area
     * @param toY bottom edge of the rectangular area
     * @return {@code false} is at least one point in this area is touching obstacles, {@code true} if all the
     * area is not touching obstacles.
     */
    
    public Boolean hasObstacle(double fromX,  double toX, double fromY, double toY) {
        boolean isTouching = false;
        for (double i = fromX; i < toX; i++) {
            for (double j = fromY; j < toY; j++) {
                if (this.isTouching(i, j, 0)) isTouching = true;
            }
        }
        return isTouching;
    }
    

    /**
     * Initialize {@code obstacles} and {@code ghostObstacles} for the maze.
     * <p>All obstacles are initialized with certain position with certain orientation and length to compose 
     * and added to the set {@code obstacle} and {@code ghostObstacles}.
     * Since all obstacles are rectangle, they can be visualized.
     * The visualized maze will have a boundary frame and all vertical and horizontal bar obstacles in it. 
     */
    public void CreateMaze() {
        //~~~~~~~~~~~~~~~~~~~~~~~~~ frame ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // top
        this.obstacles.add(new BarObstacle(0, 0, "horizontal", 48));
        // bottom
        this.obstacles.add(new BarObstacle(0, 600, "horizontal", 48));
        // left
        this.obstacles.add(new BarObstacle(0, 0, "vertical", 11));
        this.obstacles.add(new BarObstacle(0, 350, "vertical", 11));
        // right
        this.obstacles.add(new BarObstacle(1225 - BarObstacle.THICKNESS, 0, "vertical", 11));
        this.obstacles.add(new BarObstacle(1225 - BarObstacle.THICKNESS, 350, "vertical", 11));

        //~~~~~~~~~~~~~~~~~~~~~~~~~ Islands ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // obsTopLeft
        this.obstacles.add(new BarObstacle(12 * BarObstacle.THICKNESS, BarObstacle.THICKNESS, "vertical", 4));
        // obsTopRight
        this.obstacles.add(new BarObstacle(36 * BarObstacle.THICKNESS, BarObstacle.THICKNESS, "vertical", 4));
        // obsBottomLeft
        this.obstacles.add(new BarObstacle(12 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, "vertical", 4));
        // obsBottomRight
        this.obstacles.add(new BarObstacle(36 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, "vertical", 4));
        // obsTopMiddle
        this.obstacles.add(new BarObstacle(16 * BarObstacle.THICKNESS, 4 * BarObstacle.THICKNESS, "horizontal", 17));
        // obsBottomMiddle
        this.obstacles.add(new BarObstacle(16 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, "horizontal", 17));
        // obsLMTop
        this.obstacles.add(new BarObstacle(8 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, "horizontal", 5));
        // obsLMTop4
        this.obstacles.add(new BarObstacle(8 * BarObstacle.THICKNESS, 12 * BarObstacle.THICKNESS, "horizontal", 5));
        // obsLMBottom
        this.obstacles.add(new BarObstacle(8 * BarObstacle.THICKNESS, 16 * BarObstacle.THICKNESS, "horizontal", 5));
        // obsRMTop
        this.obstacles.add(new BarObstacle(36 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, "horizontal", 5));
        // obsRMTop2
        this.obstacles.add(new BarObstacle(36 * BarObstacle.THICKNESS, 12 * BarObstacle.THICKNESS, "horizontal", 5));
        // obsRMBottom
        this.obstacles.add(new BarObstacle(36 * BarObstacle.THICKNESS, 16 * BarObstacle.THICKNESS, "horizontal", 5));
        // LobsLeftTop1
        this.obstacles.add(new BarObstacle(4 * BarObstacle.THICKNESS, 4 * BarObstacle.THICKNESS, "horizontal", 5));
        // LobsLeftTop2
        this.obstacles.add(new BarObstacle(4 * BarObstacle.THICKNESS, 5 * BarObstacle.THICKNESS, "vertical", 6));
        // LobsLeftBottom1
        this.obstacles.add(new BarObstacle(4 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, "horizontal", 5));
        // LobsLeftBottom2
        this.obstacles.add(new BarObstacle(4 * BarObstacle.THICKNESS, 600 - 10 * BarObstacle.THICKNESS, "vertical", 6));
        // LobsRightTop1
        this.obstacles.add(new BarObstacle(40 * BarObstacle.THICKNESS, 4 * BarObstacle.THICKNESS, "horizontal", 5));
        // LobsRightTop2
        this.obstacles.add(new BarObstacle(44 * BarObstacle.THICKNESS, 5 * BarObstacle.THICKNESS, "vertical", 6));
        // LobsRightBottom1
        this.obstacles.add(new BarObstacle(40 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, "horizontal", 5));
        // LobsRightBottom2
        this.obstacles.add(new BarObstacle(44 * BarObstacle.THICKNESS, 600 - 10 * BarObstacle.THICKNESS, "vertical", 6));
        // cageBottom
        this.obstacles.add(new BarObstacle(16 * BarObstacle.THICKNESS, 600 - 8 * BarObstacle.THICKNESS, "horizontal", 17));
        // cageRightV
        this.obstacles.add(new BarObstacle(32 * BarObstacle.THICKNESS, 600 - 16 * BarObstacle.THICKNESS, "vertical", 8));
        // cageLeftV
        this.obstacles.add(new BarObstacle(16 * BarObstacle.THICKNESS, 600 - 16 * BarObstacle.THICKNESS, "vertical", 8));
        // cateRightH
        this.obstacles.add(new BarObstacle(17 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, "horizontal", 5));
        // cateLeftH
        this.obstacles.add(new BarObstacle(27 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, "horizontal", 5));
        
        this.ghostObstacles.add(new GhostBarObstacle(22 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, "horizontal", 5));

    }
}
