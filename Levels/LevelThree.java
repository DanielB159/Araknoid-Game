package Levels;

import GameObjects.Background;
import GameObjects.Block;
import GeomatryPrimitives.Rectangle;
import MovementAndCollision.Velocity;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
/**
 * @author Daniel B.
 */
public class LevelThree implements LevelInformation {
    public static final int NUMBER_OF_BALLS = 2;
    public static final Velocity B1_VELOCITY = new Velocity(-1.5, -1.2);
    public static final Velocity B2_VELOCITY = new Velocity(2.5, -1.5);


    public static final int PADDLE_SPEED = 6;
    public static final int PADDLE_WIDTH = 60;
    public static final String LEVEL_NAME = "Green 3";
    public static final int NUMBER_OF_BLOCKS_TO_REMOVE = 40;

    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l1 = new ArrayList<Velocity>();
        l1.add(B1_VELOCITY);
        l1.add(B2_VELOCITY);
        return l1;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return LEVEL_NAME;
    }

    @Override
    public Background getBackground() {
        Rectangle rect = new Rectangle(0, 0, 800, 600);
        Background b1 = new Background(rect, Color.green);
        return b1;
    }

    @Override
    public List<Block> blocks() {
        List<Block> l1 = new ArrayList<Block>();
        Color[] colors = {Color.gray, Color.red, Color.yellow, Color.cyan, Color.pink, Color.magenta};
        int k = 12, q = 0;
        //creating all of the blocks in the level.
        for (int i = 100; i <= 200; i += 20) {
            for (int j = 0; j < k; j++) {
                Rectangle rect = new Rectangle(710 - (j * 50), i, 50, 20);
                Block block1 = new Block(rect, colors[q]);
                l1.add(block1);
            }
            k--;
            q++;
        }
        return l1;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS_TO_REMOVE;
    }
}
