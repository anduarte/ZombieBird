package com.kilobolt.gameobjects;

/**
 * Created by andre on 19/06/2016.
 */
public class Grass extends Scrollable {
    /**
     * Constructor of the scrollable class
     *
     * @param x           X position
     * @param y           Y position
     * @param width       Width of the object
     * @param height      height of the object
     * @param scrollSpeed Velocity of the object
     */
    public Grass(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
    }

    @Override
    /**
     * Restart to the original positions
     *
     * @param x X position
     * @param scrollSpeed Velocity of the objects
     */
    public void onRestart(float x, float scrollSpeed) {
        position.x = x;
        velocity.x = scrollSpeed;
    }
}
