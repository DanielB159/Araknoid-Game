package Levels;
import GameObjects.Counter;
import GameObjects.GameLevel;
import GameObjects.KeyPressStoppableAnimation;
import GameObjects.GameOverScreen;
import GameObjects.YouWinScreen;

import java.util.List;

import biuoop.GUI;
import biuoop.KeyboardSensor;


/**
 * @author Daniel B.
 */
public class GameFlow {
    /**
     * this function runs all of the levels in the levels list.
     * @param levels - a list of Levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        //first, we set a new GUI, and initialise its counters.
        GUI gui = new GUI("Araknoid", 800, 600);
        Counter scoreCounter = new Counter();
        Counter livesCounter = new Counter();
        //we set the number of lives throughout the game to be 7.
        livesCounter.increase(7);
        //this for loop runs all of the levels in the levels list.
        for (int i = 0; i < levels.size(); i++) {
            //we make a new GameLevel.
            GameLevel level = new GameLevel(levels.get(i), scoreCounter, gui, livesCounter);
            //this while loop runs until the level has been beat, or until the player has lost.
            while ((level.getBlocksCounter().getValue() != 5 && level.getBallsCounter().getValue() != 0
            && livesCounter.getValue() > 0)) {
                //we need to save the beggining score in order to reset it of the player has lost a life.
                int j = livesCounter.getValue(), begginingScore = scoreCounter.getValue();
                //k will indicate if the player is currently on his first try of a level or not.
                int k = 0;
                for ( ; j > 0; j--, livesCounter.decrease(1)) {
                    //if the player is not on his first try, re-initialize the level completely.
                    if (k != 0) {
                        level = new GameLevel(levels.get(i), scoreCounter, gui, livesCounter);
                    }
                    //initialise the score counter to be what it was in the beggining of this level.
                    scoreCounter.decrease(scoreCounter.getValue());
                    scoreCounter.increase(begginingScore);
                    level.initialize();
                    level.setRunning(true);
                    level.run();
                    //this if condition checks weather the only blocks that are left are the boundaries
                    //and the paddle. Meaning that the player has beat the level.
                    if (level.getBlocksCounter().getValue() == 5 && j != 0) {
                        break;
                    }
                    k++;
                }
                //this if condition checks weather the player has lost all of the lives.
                if (j == 0) {
                    level.getRunner().run(new KeyPressStoppableAnimation(
                            level.getKeyboard(), KeyboardSensor.SPACE_KEY,
                                    new GameOverScreen(scoreCounter.getValue())));
                    level.getGui().close();
                }
            }
            //this if condition checks weather the player has lost all of the balls.
            if (level.getBallsCounter().getValue() == 0) {
                level.getRunner().run(new KeyPressStoppableAnimation(
                        level.getKeyboard(), KeyboardSensor.SPACE_KEY,
                        new GameOverScreen(scoreCounter.getValue())));
                level.getGui().close();
            } else if (i == levels.size() - 1) {
                //if reached here, the player has not lost all of the balls and has beat all of the blocks.
                // Meaning, the player has won!
                level.getRunner().run(new KeyPressStoppableAnimation(
                        level.getKeyboard(), KeyboardSensor.SPACE_KEY,
                        new YouWinScreen(scoreCounter.getValue())));
                level.getGui().close();
            }
        }
    }
}
