package pacman.model;

import java.util.List;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * This class defines a pacman as the leading actor of the game.
 * <p>Animation of the pacman can be created by {@code ThePacman.createAnimation} method, including moving up, left, down, right.
 * The pacman can interact with other game items, including ghosts, cookie, super cookie, portal and level entry.
 * During the animation of the pacman, it will check whether it touches any game item.
 * If any game item is touched, corresponding changes will happen.
 * If the pacman touches a ghost, it will lose life.
 * If the pacman touches a cookie, it will get score bonus.
 * If the cookie is a super cookie, the pacman will get speed bonus additionally.
 * If the pacman touches a portal, it will be teleported to another portal. 
 * If the pacman touches the level entry in infinite mode, it will enter next level.
 * @see GameModel
 * @author Zhangli Wang
 *
 */
public class ThePacman extends Circle implements Actor {
   
	/**
	 * the animation of pacman moving left
	 */
    public AnimationTimer leftPacmanAnimation;
    /**
     * the animation of pacman moving right
     */
    public AnimationTimer rightPacmanAnimation;
    /**
     * the animation of pacman moving up
     */
    public AnimationTimer upPacmanAnimation;
    /**
     * the animation of pacman moving down
     */
    public AnimationTimer downPacmanAnimation;
    /**
     * the game model (because of a thread problem, {@code ThePacman} can not use {@code GameModel.getInstance} method).
     */
    protected GameModel gameModel;
    /**
     * speed of the pacman
     */
    public static double pacmanStep = 5;
    /**
     * on development, a boolean that indicates whether the pacman is god
     */
    public boolean invincible = false;

    /**
     * Initialize the pacman with a position by the parameter {@code x} and {@code y}.
     * @param x - index of x
     * @param y - index of y
     * @param gameModel - the game board
     */
    public ThePacman(double x, double y, GameModel gameModel) {
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(25);
        this.gameModel = gameModel;
        Image pacmanImg = new Image("/pacman/view/pacman.png");
        this.setFill(new ImagePattern(pacmanImg));
        this.leftPacmanAnimation = createAnimation("left");
        this.rightPacmanAnimation = createAnimation("right");
        this.upPacmanAnimation = createAnimation("up");
        this.downPacmanAnimation = createAnimation("down");
    }
    
