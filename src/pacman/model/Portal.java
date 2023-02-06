package pacman.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class defines a rectangular portal.
 * The portal is with width of {@code "1"} and any unit height.
 * @author Zhangli Wang
 *
 */
public class Portal extends Rectangle {
    /**
    * Initialize the portal with a position.
    * Width of the portal is {@code "1"}.
    * Hight of the portal is optional {@code length}.
    * @param x - index of x
    * @param y - index of y
    * @param orientation - horizontal or vertical
    * @param length - the length of the bar (1 == 100px)
    */
   public Portal(double x, double y, String orientation, double length) {
       this.setX(x);
       this.setY(y);
       if (orientation.equals("horizontal")) {
           this.setHeight(1);
           this.setWidth(length*BarObstacle.THICKNESS);
       } else {
           this.setHeight(length*BarObstacle.THICKNESS);
           this.setWidth(1);
       }
   }
}
