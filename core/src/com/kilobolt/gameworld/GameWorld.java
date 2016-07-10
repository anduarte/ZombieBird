package com.kilobolt.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.kilobolt.gameobjects.Bird;
import com.kilobolt.gameobjects.ScrollHandler;
import com.kilobolt.zbhelpers.AssetLoader;

/**
 * Created by andre on 13/06/2016.
 * <p>
 * Helper class responsible for updateRunning the game objects
 */
public class GameWorld {

    // ****** Test the camera ******
//    private Rectangle rectangle = new Rectangle(0, 0, 17, 12);

    private Bird bird;
    private ScrollHandler scrollHandler;
    private Rectangle ground;
    private int score = 0;
    // private boolean isAlive = true;

    // Current state of the game
    private GameState currentState;

    // Vertical middle of the screen
    private int midPointY;
    /**
     * Constructor of the GameWorld
     * <p>The midPointY will make that the bird stays in the middle of the screen
     * no matter what resolution the game runs
     * <p>
     * @param midPointY Vertical middle of the screen
     */
    public GameWorld(int midPointY) {
        this.midPointY = midPointY;
        bird = new Bird(33, midPointY - 5, 17, 12);
        scrollHandler = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 136, 11);
        currentState = GameState.READY;
    }

    /**
     * Switch to the update correspondent to the current state
     * <p>
     * @param delta
     */
    public void update(float delta) {

        switch (currentState) {
            case READY:
                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }

    }

    private void updateReady(float delta) {
        // Do nothing for now
    }

    /**
     * Update the game objects
     * <p>
     * @param delta Number of seconds since the last time that render method was called
     */
    public void updateRunning(float delta) {
        // Add a delta cap so that if our game takes too long
        // to updateRunning, we will not break our collision detection.

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
            currentState = GameState.GAMEOVER;

            // Check if is the high score and updates it
            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
        }

//        if (isAlive && scrollHandler.collides(bird)) {
//            scrollHandler.stop();
//            AssetLoader.dead.play();
//            isAlive = false;
//        }

//        Gdx.app.log("GameWorld", "updateRunning");
        // ****** Test the camera ******
//        rectangle.x++;
//        if (rectangle.x > 137) rectangle.x = 0;
    }

    /**
     * Increment score
     * <p>
     * @param increment Increment by
     */
    public void addScore(int increment) {
        score += increment;
    }

    /**
     * Check game state
     */
    public boolean isReady() {
        return currentState == GameState.READY;
    }

    /**
     * Check game state
     */
    public void start() {
        currentState = GameState.RUNNING;
    }

    /**
     * Check game state
     */
    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    /**
     * Restart the variables of the game.
     * <p>currentState, score, bird position, scrollHandler
     */
    public void restart() {
        currentState = GameState.READY;
        score = 0;
        bird.onRestart(midPointY - 5);
        scrollHandler.onRestart();
        currentState = GameState.READY;
    }

    /**
     * Check game state
     */
    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    /**
     * Getter
     * <p>
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * Getter
     * <p>
     * @return
     */
    public Bird getBird() {
        return bird;
    }

    /**
     * Getter
     * <p>
     * @return
     */
    public ScrollHandler getScrollHandler() {
        return scrollHandler;
    }

    /**
     * Getter
     * <p>USED TO DEBUG
     * @return Rectangle used to test
     */
//    public Rectangle getRectangle() {
//        return rectangle;
//    }

    /**
     * GameState enum for divide the game into multiple states
     */
    public enum GameState {
        READY, RUNNING, GAMEOVER, HIGHSCORE
    }
}


