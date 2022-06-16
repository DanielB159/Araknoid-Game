//Daniel Boianju 315113159
package GameObjects;
import MovementAndCollision.HitListener;
import GeomatryPrimitives.Ball;
/**
 * @author Daniel B.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * this is the constructor function for a ScoreTrackingListener.
     * @param scoreCounter - a counter of the current score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * this is the get function for the score.
     * @return int - the score.
     */
    public int getScore() {
        return this.currentScore.getValue();
    }
    @Override
    public int getDifferentialNumber() {
        return 0;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(5);
    }
}