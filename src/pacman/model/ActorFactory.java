package pacman.model;

/**
 * This class generates {@link Actor} instances.
 * @see GameModel
 * @see Actor
 * @see ThePacman
 * @see Ghost
 * @author Zhangli Wang
 *
 */
public class ActorFactory {
   //use getActor method to get object of type Actor 
	/**
	 * Generate {@link Actor} instances.
	 * <p>According to {@code actorType}, generate corresponding type of actor
	 *  by calling corresponding constructor.
	 * @param actorType the type of actor is to be generated
	 * @param x the index of x in the game board
	 * @param y the index of y in the game board
	 * @param gameModel the model of the game for the pacman
	 * @return the corresponding instance of actor
	 */
   public Actor getActor(String actorType, double x, double y, GameModel gameModel) {
	      if(actorType == null)	{
	         return null;
	      }		
	      if(actorType.equalsIgnoreCase("PACMAN")) {
	         return new ThePacman(x, y, gameModel);
	         
	      } else if(actorType.equalsIgnoreCase("GHOST")) {
	         return new Ghost(x, y);
	   }
		return null;
   }
}
