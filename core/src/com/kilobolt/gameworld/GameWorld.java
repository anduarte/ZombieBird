package com.kilobolt.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.kilobolt.gameobjects.Bird;
import com.kilobolt.gameobjects.ScrollHandler;

/**
 * Created by andre on 13/06/2016.
 *
 * Helper class responsible for update the game objects
 */
public class GameWorld {

    // ****** Test the camera ******
//    private Rectangle rectangle = new Rectangle(0, 0, 17, 12);

    private Bird bird;

    private ScrollHandler scrollHandler;

    /**
     * Constructor of the GameWorld
     * The midPointY will make that the bird stays in the middle of the screen
     * no matter what resolution the game runs
     *
     * @param midPointY Vertical middle of the screen
     */
    public GameWorld(int midPointY) {
        bird = new Bird(33, midPointY - 5, 17, 12);
        scrollHandler = new ScrollHandler(midPointY + 66);
    }

    /**
     * Update the game objects
     *
     * @param delta Number of seconds since the last time that render method was called
     */
    public void update(float delta) {
        bird.update(delta);
        scrollHandler.update(delta);
//        Gdx.app.log("GameWorld", "update");
        // ****** Test the camera ******
//        rectangle.x++;
//        if (rectangle.x > 137) rectangle.x = 0;
    }

    /**
     * Getter
     *
     * @return
     */
    public Bird getBird() {
        return bird;
    }

    /**
     * Getter
     *
     * @return
     */
    public ScrollHandler getScrollHandler() {
        return scrollHandler;
    }

    /**
     * Getter
     * USED TO DEBUG
     * @return Rectangle used to test
     */
//    public Rectangle getRectangle() {
//        return rectangle;
//    }
}
