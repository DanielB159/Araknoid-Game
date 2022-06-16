package GameObjects;

import GeomatryPrimitives.Point;
import GeomatryPrimitives.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Daniel B;
 * this class is the background sprite for all of the levels.
 */
public class Background implements Sprite {
    private Rectangle rect;
    private Color c;

    /**
     * this is the constructor function for a background.
     * @param rect - the rectangle defining the borders of the background.
     * @param c1 - the color of the background.
     */
    public Background(Rectangle rect, Color c1) {
        this.rect = rect;
        this.c = c1;
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.c);
        d.fillRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(), (int) rect.getWidth(), (int) rect.getHeight());
    }

    @Override
    public void timePassed() { }

    @Override
    public Point getImportantPoint() {
        return this.rect.getUpperLeft();
    }

    /**
     * this function adds this background to the game.
     * @param g - the game to add the block into.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
