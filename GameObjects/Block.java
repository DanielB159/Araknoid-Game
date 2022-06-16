//Daniel Boianju 315113159
package GameObjects;
import biuoop.DrawSurface;

import java.util.List;
import java.awt.Color;
import java.util.ArrayList;

import GeomatryPrimitives.Rectangle;
import GeomatryPrimitives.Ball;
import GeomatryPrimitives.Point;
import MovementAndCollision.HitListener;
import MovementAndCollision.Collidable;
import MovementAndCollision.HitNotifier;
import MovementAndCollision.Velocity;

/**
 * @author Daniel B
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private Rectangle rect;
    private java.awt.Color color;

    /**
     * this is the constructor function for a block.
     * @param rect - the rectangle that describes the block.
     * @param c - the color of the block.
     */
    public Block(Rectangle rect, java.awt.Color c) {
        this.color = c;
        this.rect = rect;
        hitListeners = new ArrayList<HitListener>();
    }

    /**
     * this is a second constructor function for a block.
     * @param rect - the rectangle that describes the block.
     */
    public Block(Rectangle rect) {
        this.color = Color.BLACK;
        this.rect = rect;
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        Rectangle rectColl = this.getCollisionRectangle();
        if (rectColl.getLeftLine().isPointOnPhysicalLine(collisionPoint, 1)
        || rectColl.getRightLine().isPointOnPhysicalLine(collisionPoint, 1)) {
            dx = -dx;
        }
        if (rectColl.getUpperLine().isPointOnPhysicalLine(collisionPoint, 1)
        || rectColl.getLowerLine().isPointOnPhysicalLine(collisionPoint, 1)) {
            dy = -dy;
        }
        Velocity v1 = new Velocity(dx, dy);
        this.notifyHit(hitter);
        return v1;
    }
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    @Override
    public void removeHitListener(HitListener hl) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (int i = 0; i < listeners.size(); i++) {
            if (listeners.get(i).getDifferentialNumber() == hl.getDifferentialNumber()) {
                this.hitListeners.remove(i);
                return;
            }
        }
    }
    /**
     * this is the getter function for the block's color.
     * @return - java.awt.color - the block's color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        Rectangle rect1 = this.getCollisionRectangle();
        surface.fillRectangle((int) rect1.getUpperLeft().getX(), (int) rect1.getUpperLeft().getY(),
                (int) rect1.getWidth(), (int) rect1.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) rect1.getUpperLeft().getX(), (int) rect1.getUpperLeft().getY(),
                (int) rect1.getWidth(), (int) rect1.getHeight());
    }

    /**
     * this function adds this block to the game.
     * @param g - the game to add the block into.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    @Override
    public void timePassed() { }
    @Override
    public Point getImportantPoint() {
        return this.getCollisionRectangle().getUpperLeft();
    }

    /**
     * this function removes the current block from the Game.
     * @param game - the game to remove the block from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * this function notifies all of the listeners from
     * the listener list that there was a hit event.
     * @param hitter - the ball hitter.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
