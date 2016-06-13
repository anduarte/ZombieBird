package com.kilobolt.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by andre on 13/06/2016.
 *
 * Helper class responsible for update the game objects
 */
public class GameWorld {

    // ****** Test the camera ******
    private Rectangle rectangle = new Rectangle(0, 0, 17, 12);

    /**
     * Update the game objects
     *
     * @param delta Number of seconds since the last time that render method was called
     */
    public void update(float delta) {
        Gdx.app.log("GameWorld", "update");

        // ****** Test the camera ******
        rectangle.x++;
        if (rectangle.x > 137) rectangle.x = 0;
    }

    /**
     * Getter
     *
     * @return Rectangle used to test
     */
    public Rectangle getRectangle() {
        return rectangle;
    }
}
