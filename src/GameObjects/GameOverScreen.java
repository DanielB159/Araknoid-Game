package GameObjects;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Daniel B.
 */
public class GameOverScreen implements Animation {
    private int score;

    /**
     * this is the constructor function for the pause screen.
     * @param score - the score that the player has reached.
     */
    public GameOverScreen(int score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(20, d.getHeight() / 2, "GAME OVER! Your score is: " + score, 50);
        d.setColor(Color.white);
        d.drawText(20, (d.getHeight() / 2) + 50, "Press SPACE to end.", 50);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
