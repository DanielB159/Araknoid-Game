//Daniel Boianju 315113159
package MovementAndCollision;
import GameObjects.Block;
import GeomatryPrimitives.Ball;
/**
 * @author Daniel B.
 */
public interface HitListener {
    /**
     * this function is called when BeingHit is hit by the hitter.
     * @param beingHit - the Block that is being hit.
     * @param hitter - the Ball hitter.
     */
    void hitEvent(Block beingHit, Ball hitter);

    /**
     * this function is called in order to return the important point of the listener.
     * in the BlockRemover, it is the Remaining Blocks number.
     * @return int - the remaining blocks number.
     */
    int getDifferentialNumber();
}