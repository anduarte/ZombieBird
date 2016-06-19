package com.kilobolt.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by andre on 19/06/2016.
 */
public abstract class Scrollable {

    // Protected is similar to private, but allows inheritance by subclasses
    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    protected boolean isScrolledLeft;

    /**
     * Constructor of the scrollable class
     *
     * @param x X position
     * @param y Y position
     * @param width Width of the object
     * @param height height of the object
     * @param scrollSpeed Velocity of the object
     */
    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        position = new Vector2(x, y);
        velocity = new Vector2(scrollSpeed, 0);
        this.width = width;
        this.height = height;
        isScrolledLeft = false;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));

        // If the scrollable object is no longer visible
        if (position.x + width < 0) {
            isScrolledLeft = true;
        }
    }

    /**
     * Reset the position of the object
     * NOTE: Should Override in subclass for more specific behavior
     *
     * @param newX New x position
     */
    public void reset(float newX) {
        position.x = newX;
        isScrolledLeft = false;
    }

    /*
     * Class getters
     */
    public boolean isScrolledLeft() {
        return isScrolledLeft;
    }

    public float getTailX() {
        return position.x - width;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}

