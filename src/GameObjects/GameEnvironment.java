//Daniel Boianju 315113159
package GameObjects;
import java.util.ArrayList;
import java.util.List;
import GeomatryPrimitives.Rectangle;
import GeomatryPrimitives.Point;
import GeomatryPrimitives.Line;

import MovementAndCollision.Collidable;
import MovementAndCollision.CollisionInfo;

/**
 * @author Daniel B.
 */
public class GameEnvironment {
    private List<Collidable> collideList;
    private List<Rectangle> collideListRects;
    public static final int PADDLE_HEIGHT = 540;
    /**
     * the constructor function for the game enviroment.
     */
    public GameEnvironment() {
        collideList = new ArrayList<>();
        collideListRects = new ArrayList<>();
    }

    /**
     * this function adds the given Collidable to the Collidable list.
     * @param c - the Collidable.
     */
    public void addCollidable(Collidable c) {
        this.collideList.add(c);
        this.collideListRects.add(c.getCollisionRectangle());
    }

    /**
     * this function deletes a collidable from the collidable and rectangle lists.
     * @param c - the collidable to delete.
     */
    public void deleteCollidable(Collidable c) {
        List<Collidable> lTemp = this.collideList;
        for (int i = 0; i < lTemp.size(); i++) {
            if (c.getCollisionRectangle().getUpperLeft().equals(
                    this.collideList.get(i).getCollisionRectangle().getUpperLeft())) {
                        this.collideList.remove(i);
                        this.collideListRects.remove(i);
            }
        }
    }

    /**
     * this function returns the rectangle of the paddle in the collidable list.
     * @return - rectangle - the rectangle of the paddle location.
     */
    public Rectangle getPaddleRect() {
        for (int i = 0; i < this.collideListRects.size(); i++) {
            if (this.collideListRects.get(i).getUpperLeft().getY() == GameEnvironment.PADDLE_HEIGHT) {
                return this.collideListRects.get(i);
            }
        }
        return null;
    }
    /**
     * this function returns the closest collision in this current trajectory.
     * @param trajectory - the current trajectory.
     * @return - CollisionInfo - the information on the nearest Collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> nearestIntersections = new ArrayList<Point>();
        List<Collidable> relevantCollidables = new ArrayList<Collidable>();
        for (int i = 0; i < this.collideListRects.size(); i++) {
            Rectangle rect1 = this.collideListRects.get(i);
            Point currentIntersection = trajectory.closestIntersectionToStartOfLine(rect1);
            if (currentIntersection != null) {
                nearestIntersections.add(currentIntersection);
                relevantCollidables.add(this.collideList.get(i));
            }
        }
        //if the trajectory is not colliding with a Collidable.
        if (nearestIntersections.size() == 0) {
            return null;
        }
        double minimalDistance = trajectory.start().distance(nearestIntersections.get(0));
        Point closestPoint = nearestIntersections.get(0);
        Collidable nearestCollidable = relevantCollidables.get(0);
        /*
        loop over the list of nearest intersections, and find the neareset intersection.
        Saving the corresponding Collidable.
         */
        for (int i = 1; i < nearestIntersections.size(); i++) {
            double currentDistance = trajectory.start().distance(nearestIntersections.get(i));
            if (currentDistance < minimalDistance) {
                minimalDistance = currentDistance;
                closestPoint = nearestIntersections.get(i);
                nearestCollidable = relevantCollidables.get(i);
            }
        }
        CollisionInfo cInf = new CollisionInfo(closestPoint, nearestCollidable);
        return cInf;
    }

    /**
     * this is the getter function for the collidable list.
     * @return - List - the list of Collidables.
     */
    public List<Collidable> getCollideList() {
        return this.collideList;
    }
}
