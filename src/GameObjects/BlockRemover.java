//Daniel Boianju 315113159
package GameObjects;
import MovementAndCollision.HitListener;
import GeomatryPrimitives.Ball;
/**
 * @author Daniel B.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * the constructor function for a BlockRemover.
     * @param game - the game that the blockRemover acts in.
     * @param removedBlocks - the number remaining Blocks.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * this is the Get function to return the remaining Blocks in the game.
     * @return - int - the number of the remaining blocks.
     */
    public int getRemainingBlocks() {
        return this.remainingBlocks.getValue();
    }

    @Override
    public int getDifferentialNumber() {
        return this.remainingBlocks.getValue();
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks = this.game.getBlocksCounter();
    }
}