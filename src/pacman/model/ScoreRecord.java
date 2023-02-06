package pacman.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class stores a set of {@code Score} in {@code ArrayList} and can sort this list.
 * @see pacman.controller.HighScoreController
 * @see pacman.controller.GameController
 * @author Zhangli Wang
 *
 */
public class ScoreRecord {
	/**
	 * a set of {@code Score} in {@code ArrayList}
	 */
	public static ArrayList<Score> scores = new ArrayList<>();
	
	/**
	 * Sort the array list using bubble sort in descending order.
	 */
    public void sortScores() {
    	for (int i = 0; i < scores.size(); i++) {
    		for (int j = 0; j <scores.size()-1; j++) {
    			String scoreValue1 = scores.get(j).score.getText().replaceFirst("Score: ", "");
    			String scoreValue2 = scores.get(j+1).score.getText().replaceFirst("Score: ", "");
    			if (Integer.parseInt(scoreValue1) > Integer.parseInt(scoreValue2)) {
    				swap(scores, j, j+1);
    			}
    		}
    	}
    	Collections.reverse(scores);
    }
    
    /**
     * Swap two elements in a {@code List<Score>}.
     * @param list - the list to be operated
     * @param x - index of x in the list
     * @param y - index of y in the list
     */
    public void swap(List<Score> list, int x, int y) {
    	Score temp = list.get(x);
    	list.set(x, list.get(y));
    	list.set(y, temp);
    }
}
