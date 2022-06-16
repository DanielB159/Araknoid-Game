package GameObjects;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * @author Daniel B.
 * this is the class of the countdown animation.
 */
public class CountdownAnimation implements Animation {
    public static final double EPSILON = 0.0001;
    private double secondsPassed;
    private int numberToStartCountdown;
    private SpriteCollection sprites;
    private Sleeper sleeper;

    /**
     * this is the constructor for the CountDownAnimation class.
     * @param numOfSeconds - the number of seconds that have passed since the start of the animation.
     * @param countFrom - the number to start counting from.
     * @param gameScreen - the sprites to draw in the game screen.
     */
    public CountdownAnimation(float numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.secondsPassed = numOfSeconds;
        this.numberToStartCountdown = countFrom;
        this.sprites = gameScreen;
        this.sleeper = new Sleeper();
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        //first, we draw all of the sprites in the gui.
        this.sprites.drawAllOn(d);
        //this is function checks weather the countdown has ended, and displays "GO!!!"
        if (this.numberToStartCountdown == 0) {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, "GO!!!", 32);
        } else if (numberToStartCountdown != -1) {
            //this function displays the coundown numbers.
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(this.numberToStartCountdown), 32);
        }
        //we actuvate sleeper for 666 milliseconds in order to overall be 2 seconds.
        if (this.numberToStartCountdown != 3) {
            sleeper.sleepFor(666);
        }
        numberToStartCountdown--;
    }
    @Override
    public boolean shouldStop() {
        return numberToStartCountdown == -2;
    }
}