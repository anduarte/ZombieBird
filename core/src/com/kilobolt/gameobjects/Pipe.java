package com.kilobolt.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Created by andre on 19/06/2016.
 */
public class Pipe extends Scrollable {

    public static final int VERTICAL_GAP = 45;
    public static final int SKULL_WIDTH = 24;
    public static final int SKULL_HEIGHT = 11;
    private float groundY;

    private Random random;
    private Rectangle skullUp;
    private Rectangle skullDown;
    private Rectangle barUp;
    private Rectangle barDown;

    // If the bird passes between the two pipes
    private boolean isScored = false;

    /**
     * Constructor of the scrollable class
     *
     * @param x           X position
     * @param y           Y position
     * @param width       Width of the object
     * @param height      height of the object
     * @param scrollSpeed Velocity of the object
     */
    public Pipe(float x, float y, int width, int height, float scrollSpeed, float groundY) {
        super(x, y, width, height, scrollSpeed);

        // Initialize a random object for random number generator
        random = new Random();

        // Collision
        skullUp = new Rectangle();
        skullDown = new Rectangle();
        barUp = new Rectangle();
        barDown = new Rectangle();
        this.groundY = groundY;
    }

    @Override
    public void update(float delta) {
        // Call the update method in the superclass (Scrollable)
        super.update(delta);

        // The set() method allows you to set the top left corner's x, y
        // coordinates,
        // along with the width and height of the rectangle
        barUp.set(position.x, position.y, width, height);
        barDown.set(position.x, position.y + height + VERTICAL_GAP, width, groundY - (position.y + height + VERTICAL_GAP));

        // Our skull width is 24. The bar is only 22 pixels wide. So the skull
        // must be shifted by 1 pixel to the left (so that the skull is centered
        // with respect to its bar).

        // This shift is equivalent to: (SKULL_WIDTH - width) / 2
        skullUp.set(position.x - (SKULL_WIDTH - width) / 2, position.y + height - SKULL_HEIGHT, SKULL_WIDTH, SKULL_HEIGHT);
        skullDown.set(position.x - (SKULL_WIDTH - width) / 2, barDown.y, SKULL_WIDTH, SKULL_HEIGHT);

    }

    @Override
    public void reset(float newX) {
        // Call the reset method in the superclass
        super.reset(newX);

        // Change the height to a random number
        height = random.nextInt(90) + 15;

        isScored = false;
    }

    public boolean collides(Bird bird) {
        if (position.x < bird.getX() + bird.getWidth()) {
            return (Intersector.overlaps(bird.getBoundingCircle(), barUp)
                    || Intersector.overlaps(bird.getBoundingCircle(), barDown)
                    || Intersector.overlaps(bird.getBoundingCircle(), skullUp)
                    || Intersector.overlaps(bird.getBoundingCircle(), skullDown));
        }
        return false;
    }

    /*
     * Class getters
     */
    public Rectangle getSkullUp() {
        return skullUp;
    }

    public Rectangle getSkullDown() {
        return skullDown;
    }

    public Rectangle getBarUp() {
        return barUp;
    }

    public Rectangle getBarDown() {
        return barDown;
    }

    public boolean isScored() {
        return isScored;
    }

    /*
     * Class Setters
     */
    public void setScored(boolean bool) {
        isScored = bool;
    }
}
