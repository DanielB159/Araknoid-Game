//Daniel Boianju 315113159
package GeomatryPrimitives;
import GameObjects.GameLevel;
import GameObjects.GameEnvironment;
import MovementAndCollision.CollisionInfo;
import MovementAndCollision.Velocity;
import biuoop.DrawSurface;

import GameObjects.Sprite;

/**
 * author: Daniel Boianju.
 */
public class Ball implements Sprite {
    private GameEnvironment gameEnv;
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private Point startBoundary;
    private int heightBoundary;
    private int widthBoundary;
    public static final int RIGHT_BORDER_X = 760;
    public static final int LEFT_BORDER_X = 40;
    /**
     * the ball constructor function.
     *
     * @param center - the point of the ball center.
     * @param r      - the length of the ball radius.
     * @param color  - the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }
    /**
     * the second ball constructor function.
     * @param xCenter - the X value of the center of the bal.
     * @param yCenter - the Y wvalue of the center of the ball.
     * @param r       - the length of the ball radius.
     * @param color   - the color of the ball.
     */
    public Ball(int xCenter, int yCenter, int r, java.awt.Color color) {
        Point p1 = new Point(xCenter, yCenter);
        this.center = p1;
        this.radius = r;
        this.color = color;
    }

    /**
     * this function is a set function for the ball's game environment.
     * @param game1 - the game environment.
     */
    public void setGameEnvironment(GameEnvironment game1) {
        this.gameEnv = game1;
    }

    /**
     * the get function for the balls game environment.
     * @return - GameEnvironment - the ball's game environment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnv;
    }

    /**
     * this function checks weather the ball radius is valid. if not, reduce it to be the smallest between
     * the length and height of the rectangle the ball is in.
     */
    public void setValidR() {
        if ((this.getHeightBoundary() < this.getSize() * 2) || (this.getWidthBoundary() < this.getSize() * 2)) {
            this.setRadius((Math.min(this.getHeightBoundary(), this.getWidthBoundary()) / 2) - 1);
        }
    }
    /**
     * this function sets the ball radius to a new value.
     * @param r - the new radius.
     */
    public void setRadius(int r) {
        this.radius = r;
    }
    /**
     * this function sets a new center for the ball.
     * @param p1 - the new center point.
     */
    public void setCenter(Point p1) {
        this.center = p1;
    }
    /**
     * this function sets the boundaries for the ball.
     * @param x - the x value of the left corner of the boundary.
     * @param y - the y value of the left corner of the boundary.
     * @param height - the height of the boundary.
     * @param width - the width of the boundary.
     */
    public void setBounds(double x, double y, int height, int width) {
        Point p1 = new Point(x, y);
        this.setBounds(p1, height, width);
    }
    /**
     * this function sets the boundaries for the ball.
     * @param p1 - the upper left corner of the boundary.
     * @param height - the height of the boundary.
     * @param width - the width of the boundary.
     */
    public void setBounds(Point p1, int height, int width) {
        this.startBoundary = p1;
        this.heightBoundary = height;
        this.widthBoundary = width;
    }

    /**
     * this function returns the center of the ball.
     * @return Point - the center of the ball.
     */
    public Point getCenter() {
        return this.center;
    }
    /**
     * the ball startBoundary get function.
     * @return Point - the startBoundary of the ball.
     */
    public Point getStartBoundary() {
        return this.startBoundary;
    }
    /**
     * the ball heightBoundary get function.
     * @return int - the heightBoundary of the ball.
     */
    public int getHeightBoundary() {
        return this.heightBoundary;
    }
    /**
     * the ball widthBoundary get function.
     * @return int - the widthBoundary of the ball.
     */
    public int getWidthBoundary() {
        return this.widthBoundary;
    }
    /**
     * the ball X value Get function.
     *
     * @return int - the X value of the ball center.
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * the ball Y value Get function.
     *
     * @return int - the Y value of the ball center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * the ball Size Get function.
     *
     * @return int - the size of the ball (its radius).
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * the ball color Get function.
     *
     * @return java.awt.Color - the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * this function adds this ball to the game.
     * @param g - the game to add the ball into.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * this function sets the velocity for a ball.
     *
     * @param v - the velocity to set.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * this function sets the velocity for a ball.
     *
     * @param dx - the X axis velocity paramater.
     * @param dy - the Y axis velocity paramater.
     */
    public void setVelocity(double dx, double dy) {
        Velocity v1 = new Velocity(dx, dy);
        this.velocity = v1;
    }

