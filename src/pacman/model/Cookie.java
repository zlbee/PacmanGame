package pacman.model;



import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The class defines a round cookie.
 * <p>The cookie is at certain radius of {@code "12.5"}, has certain {@code value} and color {@code Color.SADDLEBROWN}.
 * The cookie can be set either visible or not.
 * @see GameModel
 * @see ThePacman
 * @author Zhangli Wang
 *
 */
public class Cookie extends Circle {

	/**
	 * the score point of the cookie
	 */
    protected int value;
    /**
     * Initialize the cookie with {@code value} of {@code "5"}, radius of {@code 12.5}, color of {@code Color.SADDLEBROWN}
     * and a position set by the parameter {@code x} and {@code y}.
     * @param x - the index of x on the game board
     * @param y - the index of y on the game board
     */
    public Cookie(double x, double y) {
        this.value = 5;
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(12.5);
        this.setFill(Color.SADDLEBROWN);
    }

    /**
     * get the value of the cookie
     * @return value of the cookie
     */
    public int getValue() {
        return value;
    }

    /**
     * hide the cookie
     */
    public void hide() {
        this.setVisible(false);
    }

    /**
     * show the cookie
     */
    public void show() {
        this.setVisible(true);
    }

}