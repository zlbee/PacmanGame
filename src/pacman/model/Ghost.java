package pacman.model;



import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

/**
 * This class defines a ghost as a running thread.
 * <p>The ghost is a rectangle that has a position and keeps moving.
 * The movement of ghost is always towards one direction. If it is
 * not able to move forward, its direction will be randomly changed.
 * The ghost can teleport between portals.
 * @see GameModel
 * @author Zhangli Wang
 *
 */
public class Ghost extends Rectangle implements Actor, Runnable {

	/**
	 * current direction of the ghost, {@code "left", "right", "up", "down"}
	 */
    String direction;
    /**
     * the game model instance
     */
    GameModel gameModel = GameModel.getInstance();
    /**
     * maze of the game model
     */
    Maze maze;
    /**
     * animation of the ghost
     */
    AnimationTimer animation;
    /**
     * a count for the times that the ghost try to change direction and walk
     */
    int timesWalked;
    /**
     * name of the ghost to match the ghost image
     */
    String ghostname;
    /**
     * a count for ghost ID 
     * that will be used to read different ghost images
     */
    public static int ghostId = 1;
    /**
     * speed of ghost
     */
    public static double ghostStep = 5;

    /**
     * Initialize a ghost with a position.
     * <p>The ghost is initialized with a certain size and different images according to {@code ghostId}.
     * Once a ghost is initialized, it will start its movement with {@code Ghost.createAnimation} method.
     * @param x - index of x
     * @param y - index of y
     */
    public Ghost(double x, double y) {
        this.setX(x);
        this.setY(y);
        this.ghostname = "ghost"+ghostId;
        this.ghostId++;
        this.maze = gameModel.maze;
        this.setHeight(50);
        this.setWidth(50);
        Image ghostImg = new Image("/pacman/view/"+this.ghostname+".png");
        this.setFill(new ImagePattern(ghostImg));
        ///this.setFill(color);
        this.timesWalked = 0;
        this.direction = "down";
        this.animation = createAnimation(this.direction);
    }
    
    /**
     * Generate a random {@link String} direction without accounting possibility of two optional directions, {@code exclude1} and {@code exclude2}.
     * Four directions are {@code "left", "right", "up", "down"}.
     * @param exclude1 - first direction to omit
     * @param exclude2 - second direction to omit
     * @return a random {@link String} direction
     */
    private String getRandom2Directions(String exclude1, String exclude2) {
        String[] directions = {"left", "right", "up", "down"};
        int rnd = new Random().nextInt(directions.length);
        while (directions[rnd].equals(exclude1) || directions[rnd].equals(exclude2)) {
            rnd = new Random().nextInt(directions.length);
        }
        return directions[rnd];
    }

    /*
    private boolean getRandomBoolean() {
        Random rand = new Random();
        return rand.nextBoolean();
    }
    */

    /**
     * Gets the {@code animation} of the ghost.
     * @return the {@code animation} of the ghost
     */
    public AnimationTimer getAnimation() {
        return animation;
    }

    /**
     *Check if current direction is able to continue the movement.
     *If in front of the direction is no obstacle, the {@code direction} will be kept.
     *Else the method will return.
     * @param direction current direction of ghost
     */
    private void checkIftheresPathToGo(String direction) {
        double rightEdge, leftEdge, topEdge, bottomEdge;
        switch (direction) {
            case "down":
                leftEdge = getX() - 10;
                bottomEdge = getY() + getHeight() + 15;
                rightEdge = getX() + getWidth() + 10;
                if (!maze.hasObstacle(leftEdge, rightEdge, bottomEdge - 1, bottomEdge)) {
                    this.direction = direction;
                }
                break;
            case "up":
                leftEdge = getX() - 10;
                rightEdge = getX() + getWidth() + 10;
                topEdge = getY() - 15;
                if (!maze.hasObstacle(leftEdge, rightEdge, topEdge - 1, topEdge)) {
                    this.direction = direction;
                }
                break;
            case "left":
                leftEdge = getX() - 15;
                bottomEdge = getY() + getHeight() + 10;
                topEdge = getY() - 10;
                if (!maze.hasObstacle(leftEdge - 1, leftEdge, topEdge, bottomEdge)) {
                    this.direction = direction;
                }
                break;
            case "right":
                bottomEdge = getY() + getHeight() + 10;
                rightEdge = getX() + getWidth() + 15;
                topEdge = getY() - 10;
                if (!maze.hasObstacle(rightEdge - 1, rightEdge, topEdge, bottomEdge)) {
                    this.direction = direction;
                }
                break;
        }
    }

