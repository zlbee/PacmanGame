package pacman.model;

import javafx.animation.AnimationTimer;
/**
 * The interface defines actors in the game board, who is able to move or teleport between portals.
 * @author Zhangli Wang
 *
 */
interface Actor {
	/**
	 * Create an animation of the actor with the direction
	 * @param direction the direction of the actor
	 * @return an animation of the actor
	 */
	public AnimationTimer createAnimation(String direction);
	
	/**
	 * Check whether the actor touches a portal.
	 * @param portals a {@code DoublePortals} instance, including a left portal and a right portal.
	 */
	public void checkPortalCoalition(DoublePortals portals);
}
