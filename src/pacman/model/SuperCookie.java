package pacman.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This class defines a special {@code Cookie} that can be used to bonus the pacman.
 * @see Cookie
 * @see GameModel
 * @author Zhangli Wang
 *
 */
public class SuperCookie extends Cookie {
	/**
	 * the instance of the game model
	 */
	GameModel gameModel = GameModel.getInstance();

	/**
	 * Initialize the super cookie with a position.
	 * The super cookie displays differently with cookie.
	 * @param x index of x on the game board
	 * @param y index of y on the game board
	 */
	public SuperCookie(double x, double y) {
		super(x, y);
		this.value = 10;
		this.setFill(Color.GREENYELLOW);
	}
	
	/*
	 * on development
	public void toSuperPacman() {
		gameModel.pacman.invincible = true;
	}
	*/
	
	/**
	 * Increase speed of the pacman to 8.
	 */
	public void upgradePacman() {
		ThePacman.pacmanStep = 8;
	}
	
}