    /**
     * the Get function to a ball's velocity.
     *
     * @return Velocity - the balls current velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * this function checks the values of the next step in the direction of the ball.
     *
     * @return - point - the next point of the ball in its current velocity.
     */
    public Point naiveMoveOneStep() {
        return this.getVelocity().applyToPoint(this.center);
    }

    /**
     * this function checks weather the ball is in its bounds.
     * @return - boolean. true if inbounds, false otherwise.
     */
    public boolean isBallInbounds() {
        //check if the ball is in the bounds he is bounded by.
        if (this.getX() <= this.getStartBoundary().getX() + this.getWidthBoundary()
                && this.getX() >= this.getStartBoundary().getX()
                && this.getY() <= this.getStartBoundary().getY() + this.getHeightBoundary()
                && this.getY() >= this.getStartBoundary().getY()) {
            return true;
        }
        return false;
    }

    /**
     * this function finds the endpoint of the balls trajectory.
     * @return Point - the endpoint of the ball's trajectory.
     */
    public Point findTrajectoryEndpoint() {
        Ball b1 = new Ball(this.center, this.getSize(), this.getColor());
        b1.setVelocity(this.getVelocity());
        b1.setBounds(this.getStartBoundary(), this.getHeightBoundary(), this.getWidthBoundary());
        while (b1.isBallInbounds()) {
            b1.center = b1.naiveMoveOneStep();
        }
        return b1.center;
    }
    /**
     * this function moves the ball one stop in its current velocity -
     * while still inside the bounds that the ball is in.
     */
    public void moveOneStep() {
        Point p1 = this.naiveMoveOneStep();
        Point endTrajectory = p1;
        Line trajectory = new Line(this.center, endTrajectory);
        CollisionInfo cInf1 = this.gameEnv.getClosestCollision(trajectory);
        if (cInf1 == null) {
            this.setCenter(p1);
            Rectangle pad = this.getGameEnvironment().getPaddleRect();
            //make sure that the ball stays outside the paddle Rectangle.
            if (pad != null) {
                if (p1.checkIfInsideRectangle(pad)) {
                    Point p = p1.findNearestSideOfRectangle(pad);
                    if (p.getX() > Ball.LEFT_BORDER_X && p.getX() < Ball.RIGHT_BORDER_X) {
                        this.setCenter(p);
                        Velocity v = new Velocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
                    } else {
                        p.setY(pad.getUpperLeft().getY() - 3);
                        if (this.getX() <= Ball.LEFT_BORDER_X) {
                            p.setX(this.getX() + 2);
                        } else if (this.getX() >= Ball.RIGHT_BORDER_X) {
                            p.setX(this.getX() + 2);
                        } else {
                            p.setX(this.getX());
                        }
                        this.setCenter(p);
                    }
                }
            }
            return;
        }
        /*
        if reached here, there is a collision in the next move.
        check which side of the rectangle is the collision point is, and change the velocity accordingly.
         */
        Point collisionPoint = cInf1.collisionPoint();
        Rectangle rectColl = cInf1.collisionObject().getCollisionRectangle();
        Rectangle pad = this.getGameEnvironment().getPaddleRect();
        if (pad.getUpperLine().isPointOnPhysicalLine(collisionPoint, 1)) {
            this.setCenter(new Point(this.getX(), this.getY() - 1));
        }
        this.setVelocity(cInf1.collisionObject().hit(this, collisionPoint, this.getVelocity()));
    }
    @Override
    public void timePassed() {
        this.moveOneStep();
    }
    @Override
    public Point getImportantPoint() {
        return this.getCenter();
    }
    /**
     * this function removes the current block from the Game.
     * @param game - the game to remove the block from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}