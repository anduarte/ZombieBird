package com.kilobolt.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by andre on 14/06/2016.
 */
public class  Bird {

    private Vector2 velocity;
    private Vector2 position;
    private Vector2 acceleration;

    private float rotation;
    private int width;
    private int height;

    private Circle boundingCircle;

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

        boundingCircle = new Circle();
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

        // Set the circle's center to be (9, 6) with respect to the bird.
        // Set the circle's radius to be 6.5f;
        boundingCircle.set(position.x + 9, position.y + 6, 6.5f);

        // Rotate counterclockwise (Rising)
        if (velocity.y < 0) {
            rotation -= 600 * delta; // Delta will make the bird rotate at the same rate even if the game slows down

            if(rotation < -20) {
                rotation = -20;
            }
        }

        // Rotate clockwise (falling)
        if (isFalling()) {
            rotation += 480 * delta; // Delta will make the bird rotate at the same rate even if the game slows down

            if (rotation > 90) {
                rotation = 90;
            }
        }

    }

    /**
     * Verifies if the bird is falling.
     * Decide when the bird should begin rotating downwards
     *
     * @return True if it is falling
     */
    public boolean isFalling() {
        return velocity.y > 110;
    }

    /**
     * Determine when the bird should stop animating
     *
     * @return True if the bird stop animating
     */
    public boolean shouldntFlap() {
        return velocity.y > 70;
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

    /**
     * Getter
     *
     * @return
     */
    public Circle getBoundingCircle() {
        return boundingCircle;
    }
}
