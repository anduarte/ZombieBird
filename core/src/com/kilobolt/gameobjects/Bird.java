package com.kilobolt.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by andre on 14/06/2016.
 */
public class Bird {

    private Vector2 velocity;
    private Vector2 position;
    private Vector2 acceleration;

    private float rotation;
    private int width;
    private int height;

    /**
     * Bird construct
     *
     * @param x X position
     * @param y Y position
     * @param width Bird width
     * @param height Bird height
     */
    public Bird(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
    }

    /**
     * Updates the y position of the bird
     *
     * @param delta Number of seconds since the last time that render method was called
     */
    public void update(float delta) {
        // Multiply the acceleration and velocity vectors by the delta,
        // which is the amount of time that has passed since the update method was  previously called
        // This has a normalizing effect
        // By scaling our Vectors with delta, we can achieve frame-rate independent movement
        velocity.add(acceleration.cpy().scl(delta)); //  Vector2.cpy() will create a Vector2 rather than recycling which will be calling GB every FPS

        // Don't gain velocity upper than 200
        if (velocity.y > 200) {
            velocity.y = 200;
        }

        // Give the new position of the bird
        position.add(velocity.cpy().scl(delta));
    }

    /**
     * Method use when the screen is clicked or touched
     */
    public void onClick() {
        velocity.y = -140;
    }

    /**
     * Getter
     *
     * @return X position
     */
    public float getX() {
        return position.x;
    }

    /**
     * Getter
     *
     * @return Y position
     */
    public float getY() {
        return position.y;
    }

    /**
     * Getter
     *
     * @return Bird height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter
     *
     * @return Bird width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter
     *
     * @return Bird rotation
     */
    public float getRotation() {
        return rotation;
    }
}
