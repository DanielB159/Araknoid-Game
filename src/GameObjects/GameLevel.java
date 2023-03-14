//Daniel Boianju 315113159
package GameObjects;
import Levels.LevelInformation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import biuoop.DrawSurface;
import GeomatryPrimitives.Rectangle;
import GeomatryPrimitives.Ball;

import MovementAndCollision.Collidable;

import java.awt.Color;

/**
 * @author Daniel B
 */
public class GameLevel implements Animation {
    private LevelInformation levelInformation;
    private AnimationRunner runner;
    private boolean running;
    public static final double EPSILON = 0.00000000001;
    private Sleeper sleeper;
    private GUI gui;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle pad;
    private Counter blocksCounter;
    private Counter spritesCounter;
    private Counter ballsCounter;
    private Counter scoreCounter;
    private Counter livesCounter;
    private KeyboardSensor keyboard;
    public static final int GUI_WIDTH = 800;
    public static final int GUI_HEIGHT = 600;
    public static final int BALLS_RADIUS = 3;

    /**
     * the constructor function for a Game.
     * @param lInf - the level information.
     * @param gui - the game gui.
     */
    public GameLevel(LevelInformation lInf, GUI gui) {
        this.levelInformation = lInf;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter();
        this.spritesCounter = new Counter();
        this.ballsCounter = new Counter();
        ballsCounter.increase(lInf.numberOfBalls());
        this.scoreCounter = new Counter();
        this.gui = gui;
    }
    /**
     * the constructor function for a Game.
     * @param lInf - the level information.
     * @param scorecounter - a counter of the score.
     * @param gui - the gui of the game.
     * @param livesCounter - the lives counter.
     */
    public GameLevel(LevelInformation lInf, Counter scorecounter, GUI gui, Counter livesCounter) {
        this.levelInformation = lInf;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter();
        this.spritesCounter = new Counter();
        this.ballsCounter = new Counter();
        ballsCounter.increase(lInf.numberOfBalls());
        this.scoreCounter = scorecounter;
        this.gui = gui;
        this.livesCounter = livesCounter;
    }

    /**
     * this is the set function for the game's pad.
     * @param pad
     */
    public void setPaddle(Paddle pad) {
        this.pad = pad;
    }

    /**
     * this is the set function for the game's GameEnvironment.
     * @param environment - the game environment.
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
        Counter c1 = new Counter();
        c1.increase(environment.getCollideList().size());
        this.blocksCounter = c1;
    }

    /**
     * this function increases the blocks number by num.
     * @param num - the number to increase.
     */
    public void increaseBlocksNumber(int num) {
        this.blocksCounter.increase(num);
    }

    /**
     * this is the get function for the game's keyboard.
     * @return KeyboardSensor - this game's keyboard.
     */
    public KeyboardSensor getKeyboard() {
        return keyboard;
    }

    /**
     * this function decreases the ball counter by num.
     * @param num - the amount to decrease.
     */
    public void decreaseBallCount(int num) {
        this.ballsCounter.decrease(num);
    }

    /**
     * this is the set function for the running variable.
     * @param running - the boolean value to put in running.
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * this is the get function for the ball counter.
     * @return Counter - the ball counter.
     */
    public Counter getBallsCounter() {
        return this.ballsCounter;
    }
    /**
     * this is the getter function for the blocks counter.
     * @return Counter - the blocks counter.
     */
    public Counter getBlocksCounter() {
        return this.blocksCounter;
    }
    /**
     * this function decreases the blocks number by num.
     * @param num - the number to decrease.
     */
    public void decreaseBlocksNumber(int num) {
        this.blocksCounter.decrease(num);
    }
    /**
     * this is the set function for the game's Sprite collection.
     * @param s - the sprite collection.
     */
    public void setSprites(SpriteCollection s) {
        this.sprites = s;
        this.spritesCounter.increase(s.getSpriteList().size());
    }

