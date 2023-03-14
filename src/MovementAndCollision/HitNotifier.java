//Daniel Boianju 315113159
package MovementAndCollision;
/**
 * @author Daniel B.
 */
public interface HitNotifier {
    /**
     * this function adds hl as a listener to hit events.
     * @param hl = the HitListener to add.
     */
    void addHitListener(HitListener hl);

    /**
     * this function removes hl from the list of listeners to hit events.
     * @param hl - the HitListener to remove.
     */
    void removeHitListener(HitListener hl);
}