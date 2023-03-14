//Daniel Boianju 315113159
package GeomatryPrimitives;
/**
 * author: Daniel Boianju.
 */
public class Point {
    public static final double EPSILON = 0.00000000001;
    private double x;
    private double y;
    public static final int PADDLE_DEFLECT_SPEED = 6;
    /**
     * this is the constructor function. constructs a new point.
     * @param x - the x value of a point in a two-dimensional plane.
     * @param y - the x value of a point in a two-dimensional plane.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * this function determines of two double values are close enough together to be considered the same.
     * @param p1 - the point to check if is in epsilon range
     * @return boolean - true if are considered the same, false if not.
     */
    public boolean inRangeOfEPSILON(Point p1) {
        double x = p1.getX(), y = p1.getY();
        if (Math.abs(this.getX() - x) <= Point.EPSILON
                && Math.abs(this.getY() - y) <= Point.EPSILON) {
            return true;
        }
        return false;
    }
    /**
     * this function returns the distance between the current point to another point.
     * @param other - the other point of which we want to check the distance to.
     * @return double - the distance to the other point.
     */
    public double distance(Point other) {
        double distance = Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
        return distance;
    }

    /**
     * this function checks weather the current point and the other point are equal.
     * @param other - the other point which we want to check weather it is equal.
     * @return boolean - true if is equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (this.getX() == other.getX() && this.getY() == other.getY()) {
            return true;
        }
        if (this.inRangeOfEPSILON(other)) {
            return true;
        }
        return false;
    }

    /**
     * this function is the Getter function to a point's x value.
     * @return double - the x value.
     */
    public double getX() {
        return this.x;
    }
    /**
     * this function is the Getter function to a point's y value.
     * @return double - the x value.
     */
    public double getY() {
        return this.y;
    }
    /**
     * this function sets a point's X value to the given x value.
     * @param x - the new X value.
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * this function sets a point's Y value to the given y value.
     * @param y - the new Y value.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * this function gets rectangle and checks weather the point is inside the rectangle (not including the edges).
     * @param rect - the rectangle to check.
     * @return boolean =- true if inside the rectangle, false otherwise.
     */
    public boolean checkIfInsideRectangle(Rectangle rect) {
            double x = this.getX(), y = this.getY();
            if (x > rect.getLeftLine().start().getX() && x < rect.getRightLine().start().getX()
                    && y > rect.getUpperLeft().getY() && y < rect.getLowerLine().start().getY()) {
                return true;
            }
            return false;
    }

    /**
     * this function gets a rectangle and finds the nearest point on the outside of the rectangle
     * (left or right).
     * @param rect - the rectangle to check.
     * @return - Point - the nearest point on the outside of the rectangle.
     */
    public Point findNearestSideOfRectangle(Rectangle rect) {
        double distanceRight = Math.abs(rect.getRightLine().start().getX() - this.getX());
        double distanceLeft = Math.abs(rect.getLeftLine().start().getX() - this.getX());
        if (distanceRight < distanceLeft) {
            Point p;
            double newx = rect.getRightLine().start().getX();
            p = new Point(newx + PADDLE_DEFLECT_SPEED, this.getY());
            return p;
        } else {
            Point p;
            double newx =  rect.getLeftLine().start().getX();
            p = new Point(newx - PADDLE_DEFLECT_SPEED, this.getY());
            return p;
        }
    }
}