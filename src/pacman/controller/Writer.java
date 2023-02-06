package pacman.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import pacman.model.Score;
import pacman.model.ScoreRecord;

/**
 * This class write scores stored in {@code ScoreRecord} into highscore.txt file.
 * The results are whipped after the program is ended, but are remained during several plays.
 * @author Zhangli Wang
 *
 */
public class Writer {

	public Writer() {
		
	}
	/**
	 * Write scores into highscore.txt file.
	 * <p>Iteratively write each score of the {@code scores} in {@code ScoreRecord}.
	 *For each {@code Score} or {@code Lifes} to be written, convert {@code Text} type to {@link String} type.
	 * @throws IOException unable to write streams
	 */
	public void writeScores() throws IOException {
        File file = new File("./src/pacman/model/highscore.txt");

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        
		for (int i = 0; i < ScoreRecord.scores.size(); i++) {
			String tempString = ScoreRecord.scores.get(i).score.getText().toString()+ScoreRecord.scores.get(i).lifes.getText().toString()+"\n";
		        
	        writer.write(tempString);
		}
        writer.flush();
        
        writer.close();	
	}
}
