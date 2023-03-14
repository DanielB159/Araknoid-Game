package GameObjects;
import biuoop.DrawSurface;

/**
 * @author Daniel B.
 */
public class PauseScreen implements Animation {
    /**
     * this is the constructor function for the pause screen.
     */
    public PauseScreen() { }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
