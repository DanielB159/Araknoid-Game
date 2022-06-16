package Levels;
import GameObjects.Background;
import GameObjects.Block;
import MovementAndCollision.Velocity;
import java.util.List;

/**
 * @author Daniel B.
 */
public interface LevelInformation {
    /**
     * this function returns the number of balls in the level.
     * @return int - the number of balls.
     */
    int numberOfBalls();

    /**
     * this function returns the initial velocity of each ball.
     * @return List - of ball velocities.
     */
    List<Velocity> initialBallVelocities();
    /**
     * this function returns the speed of the Paddle.
     * @return int - the speed of the paddle.
     */
    int paddleSpeed();
    /**
     * this function returns the width of the Paddle.
     * @return - int - the speed of the paddle.
     */
    int paddleWidth();

    /**
     * this function returns the Level name - displayed at the top of the screen.
     * @return String - the level name.
     */
    String levelName();
    // Returns a sprite with the background of the level

    /**
     * this function returns the Background of the Level.
     * @return Sprite - the background of the level.
     */
    Background getBackground();

    /**
     * this function returns the blocks that make a level.
     * @return List - a list of blocks. each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     * the number of Blocks to remove from the level in order to win.
     * @return int - the number of blocks to be removed from a level.
     */
    int numberOfBlocksToRemove();
}
