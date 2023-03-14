//Daniel Boianju 315113159
package MovementAndCollision;
import GeomatryPrimitives.Rectangle;
import GeomatryPrimitives.Ball;
import GeomatryPrimitives.Point;
/**
 * @author Daniel B
 */
public interface Collidable {
    /**
     * this function returns the collision shape of an object.
     * @return - Rectangle. the only collision shapes in this GUI.
     */
    Rectangle getCollisionRectangle();

    /**
     * this function is the collision function for any object that collides with a collidable.
     * @param collisionPoint - the point of collision.
     * @param currentVelocity - the velocity of the object that has collided.
     * @param hitter - the Ball that has hit the collidable.
     * @return velocity - the updated velocity of the object that has collided.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
