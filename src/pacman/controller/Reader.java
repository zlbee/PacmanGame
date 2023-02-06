package pacman.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class reads content from highscore.txt.
 * @author Zhangli Wang
 *
 */
public class Reader {
	
	/**
	 * Read content from highscore.txt.
	 * <p>Read each line of the file
	 *  and compose read lines with one string as return.
	 * @return a string contain all contents of the text file
	 */
	public String readScores() {
		String tempString = "";
		BufferedReader reader = null;
	       	try {
	       		reader = new BufferedReader(new FileReader("./src/pacman/model/highscore.txt"));
	       		String tempLine;
	       		while ((tempLine = reader.readLine()) != null) {
	       			tempString = tempString+tempLine;
	       		}
	       		reader.close();

	       	} catch (IOException e) {
	       		e.printStackTrace();
	       	}
		return tempString; 
	}
}
