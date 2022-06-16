//Daniel Boianju 315113159
package GameObjects;
/**
 * @author Daniel B.
 */
public class Counter {
    private int count;

    /**
     * the constructor function for a Counter.
     */
    public Counter() {
        this.count = 0;
    }
    /**
     * this function adds a number to the current count.
     * @param number - the number to add.
     */
    public void increase(int number) {
        this.count += number;
    }
    /**
     * this function subtracts a number to the current count.
     * @param number - the number to add.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * this get function for the current count.
     * @return - int - the current count.
     */
    public int getValue() {
        return this.count;
    }
}