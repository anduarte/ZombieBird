package com.kilobolt.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.kilobolt.gameobjects.Bird;
import com.kilobolt.gameobjects.ScrollHandler;
import com.kilobolt.zbhelpers.AssetLoader;

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

    private Rectangle ground;

    private int score = 0;
    // private boolean isAlive = true;

    /**
     * Constructor of the GameWorld
     * The midPointY will make that the bird stays in the middle of the screen
     * no matter what resolution the game runs
     *
     * @param midPointY Vertical middle of the screen
     */
    public GameWorld(int midPointY) {
        bird = new Bird(33, midPointY - 5, 17, 12);
        scrollHandler = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 136, 11);
    }

    /**
     * Update the game objects
     *
     * @param delta Number of seconds since the last time that render method was called
     */
    public void update(float delta) {
        // Add a delta cap so that if our game takes too long
        // to update, we will not break our collision detection.

        if (delta > .15f) {
            delta = .15f;
        }

        bird.update(delta);
        scrollHandler.update(delta);

        if (scrollHandler.collides(bird) && bird.isAlive()) {
            scrollHandler.stop();
            bird.die();
            AssetLoader.dead.play();
        }

        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scrollHandler.stop();
            bird.die();
            bird.decelerate();
        }

//        if (isAlive && scrollHandler.collides(bird)) {
//            scrollHandler.stop();
//            AssetLoader.dead.play();
//            isAlive = false;
//        }

//        Gdx.app.log("GameWorld", "update");
        // ****** Test the camera ******
//        rectangle.x++;
//        if (rectangle.x > 137) rectangle.x = 0;
    }

    /**
     * Increment score
     *
     * @param increment Increment by
     */
    public void addScore(int increment) {
        score += increment;
    }

    /**
     * Getter
     *
     * @return
     */
    public int getScore() {
        return score;
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
