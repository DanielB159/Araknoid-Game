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
public class LevelOne implements LevelInformation {
    public static final int NUMBER_OF_BALLS = 1;
    public static final Velocity B1_VELOCITY = new Velocity(0.5, -2);
    public static final int PADDLE_SPEED = 5;
    public static final int PADDLE_WIDTH = 50;
    public static final String LEVEL_NAME = "Direct Hit";
    public static final int NUMBER_OF_BLOCKS_TO_REMOVE = 1;

    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> l1 = new ArrayList<Velocity>();
        l1.add(B1_VELOCITY);
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
        Background b1 = new Background(rect, Color.LIGHT_GRAY);
        return b1;
    }

    @Override
    public List<Block> blocks() {
        Rectangle rect = new Rectangle(380, 80, 30, 30);
        Block b1 = new Block(rect, Color.RED);
        List<Block> l1 = new ArrayList<Block>();
        l1.add(b1);
        return l1;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS_TO_REMOVE;
    }
}
