//Daniel Boianju 315113159
package MovementAndCollision;

import GeomatryPrimitives.Point;

/**
 * author: Daniel Boianju.
 */
public class Velocity {
    public static final int NINETY_DEGREES = 90;
    public static final int ONE_EIGHTY_DEGREES = 180;
    public static final int TWO_SEVENTY_DEGREES = 270;
    public static final int THREE_SIXTY_DEGREES = 360;
    private double dx;
    private double dy;
    /**
     * this is the Velocity constructor function.
     * @param dx - the X axis velocity.
     * @param dy - the Y axis velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * the Get function for the velocity dx value.
     * @return double - the dx value.
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * the Get function for the velocity dy value.
     * @return double - the dy value.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * this function is meant to add to a point with certain velocity the corresponding X and Y values
     * of its future state.
     * @param p - the moving point.
     * @return Point - the point in its future state, after it has moved.
     */
    public Point applyToPoint(Point p) {
        if (p == null) {
            return null;
        }
        Point p1 = new Point(p.getX() + dx, p.getY() + dy);
        return p1;
    }
    /**
     * this function is a static velocity constructor based on the angle and speed of the velocity.
     * (assuming up angle is 0)
     * @param angle - the angle of the velocity.
     * @param speed - the speed of the velocity.
     * @return - velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = angle % Velocity.THREE_SIXTY_DEGREES;
        double dx, dy;
        //separate to four conditions. if the angle is between 0-90 degrees:
        if (0 <= angle && angle < Velocity.NINETY_DEGREES) {
            dx = speed * Math.cos(Math.toRadians(Velocity.NINETY_DEGREES - angle));
            dy = -speed * Math.sin(Math.toRadians(Velocity.NINETY_DEGREES - angle));
        } else if (Velocity.NINETY_DEGREES <= angle && angle < Velocity.ONE_EIGHTY_DEGREES) {
            //separate to four conditions. if the angle is between 90-180 degrees:
            dx = speed * Math.cos(Math.toRadians(angle - Velocity.NINETY_DEGREES));
            dy = speed * Math.sin(Math.toRadians(angle - Velocity.NINETY_DEGREES));
        } else if (Velocity.ONE_EIGHTY_DEGREES <= angle && angle < Velocity.TWO_SEVENTY_DEGREES) {
            //separate to four conditions. if the angle is between 180-270 degrees:
            dx = -speed * Math.sin(Math.toRadians(angle - Velocity.ONE_EIGHTY_DEGREES));
            dy = speed * Math.cos(Math.toRadians(angle - Velocity.ONE_EIGHTY_DEGREES));
        } else {
            //separate to four conditions. if the angle is between 270-360 degrees:
            dx = -speed * Math.cos(Math.toRadians(angle - Velocity.TWO_SEVENTY_DEGREES));
            dy = -speed * Math.sin(Math.toRadians(angle - Velocity.TWO_SEVENTY_DEGREES));
        }
        return new Velocity(dx, dy);
    }
}