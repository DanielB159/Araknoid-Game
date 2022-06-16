//Daniel Boianju 315113159
package GameObjects;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import MovementAndCollision.Collidable;
import MovementAndCollision.Velocity;
import GeomatryPrimitives.Rectangle;
import GeomatryPrimitives.Point;
import GeomatryPrimitives.Ball;
import GeomatryPrimitives.Line;
import java.awt.Color;

/**
 * @author Daniel B.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private GUI gui;
    private Velocity v;
    private int paddleSpeed;
    private Rectangle rect;
    public static final int PADDLE_TOP_RIGHT_X = 350;
    public static final int PADDLE_TOP_RIGHT_Y = 540;
    public static final int PADDLE_HEIGHT = 20;
    public static final int PADDLE_RIGHT_BORDER = 760;
    public static final int PADDLE_LEFT_BORDER = 40;
    public static final int LEFTMOST_REGION_ANGLE = 300;
    public static final int NEAR_LEFTMOST_REGION_ANGLE = 330;
    public static final int RIGHTMOST_REGION_ANGLE = 60;
    public static final int NEAR_RIGHTMOST_REGION_ANGLE = 30;

    /**
     * the constructor function for a paddle.
     * @param k - the keyboard sensor.
     * @param g - the gui that tha paddle is in.
     * @param paddleSpeed - the speed of the paddle.
     * @param paddleWidth - the width of the paddle.
     */
    public Paddle(GUI g, biuoop.KeyboardSensor k, int paddleWidth, int paddleSpeed) {
        this.keyboard = k;
        this.gui = g;
        this.v = new Velocity(0, 0);
        this.rect = new Rectangle(Paddle.PADDLE_TOP_RIGHT_X,
                Paddle.PADDLE_TOP_RIGHT_Y, paddleWidth, Paddle.PADDLE_HEIGHT);
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * this function sets the paddle to be the new rectangle that is inputted.
     * @param rect1 - the new paddle Rectangle position.
     */
    public void setRectangle(Rectangle rect1) {
        this.rect = rect1;
    }

    /**
     * this function sets the new velocity to the paddle.
     * @param v1 - the new velocity.
     */
    public void setVelocity(Velocity v1) {
        this.v = v1;
    }

    /**
     * this function sets the new velocity to the paddle.
     * @param dx - the x-axis velocity.
     * @param dy - the y-axis velocity.
     */
    public void setVelocity(double dx, double dy) {
        Velocity v1 = new Velocity(dx, dy);
        this.setVelocity(v1);
    }
    /**
     * this function sets the velocity of the paddle to move left.
     */
    public void moveLeft() {
        this.setVelocity(-paddleSpeed, 0);
        Point newUpperLeft = this.v.applyToPoint(this.getCollisionRectangle().getUpperLeft());
        if (newUpperLeft.getX() >= Paddle.PADDLE_LEFT_BORDER
                && newUpperLeft.getX() + rect.getWidth() <= Paddle.PADDLE_RIGHT_BORDER) {
            this.rect.setUpperLeftP(newUpperLeft);
        }
    }
    /**
     * this function checks weather the right key is pressed.
     */
    public void moveRight() {
        this.setVelocity(paddleSpeed, 0);
        Point newUpperLeft = this.v.applyToPoint(this.getCollisionRectangle().getUpperLeft());
        if (newUpperLeft.getX() >= Paddle.PADDLE_LEFT_BORDER
                && newUpperLeft.getX() + rect.getWidth() <= Paddle.PADDLE_RIGHT_BORDER) {
            this.rect.setUpperLeftP(newUpperLeft);
        }
    }

    @Override
    public void timePassed() {
        boolean c1 = keyboard.isPressed(KeyboardSensor.RIGHT_KEY),
                c2 = keyboard.isPressed(KeyboardSensor.LEFT_KEY);
        if (c1) {
            this.moveRight();
        } else if (c2) {
            this.moveLeft();
        }
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.orange);
        Rectangle rect1 = this.getCollisionRectangle();
        surface.fillRectangle((int) rect1.getUpperLeft().getX(), (int) rect1.getUpperLeft().getY(),
                (int) rect1.getWidth(), (int) rect1.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) rect1.getUpperLeft().getX(), (int) rect1.getUpperLeft().getY(),
                (int) rect1.getWidth(), (int) rect1.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        Velocity v1;
        Rectangle rectColl = this.getCollisionRectangle();
        if (rectColl.getUpperLine().isPointOnPhysicalLine(collisionPoint, 1)) {
            int region = this.getRegion(collisionPoint);
            v1 = returnVBasedOnRegion(region, currentVelocity);
            return v1;
        } else if (rectColl.getLeftLine().isPointOnPhysicalLine(collisionPoint, 1)
                || rectColl.getRightLine().isPointOnPhysicalLine(collisionPoint, 1)) {
            dx = -dx;
        } else if (rectColl.getLowerLine().isPointOnPhysicalLine(collisionPoint, 1)) {
            dy = -dy;
        }
        v1 = new Velocity(dx, dy);
        return v1;
    }

    /**
     * this function adds the paddle to the game.
     * @param g - the game to add the paddle to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * this function returns the region of the paddle that the ball has hit.
     * assuming the ball has hit the upper side of the paddle.
     * @param p1 - the point of collision.
     * @return int - the region (1-5).
     */
    public int getRegion(Point p1) {
        Line l1 = this.getCollisionRectangle().getUpperLine();
        double fifth = this.rect.getWidth() / 5;
        //check what region of the side the X value is in.
        if (p1.getX() >= l1.start().getX() && p1.getX() < l1.start().getX() + fifth) {
            return 1;
        } else if (p1.getX() >= l1.start().getX() + fifth && p1.getX() < l1.start().getX() + 2 * fifth) {
            return 2;
        } else if (p1.getX() >= l1.start().getX() + 2 * fifth && p1.getX() < l1.start().getX() + 3 * fifth) {
            return 3;
        } else if (p1.getX() >= l1.start().getX() + 3 * fifth && p1.getX() < l1.start().getX() + 4 * fifth) {
            return 4;
        } else {
            return 5;
        }
    }

    /**
     * this function returns the new velocity of the ball based on the region of the ball.
     * @param region - the region that the ball is in.
     * @param currentV - the current velocity of the ball.
     * @return - Velocity - the new velocity of the ball.
     */
    public Velocity returnVBasedOnRegion(int region, Velocity currentV) {
        Point p1 = new Point(0, 0), p2 = currentV.applyToPoint(p1);
        double speed = p1.distance(p2);
        Velocity newV;
        switch (region) {
            //leftmost region.
            case 1: newV = Velocity.fromAngleAndSpeed(Paddle.LEFTMOST_REGION_ANGLE, speed);
                break;
            //near leftmost region.
            case 2: newV = Velocity.fromAngleAndSpeed(Paddle.NEAR_LEFTMOST_REGION_ANGLE, speed);
                break;
            //near rightmost region.
            case 4: newV = Velocity.fromAngleAndSpeed(Paddle.NEAR_RIGHTMOST_REGION_ANGLE, speed);
                break;
            //rightmost region
            case 5: newV = Velocity.fromAngleAndSpeed(Paddle.RIGHTMOST_REGION_ANGLE, speed);
                break;
            //middle region.
            default: double dx = currentV.getDx(), dy = currentV.getDy();
                newV = new Velocity(dx, -dy);
        }
        return newV;
    }
    @Override
    public Point getImportantPoint() {
        return this.getCollisionRectangle().getUpperLeft();
    }
}