    /**
     * this is the get function for the running variable.
     * @return - boolean - running value.
     */
    public boolean isRunning() {
        return running;
    }
    /**
     * this function adds a collidable to the collidable list.
     * @param c - the collidable to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
        this.blocksCounter.increase(1);
    }

    /**
     * this function adds a sprite to the sprite list.
     * @param s - the sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
        this.spritesCounter.increase(1);
    }

    /**
     * this function generates an array of X values based on the number of balls.
     * @param numOfBalls - the numbert of balls to generate X values to.
     * @return int[] - an array of X values.
     */
    public int[] generateBallXValues(int numOfBalls) {
        int[] values = new int[numOfBalls];
        for (int i = 0; i < numOfBalls; i++) {
            values[i] = 300 + (200 / numOfBalls) * i;
        }
        return values;
    }

    /**
     * this function initializes the game.
     */
    public void initialize() {
        if (this.getBallsCounter().getValue() == 0) {
            this.ballsCounter.increase(this.levelInformation.numberOfBalls());
        }
        this.sleeper = new Sleeper();
        this.runner = new AnimationRunner(this.gui, 60, this.sleeper);
        this.running = true;
        Background background = levelInformation.getBackground();
        Rectangle border1 = new Rectangle(0, 0, GameLevel.GUI_WIDTH, 40),
                border2 = new Rectangle(760, 40, 40, 560),
                border3 = new Rectangle(0, 40, 40, 560),
                border4 = new Rectangle(-50, 600, 900, 40);
        Block borderB1 = new Block(border1, Color.gray);
        Block borderB2 = new Block(border2, Color.gray);
        Block borderB3 = new Block(border3, Color.gray);
        Block deathZone = new Block(border4, Color.gray);
        BlockRemover l1 = new BlockRemover(this, this.blocksCounter);
        BallRemover l2 = new BallRemover(this, ballsCounter);
        ScoreTrackingListener l3 = new ScoreTrackingListener(this.scoreCounter);
        ScoreIndicator indicator = new ScoreIndicator(l3, levelInformation.levelName(), this.livesCounter.getValue());
        background.addToGame(this);
        borderB1.addToGame(this);
        borderB2.addToGame(this);
        borderB3.addToGame(this);
        deathZone.addToGame(this);
        deathZone.addHitListener(l2);
        this.keyboard = this.gui.getKeyboardSensor();
        this.setPaddle(new Paddle(this.gui, this.keyboard,
                levelInformation.paddleWidth(), 5));
        pad.addToGame(this);
        for (int i = 0; i < levelInformation.blocks().size(); i++) {
            Block b1 = levelInformation.blocks().get(i);
            b1.addToGame(this);
            b1.addHitListener(l3);
            b1.addHitListener(l1);
        }
        int[] values = generateBallXValues(levelInformation.numberOfBalls());
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball b1 = new Ball(values[i], 520, BALLS_RADIUS, Color.white);
            b1.setVelocity(levelInformation.initialBallVelocities().get(i));
            b1.setBounds(0, 0, GameLevel.GUI_HEIGHT, GameLevel.GUI_WIDTH);
            b1.setGameEnvironment(this.environment);
            b1.addToGame(this);
        }
        indicator.addToGame(this);
    }

    /**
     * the get function for the gui.
     * @return GUI - the game's gui.
     */
    public GUI getGui() {
        return this.gui;
    }
    /**
     * this function runs the game in its current state.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(3, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * this function removes a collidable from the collidable list.
     * @param c - the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.deleteCollidable(c);
        this.blocksCounter.decrease(1);
    }

    /**
     * this function removes a sprite from the sprite list.
     * @param s - the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.deleteSprite(s);
        this.spritesCounter.decrease(1);
    }

    /**
     * this is the get function for the game's animation runner.
     * @return Animation runner - the game;s animation runner.
     */
    public AnimationRunner getRunner() {
        return this.runner;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(
                    this.getKeyboard(), KeyboardSensor.SPACE_KEY,
                    new PauseScreen()));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.blocksCounter.getValue() == 5) {
            this.scoreCounter.increase(100);
            this.running = false;
        } else if (this.ballsCounter.getValue() == 0) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}