//Daniel Boianju 315113159
package GameObjects;
import biuoop.DrawSurface;
import GeomatryPrimitives.Rectangle;
import GeomatryPrimitives.Point;

import java.awt.Color;

/**
 * @author Daniel B.
 */
public class ScoreIndicator implements Sprite {
    public static final String SPACE_BETWEEN_TEXT = "                               ";
    private ScoreTrackingListener score;
    private Block block;
    private String levelname;
    private int  numberOfLives;
    /**
     * this is the constructor function for a ScoreIndicator.
     * @param s1 - the ScoreTrackingListener.
     * @param levelname - the level name;
     * @param numberOfLives - the current number of lives.
     */
    public ScoreIndicator(ScoreTrackingListener s1, String levelname, int numberOfLives) {
        this.score = s1;
        Rectangle rect = new Rectangle(0, 0, 800, 20);
        this.block = new Block(rect, Color.WHITE);
        this.levelname = levelname;
        this.numberOfLives = numberOfLives;
    }

    /**
     * this function adds the ScoreIndicator to the game.
     * @param g - the Game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    @Override
    public Point getImportantPoint() {
        return this.block.getImportantPoint();
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);
        String s = "Lives: " + this.numberOfLives + SPACE_BETWEEN_TEXT + "Score: "
                +  Integer.toString(this.score.getScore())
                + SPACE_BETWEEN_TEXT + "Level Name: " + this.levelname;
        d.drawText(200, 15, s, 12);
    }

    @Override
    public void timePassed() { }
}
