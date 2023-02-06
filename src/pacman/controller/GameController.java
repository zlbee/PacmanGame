package pacman.controller;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pacman.model.BarObstacle;
import pacman.model.GameModel;
import pacman.model.Ghost;
import pacman.model.Score;
import pacman.model.ScoreRecord;

/**
 * Class {@code GameController} manipulates game objects and update changes to the view.
 * 
 * <p>This class manipulates with keyboard input and update the view accordingly.
 * This class is the observer of {@code GameManager} class. 
 * When {@code GameManager} notify this class, corresponding changes to the view will be executed.
 * @author Zhangli Wang
 *
 */
public class GameController implements Observer {
	private GameModel gameModel = GameModel.getInstance();
	private Group root;
	
	public GameController(Group root) {
		this.root = root;
        gameModel.addObserver(this);
	}

    /**
     * Draw the board of the game with the maze and game items to the view.
     * <p>Items are: {@code obstacles}, {@code ghostObstacles}, {@code cookieSet}, {@code pacman}, {@code leftPortal}, {@code rightPortal}, {@code scoreBoard}
     * of the gameModel.
     */
    public void drawBoard() {
    	gameModel.initGame();
    	
        root.getChildren().addAll(gameModel.maze.obstacles);
        
        root.getChildren().addAll(gameModel.maze.ghostObstacles);
        
        root.getChildren().addAll(gameModel.cookieSet);
        
        root.getChildren().add(gameModel.pacman);
        
        root.getChildren().addAll(gameModel.ghosts);
              
        root.getChildren().add(gameModel.portals.leftPortal);
        root.getChildren().add(gameModel.portals.rightPortal);
        
        root.getChildren().add(gameModel.scoreBoard.score);
        root.getChildren().add(gameModel.scoreBoard.lifes);
        if (gameModel.infiniteGame) {
        	root.getChildren().add(gameModel.scoreBoard.level);
        }
    }
    
	/**
	 * End game display.
	 * <p>All actors are cleared from the view.
	 * Instead of the score board, a game over prompt is displayed at the bottom area.
	 */
    public void endGameDisplay() {
    	if (gameModel.gameEnded == true) {
        	root.getChildren().remove(gameModel.pacman);
        	for (Ghost ghost : gameModel.ghosts) {
                root.getChildren().remove(ghost);
            }
        	
            root.getChildren().remove(gameModel.scoreBoard.score);
            root.getChildren().remove(gameModel.scoreBoard.lifes);
            
            javafx.scene.text.Text endGameText = new javafx.scene.text.Text("Game Over, press ESC to restart");
            endGameText.setX(BarObstacle.THICKNESS * 3);
            endGameText.setY(BarObstacle.THICKNESS * 28);
            endGameText.setFont(Font.font("Arial", 40));
            endGameText.setFill(Color.ROYALBLUE);
            root.getChildren().add(endGameText);
    	}
    }
    
    /**
     * Restart game display.
     * <p>All children of the view are cleared.
     * Properties in the game model are reset.
     * The game board is drawn again.
     * @param event ESCAPE is pressed
     */
    public void restartGameDisplay(KeyEvent event) {
    	if (event.getCode() == KeyCode.ESCAPE && gameModel.gameEnded) {
        	root.getChildren().clear();
        	gameModel.restartGame();
        	drawBoard();
    	}
    }
    
    /**
     * Moves the pacman.
     * <p>According to keyboard input, animation of the pacman is run.
     * The pacman can move up, left, down and right.
     * @param event direction keys are pressed
     */
    public void movePacman(KeyEvent event) {
        for (Ghost ghost : gameModel.ghosts) {
            ghost.run();
        }
        switch(event.getCode()) {
            case RIGHT:
            	gameModel.pacman.rightPacmanAnimation.start();
                break;
            case LEFT:
            	gameModel.pacman.leftPacmanAnimation.start();
                break;
            case UP:
            	gameModel.pacman.upPacmanAnimation.start();
                break;
            case DOWN:
            	gameModel.pacman.downPacmanAnimation.start();
                break;
        }
    }

    /**
     * Stops the pacman.
     * <p>According to keyboard event, animation of the pacman is stopped.
     * @param event direction keys are released
     */
    public void stopPacman(KeyEvent event) {
        switch(event.getCode()) {
            case RIGHT:
            	gameModel.pacman.rightPacmanAnimation.stop();
                break;
            case LEFT:
            	gameModel.pacman.leftPacmanAnimation.stop();
                break;
            case UP:
            	gameModel.pacman.upPacmanAnimation.stop();
                break;
            case DOWN:
            	gameModel.pacman.downPacmanAnimation.stop();
                break;
        }
    }

    /**
     * Update the view.
     * <p> According to parameters passed by the observed game model, the view is updated.
     * <p>If {@code "1"} is passed, the pacman's life is lost. Animation of the pacman will be stopped
     *  and the score board will be updated.
     * <p>If {@code "2"} is passed, the game is ended. {@code endGameDisplay} is called, scores are recorded
     * and high score window is displayed.
     * <p>If {@code "3"} is passed, in infinite mode, current level is cleared. The next level entry is
     * added to the root children and displayed.
     * <p>If {@code "4"} is passed, in infinite mode, the pacman has entered the next level. Game display is refreshed.
     * @param o the observed object
     * @param arg the passed parameter for the observer
     */
	@Override
	public void update(Observable o, Object arg) {
		switch(arg.toString()) {
		// when life is lost
		case "1":
	        gameModel.pacman.leftPacmanAnimation.stop();
	        gameModel.pacman.rightPacmanAnimation.stop();
	        gameModel.pacman.upPacmanAnimation.stop();
	        gameModel.pacman.downPacmanAnimation.stop();
	        gameModel.scoreBoard.lifes.setText("Lifes: " + gameModel.lifes);
	        gameModel.scoreBoard.score.setText("Score: " + gameModel.scorePoint);
	        break;
	    // when game is ended
		case "2":
            endGameDisplay();
            
            Score writtenScore = gameModel.scoreBoard;
            ScoreRecord.scores.add(writtenScore);
            ScoreRecord scoreRecord = new ScoreRecord();
            scoreRecord.sortScores();
            
            Writer writer = new Writer();
            try {
				writer.writeScores();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            
            HighScore highScore = new HighScore();
            try {
				highScore.showWindow();
			} catch (Exception e) {
				e.printStackTrace();
			}
            break;
        // when level is clear
		case "3":
			if (gameModel.infiniteGame) {
				root.getChildren().add(gameModel.levelEntry);
			}
			break;
		// when pacman enter next level	
		case "4":
        	root.getChildren().clear();
        	drawBoard();
			break;
			/*
		// when ghost is removed
		case "5":
			for (Ghost ghost : gameModel.ghosts) {
                root.getChildren().remove(ghost);
            }
			break;
			*/
		}
		
	}
}
