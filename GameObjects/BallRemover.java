//Daniel Boianju 315113159
package GameObjects;
import MovementAndCollision.HitListener;
import GeomatryPrimitives.Ball;
/**
 * @author Daniel B.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * the constructor function for a BallRemover.
     * @param game - the game that the BallRemover acts in.
     * @param remainingBalls - the number remaining Balls.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * this is the Get function to return the remaining Balls in the game.
     * @return - int - the number of the remaining Balls.
     */
    public int getRemainingBalls() {
        return this.remainingBalls.getValue();
    }

    @Override
    public int getDifferentialNumber() {
        return this.getRemainingBalls();
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.game.decreaseBallCount(1);
        this.remainingBalls = this.game.getBallsCounter();
    }
}
