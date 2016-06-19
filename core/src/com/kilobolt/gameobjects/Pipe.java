package com.kilobolt.gameobjects;

import java.util.Random;

/**
 * Created by andre on 19/06/2016.
 */
public class Pipe extends Scrollable {

        private Random random;

    /**
     * Constructor of the scrollable class
     *
     * @param x           X position
     * @param y           Y position
     * @param width       Width of the object
     * @param height      height of the object
     * @param scrollSpeed Velocity of the object
     */
    public Pipe(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);

        // Initialize a random object for random number generator
        random = new Random();
    }

    @Override
    public void reset(float newX) {
        // Call the reset method in the superclass
        super.reset(newX);

        // Change the height to a random number
        height = random.nextInt(90) + 15;
    }
}
