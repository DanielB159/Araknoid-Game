//Daniel Boianju 315113159
package GameObjects;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
/**
 * @author Daniel B
 */
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * this is the constructor function for the SpriteCollection.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * this function adds a new sprite to the spriteList.
     * @param s - the new sprite to add.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * this is the Get function for the SpriteCollection's spriteList.
     * @return - List - the Collection's spriteList.
     */
    public List<Sprite> getSpriteList() {
        return this.spriteList;
    }

    /**
     * this function uses the timePassed() function in all the sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).timePassed();
        }
    }

    /**
     * this function removes a sprite from the spriteList.
     * @param s - the sprite to remove.
     */
    public void deleteSprite(Sprite s) {
        List<Sprite> lTemp = this.spriteList;
        for (int i = 0; i < lTemp.size(); i++) {
            if (this.spriteList.get(i).getImportantPoint().equals(s.getImportantPoint())) {
                this.spriteList.remove(i);
                return;
            }
        }
    }

    /**
     * this function will call the drawOn function for all the sprites.
     * @param d - the draw-surface that all the sprites are drawn in.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).drawOn(d);
        }
    }
}