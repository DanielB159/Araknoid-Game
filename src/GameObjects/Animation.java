package GameObjects;
import biuoop.DrawSurface;
/**
 * @author Daniel B.
 */
public interface Animation {
    /**
     * this function updated all of the game objects to move one frame.
     * @param d - the drawsurface all of the objectrs are in.
     */
    void doOneFrame(DrawSurface d);

    /**
     * this function checks weather the loop stopping condition is met.
     * @return boolean - true of should stop, false otherwise.
     */
    boolean shouldStop();
}
