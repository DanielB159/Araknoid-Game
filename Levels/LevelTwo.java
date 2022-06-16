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
public class LevelTwo implements LevelInformation {
    public static final int NUMBER_OF_BALLS = 10;
    public static final Velocity B1_VELOCITY = new Velocity(-1.5, -1.2);
    public static final Velocity B2_VELOCITY = new Velocity(-2, -1.3);
    public static final Velocity B3_VELOCITY = new Velocity(-1.5, -2);
    public static final Velocity B4_VELOCITY = new Velocity(-4, -1.5);
    public static final Velocity B5_VELOCITY = new Velocity(-1.5, -2);
    public static final Velocity B6_VELOCITY = new Velocity(1.5, -1.2);
    public static final Velocity B7_VELOCITY = new Velocity(1.5, -1.1);
    public static final Velocity B8_VELOCITY = new Velocity(3, -1);
    public static final Velocity B9_VELOCITY = new Velocity(1.5, -1.1);
    public static final Velocity B10_VELOCITY = new Velocity(5, -1.2);
    public static final int PADDLE_SPEED = 1;
    public static final int PADDLE_WIDTH = 200;
    public static final String LEVEL_NAME = "Wide Easy";
    public static final int NUMBER_OF_BLOCKS_TO_REMOVE = 15;

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
        l1.add(B5_VELOCITY);
        l1.add(B6_VELOCITY);
        l1.add(B7_VELOCITY);
        l1.add(B8_VELOCITY);
        l1.add(B9_VELOCITY);
        l1.add(B10_VELOCITY);
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
        Background b1 = new Background(rect, Color.blue);
        return b1;
    }

    @Override
    public List<Block> blocks() {
        List<Block> l1 = new ArrayList<Block>();
        Color[] colors = {Color.magenta, Color.red, Color.yellow, Color.cyan, Color.pink, Color.green, Color.white};
        int topRightX = 40;
        //creating the blocks for the level.
        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 3:
                    for (int j = 0; j < 3; j++) {
                        Rectangle rect = new Rectangle(topRightX, 250, 48, 20);
                        Block b1 = new Block(rect, colors[i]);
                        l1.add(b1);
                        topRightX += 48;
                        break;
                    }
                default:
                    for (int j = 0; j < 2; j++) {
                        Rectangle rect = new Rectangle(topRightX, 250, 48, 20);
                        Block b1 = new Block(rect, colors[i]);
                        l1.add(b1);
                        topRightX += 48;
                    }

            }

        }
        return l1;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS_TO_REMOVE;
    }
}
