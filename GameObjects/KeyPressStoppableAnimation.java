package GameObjects;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Daniel B.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;
    /**
     * this is the constructor function.
     * @param sensor - the keyboard.
     * @param key - the key to press in order to stop the animation.
     * @param animation - the animation itself.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.sensor = sensor;
        this.key = key;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (sensor.isPressed(key) && !this.isAlreadyPressed) {
            stop = true;
        }
        if (this.isAlreadyPressed) {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
