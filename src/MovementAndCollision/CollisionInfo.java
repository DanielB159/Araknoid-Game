//Daniel Boianju 315113159
package MovementAndCollision;
import GeomatryPrimitives.Point;
/**
 * @author Daniel B.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * the constructor function for a CollisionInfo object.
     * @param p1 - the Point of collision.
     * @param c1 - the Collidable.
     */
    public CollisionInfo(Point p1, Collidable c1) {
        this.collisionPoint = p1;
        this.collisionObject = c1;
    }
    /**
     * this function returns the point at which the collision with the object occurs.
     * @return - Point - the point of collision.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * this function returns the object that is colliding.
     * @return - Collidable - the object that is colliding.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
