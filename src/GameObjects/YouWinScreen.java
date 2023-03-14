package GameObjects;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Daniel B.
 */
public class YouWinScreen implements Animation {
    private int score;

    /**
     * this is the constructor function for the You Win Screen.
     * @param score - the score that the player has reached.
     */
    public YouWinScreen(int score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.GREEN);
        d.drawText(20, d.getHeight() / 2, "YOU WIN! Your score is: " + score, 50);
        d.setColor(Color.white);
        d.drawText(20, (d.getHeight() / 2) + 50, "Press SPACE to end.", 50);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
