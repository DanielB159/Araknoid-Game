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
public class LevelFour implements LevelInformation {
    public static final int NUMBER_OF_BALLS = 4;
    public static final Velocity B1_VELOCITY = new Velocity(-1.5, -1.7);
    public static final Velocity B2_VELOCITY = new Velocity(2, -1);
    public static final Velocity B3_VELOCITY = new Velocity(-1, -2);
    public static final Velocity B4_VELOCITY = new Velocity(-1, -1.7);

    public static final int PADDLE_SPEED = 5;
    public static final int PADDLE_WIDTH = 100;
    public static final String LEVEL_NAME = "Final Four";
    public static final int NUMBER_OF_BLOCKS_TO_REMOVE = 105;

    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l1 = new ArrayList<Velocity>();
        l1.add(B1_VELOCITY);
        l1.add(B2_VELOCITY);
        l1.add(B3_VELOCITY);
        l1.add(B4_VELOCITY);
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
        Background b1 = new Background(rect, Color.magenta);
        return b1;
    }

    @Override
    public List<Block> blocks() {
        List<Block> l1 = new ArrayList<Block>();
        Color[] colors = {Color.gray, Color.red, Color.yellow, Color.green, Color.white, Color.pink, Color.cyan};
        //building the blocks for the level.
        for (int i = 0; i < 7; i++) {
            for (int j = 40; j < 760; j += 48) {
                Rectangle rect = new Rectangle(j, 150 + i * 20, 48, 20);
                Block b1 = new Block(rect, colors[i]);
                l1.add(b1);
            }
        }
        return l1;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS_TO_REMOVE;
    }

}