    /**
     *Move the ghost towards one direction until it can not.
     *If in front of one direction is no obstacle, the ghost will move forward one unit step.
     *If there is an obstacle in front of one direction, the {@code direction} will be changed.
     * @param whereToGo current direction
     * @param whereToChangeTo next direction to change
     * @param leftEdge left edge of ghost
     * @param topEdge top edge of ghost
     * @param rightEdge right edge of ghost
     * @param bottomEdge bottom edge of ghost
     * @param padding padding of ghost
     */
    private void moveUntilYouCant(String whereToGo, String whereToChangeTo, double leftEdge, double topEdge, double rightEdge, double bottomEdge, double padding) {
        switch (whereToGo) {
            case "left":
                if (!maze.isTouching(leftEdge, bottomEdge, padding) && !maze.isGhostTouching(leftEdge, bottomEdge, padding)) {
                    setX(leftEdge - ghostStep);
                } else {
                    while (maze.isTouching(getX(), getY(), padding)) {
                        setX(getX() + 1);
                    }
                    this.direction = whereToChangeTo;
                }
                break;
            case "right":
                if (!maze.isTouching(rightEdge, topEdge, padding) && !maze.isGhostTouching(rightEdge, topEdge, padding)) {
                    setX(leftEdge + ghostStep);
                } else {
                    while (maze.isTouching(getX() + getWidth(), getY(), padding)) {
                        setX(getX() - 1);
                    }
                    this.direction = whereToChangeTo;
                }
                break;
            case "up":
                if (!maze.isTouching(leftEdge, topEdge, padding) && !maze.isGhostTouching(leftEdge, topEdge, padding)) {
                    setY(topEdge - ghostStep);
                } else {
                    while (maze.isTouching(getX(), getY(), padding)) {
                        setY(getY() + 1);
                    }
                    this.direction = whereToChangeTo;
                }
                break;
            case "down":
                if (!maze.isTouching(rightEdge, bottomEdge, padding) && !maze.isGhostTouching(rightEdge, bottomEdge, padding)) {
                    setY(topEdge + ghostStep);
                } else {
                    while (maze.isTouching(getX(), getY() + getHeight(), padding)) {
                        setY(getY() - 1);
                    }
                    this.direction = whereToChangeTo;
                }
                break;
        }

    }

    /**
     * Creates an animation of the ghost.
     * <p>The animation keeps the ghost moving.
     * The ghost will keep moving forward. If it meets an obstacle,
     * the {@code direction} will be changed to either left or right relative to the front of ghost.
     * The ghost always check whether it touches a portal.
     */
    public AnimationTimer createAnimation(String arg0) {

        return new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                gameModel.pacman.checkGhostCoalition(gameModel.ghosts);
                double leftEdge = getX();
                double topEdge = getY();
                double rightEdge = getX() + getWidth();
                double bottomEdge = getY() + getHeight();
                double padding = 11.5;
                timesWalked++;
                int walkAtLeast = 4;
                switch (direction) {
                    case "left":
                        moveUntilYouCant("left", "down", leftEdge, topEdge, rightEdge, bottomEdge, padding);
                        if (timesWalked > walkAtLeast) {
                            checkPortalCoalition(gameModel.portals);
                            checkIftheresPathToGo(getRandom2Directions("left", "right"));
                            timesWalked = 0;
                        }
                        break;
                    case "right":
                        moveUntilYouCant("right", "up", leftEdge, topEdge, rightEdge, bottomEdge, padding);
                        if (timesWalked > walkAtLeast) {
                            checkPortalCoalition(gameModel.portals);
                            checkIftheresPathToGo(getRandom2Directions("left", "right"));
                             timesWalked = 0;
                        }
                        break;
                    case "up":
                        moveUntilYouCant("up", "left", leftEdge, topEdge, rightEdge, bottomEdge, padding);
                        if (timesWalked > walkAtLeast) {
                            checkPortalCoalition(gameModel.portals);
                            checkIftheresPathToGo(getRandom2Directions("up", "down"));
                            timesWalked = 0;
                        }
                        break;
                    case "down":
                        moveUntilYouCant("down", "right", leftEdge, topEdge, rightEdge, bottomEdge, padding);
                        if (timesWalked > walkAtLeast) {
                            checkPortalCoalition(gameModel.portals);
                            checkIftheresPathToGo(getRandom2Directions("up", "down"));
                            timesWalked = 0;
                        }
                        break;
                }
            }
        };
    }
    
    /**
     * Checks if ghost is touching a portal.
     * @param portals the double portal represented as left portal and right portal
     */
    public void checkPortalCoalition(DoublePortals portals) {
    	double ghostY = this.getY();
        double ghostX = this.getX();
        if (ghostX <= portals.leftPortal.getX()) {
        	this.setX(portals.rightPortal.getX());
        }
        if (ghostX >= portals.rightPortal.getX()) {
        	this.setX(portals.leftPortal.getX());
        }
    }

    /**
     * start the animation thread
     */
    @Override
    public void run() {
        this.animation.start();
    }
}
