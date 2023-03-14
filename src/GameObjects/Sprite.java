//Daniel Boianju 315113159
package GameObjects;
import biuoop.DrawSurface;
import GeomatryPrimitives.Point;
/**
 * @author Daniel B.
 */
public interface Sprite {
    /**
     * this function draws the sprite in the GUI.
     * @param d - the draw-surface.
     */
    void drawOn(DrawSurface d);

    /**
     * this function notifies the Sprite that time has passed.
     */
    void timePassed();

    /**
     * this function returns the important point of a sprite
     * so it can be recognizable from other Sprites.
     * @return Point - the important point.
     */
    Point getImportantPoint();
}