    /**
     * Creates an animation of the pacman with a given {@code direction}.
     * <p>The animation is moving towards the {@code direction}, image of the pacman will rotate accordingly.
     * The animation checks any coalition in each step.
     * @param direction - direction of the movement
     * @return the animation
     */
    public AnimationTimer createAnimation(String direction) {
        return new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
	            switch (direction) {
	                case "left":
	                    if (!gameModel.maze.isTouching(getCenterX() - getRadius(), getCenterY(), 15)) {
	                    	gameModel.pacman.setRotate(180);
	                    	setCenterX(getCenterX() - pacmanStep);
	                    	checkCookieCoalition("x", gameModel.cookieSet);
	                    	checkGhostCoalition(gameModel.ghosts);
	                        checkPortalCoalition(gameModel.portals);
	                        checkLevelEntryCoalition(gameModel.levelEntry);
	                    }
	                    break;
	                case "right":
	                    if (!gameModel.maze.isTouching(getCenterX() + getRadius(), getCenterY(), 15)) {
	                    	gameModel.pacman.setRotate(0);
	                    	setCenterX(getCenterX() + pacmanStep);
	                    	checkCookieCoalition("x", gameModel.cookieSet);
	                    	checkGhostCoalition(gameModel.ghosts);
	                        checkPortalCoalition(gameModel.portals);
	                        checkLevelEntryCoalition(gameModel.levelEntry);
	                    }
	                    break;
	                case "up":
	                    if (!gameModel.maze.isTouching(getCenterX(), getCenterY() - getRadius(), 15)) {
	                    	gameModel.pacman.setRotate(-90);
	                    	setCenterY(getCenterY() - pacmanStep);
	                    	checkCookieCoalition("y", gameModel.cookieSet);
	                    	checkGhostCoalition(gameModel.ghosts);
	                        checkPortalCoalition(gameModel.portals);
	                        checkLevelEntryCoalition(gameModel.levelEntry);
	                    }
	                    break;
	                case "down":
	                   if (!gameModel.maze.isTouching(getCenterX(), getCenterY() + getRadius(), 15)) {
	                	   gameModel.pacman.setRotate(90);
	                	   setCenterY(getCenterY() + pacmanStep);
	                	   checkCookieCoalition("y", gameModel.cookieSet);
	                       checkGhostCoalition(gameModel.ghosts);
	                       checkPortalCoalition(gameModel.portals);
	                       checkLevelEntryCoalition(gameModel.levelEntry);
	                   }
	                   break;
            	}
	            return;
            }
        };
    }
    
    /**
     * Checks if the pacman touches cookies.
     * If a visible cookie is touched, the pacman can get score bonus and {@code GameManager.scoreBoard} will be updated.
     * If the cookie is a super cookie by judging {@code cookie instanceof SuperCookie}, the pacman can get speed bonus additionally.
     * Afterwards the cookie will be hidden by {@code Cookie.hide} method.
     * In not infinite mode, if all cookies on the game board are eaten, {@code GameManager.endGame} method will be called.
     * In infinite mode, if all cookies on the game board are eaten, {@code GameManager.clearLevel} method will be called.
     * @param axis - axis of direction of the movement
     * @param cookieSet all cookies in the game board
     */
    public void checkCookieCoalition(String axis, List<Cookie> cookieSet) {
        double pacmanCenterY = this.getCenterY();
        double pacmanCenterX = this.getCenterX();
        double pacmanLeftEdge = pacmanCenterX - this.getRadius();
        double pacmanRightEdge = pacmanCenterX + this.getRadius();
        double pacmanTopEdge = pacmanCenterY - this.getRadius();
        double pacmanBottomEdge = pacmanCenterY + this.getRadius();
        for (Cookie cookie:cookieSet) {
            double cookieCenterX = cookie.getCenterX();
            double cookieCenterY = cookie.getCenterY();
            double cookieLeftEdge = cookieCenterX - cookie.getRadius();
            double cookieRightEdge = cookieCenterX + cookie.getRadius();
            double cookieTopEdge = cookieCenterY - cookie.getRadius();
            double cookieBottomEdge = cookieCenterY + cookie.getRadius();
            if (axis.equals("x")) {
                // pacman goes right
                if ((cookieCenterY >= pacmanTopEdge && cookieCenterY <= pacmanBottomEdge) && (pacmanRightEdge >= cookieLeftEdge && pacmanRightEdge <= cookieRightEdge)) {
                    if (cookie.isVisible()) {
                    	if (cookie instanceof SuperCookie) {
                    		((SuperCookie) cookie).upgradePacman();
                    	}
                        gameModel.scorePoint += cookie.getValue();
                        gameModel.cookiesEaten++;
                        if (gameModel.cookiesEaten == gameModel.cookieSet.size()) {
                        	if (gameModel.infiniteGame) {
                        		gameModel.clearLevel();
                        	} else {
                        		gameModel.endGame();
                        	}
                            return;
                        }
                    }
                    cookie.hide();

                }
                // pacman goes left
                if ((cookieCenterY >= pacmanTopEdge && cookieCenterY <= pacmanBottomEdge) && (pacmanLeftEdge >= cookieLeftEdge && pacmanLeftEdge <= cookieRightEdge)) {
                    if (cookie.isVisible()) {
                    	if (cookie instanceof SuperCookie) {
                    		((SuperCookie) cookie).upgradePacman();
                    	}
                    	gameModel.scorePoint += cookie.getValue();
                        gameModel.cookiesEaten++;
                        if (gameModel.cookiesEaten == gameModel.cookieSet.size()) {
                        	if (gameModel.infiniteGame) {
                        		gameModel.clearLevel();
                        	} else {
                        		gameModel.endGame();
                        	}
                        }
                    }
                    cookie.hide();
                }
            } else {
                // pacman goes up
                if ((cookieCenterX >= pacmanLeftEdge && cookieCenterX <= pacmanRightEdge) && (pacmanBottomEdge >= cookieTopEdge && pacmanBottomEdge <= cookieBottomEdge)) {
                    if (cookie.isVisible()) {
                    	if (cookie instanceof SuperCookie) {
                    		((SuperCookie) cookie).upgradePacman();
                    	}
                    	gameModel.scorePoint += cookie.getValue();
                    	gameModel.cookiesEaten++;
                        if (gameModel.cookiesEaten == gameModel.cookieSet.size()) {
                        	if (gameModel.infiniteGame) {
                        		gameModel.clearLevel();
                        	} else {
                        		gameModel.endGame();
                        	}
                        }
                    }
                    cookie.hide();
                }
                // pacman goes down
                if ((cookieCenterX >= pacmanLeftEdge && cookieCenterX <= pacmanRightEdge) && (pacmanTopEdge <= cookieBottomEdge && pacmanTopEdge >= cookieTopEdge)) {
                    if (cookie.isVisible()) {
                    	if (cookie instanceof SuperCookie) {
                    		((SuperCookie) cookie).upgradePacman();
                    	}
                    	gameModel.scorePoint += cookie.getValue();
                    	gameModel.cookiesEaten++;
                        if (gameModel.cookiesEaten == gameModel.cookieSet.size()) {
                        	if (gameModel.infiniteGame) {
                        		gameModel.clearLevel();
                        	} else {
                        		gameModel.endGame();
                        	}
                        }
                    }
                    cookie.hide();
                }
            }
            gameModel.scoreBoard.score.setText("Score: " + gameModel.scorePoint);
        }
    }
    
    /**
     * Checks if the pacman is touching a ghost.
     * If the pacman touches a ghost, {@code GameManager.lifeLost} method will be called.
     * @param ghosts all ghosts in the game board
     */
    public void checkGhostCoalition(Set<Ghost> ghosts) {
        double pacmanCenterY = this.getCenterY();
        double pacmanCenterX = this.getCenterX();
        double pacmanLeftEdge = pacmanCenterX - this.getRadius();
        double pacmanRightEdge = pacmanCenterX + this.getRadius();
        double pacmanTopEdge = pacmanCenterY - this.getRadius();
        double pacmanBottomEdge = pacmanCenterY + this.getRadius();
        for (Ghost ghost : ghosts) {
            double ghostLeftEdge = ghost.getX();
            double ghostRightEdge = ghost.getX() + ghost.getWidth();
            double ghostTopEdge = ghost.getY();
            double ghostBottomEdge = ghost.getY() + ghost.getHeight();
            if ((pacmanLeftEdge <= ghostRightEdge && pacmanLeftEdge >= ghostLeftEdge) || (pacmanRightEdge >= ghostLeftEdge && pacmanRightEdge <= ghostRightEdge)) {
                if ((pacmanTopEdge <= ghostBottomEdge && pacmanTopEdge >= ghostTopEdge) || (pacmanBottomEdge >= ghostTopEdge && pacmanBottomEdge <= ghostBottomEdge)) {
                    if (this.invincible) {
                    	//gameModel.removeGhost(ghost);
                    } else {
                    	gameModel.lifeLost();
                    }
                }
            }
        }
    }
    
    /**
     * Checks if pacman is touching a portal.
     * If the pacman touches a portal, its x coordinate will be set to the position of another portal.
     * @param portals the two portals on the game board.
     */
    public void checkPortalCoalition(DoublePortals portals) {
    	double pacmanCenterY = this.getCenterY();
        double pacmanCenterX = this.getCenterX();
        if (pacmanCenterX <= portals.leftPortal.getX()) {
        	this.setCenterX(portals.rightPortal.getX());
        }
        if (pacmanCenterX >= portals.rightPortal.getX()) {
        	this.setCenterX(portals.leftPortal.getX());
        }
    }
    
    /**
     * Checks if the pacman is touching a level entry.
     * If the pacman touches a level entry, {@code GameManager.enterNextLevel} method will be called.
     * @param levelEntry the level entry
     */
    public void checkLevelEntryCoalition(LevelEntry levelEntry) {
        double pacmanCenterY = this.getCenterY();
        double pacmanCenterX = this.getCenterX();
        double pacmanLeftEdge = pacmanCenterX - this.getRadius();
        double pacmanRightEdge = pacmanCenterX + this.getRadius();
        double pacmanTopEdge = pacmanCenterY - this.getRadius();
        double pacmanBottomEdge = pacmanCenterY + this.getRadius();

        double entryLeftEdge = levelEntry.getX();
        double entryRightEdge = levelEntry.getX() + levelEntry.getWidth();
        double entryTopEdge = levelEntry.getY();
        double entryBottomEdge = levelEntry.getY() + levelEntry.getHeight();
        if ((pacmanLeftEdge <= entryRightEdge && pacmanLeftEdge >= entryLeftEdge) || (pacmanRightEdge >= entryLeftEdge && pacmanRightEdge <= entryRightEdge)) {
            if ((pacmanTopEdge <= entryBottomEdge && pacmanTopEdge >= entryTopEdge) || (pacmanBottomEdge >= entryTopEdge && pacmanBottomEdge <= entryBottomEdge)) {
                if (levelEntry.isVisible()) {
                	gameModel.enterNextLevel();
                }
            	
            }
        }

    }
}
