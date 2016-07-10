package com.kilobolt.zbhelpers;

import com.badlogic.gdx.InputProcessor;
import com.kilobolt.gameobjects.Bird;
import com.kilobolt.gameworld.GameWorld;

/**
 * Created by andre on 14/06/2016.
 * <p>
 * Class that will react to various inputs
 */
public class InputHandler implements InputProcessor {

    /** Instance of the bird from the gameScreen */
    private Bird bird;

    // Reference to the gameWorld
    private GameWorld gameWorld;

    /**
     * Constructor of the InputHandler
     * <p>Access to the Bird reference of the game screen to make it react to inputs
     * <p>
     * @param gameWorld Reference GameWorld
     */
    public InputHandler(GameWorld gameWorld) {
        // myBird now represents the gameWorld's bird.
        this.gameWorld = gameWorld;
        bird = gameWorld.getBird();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (gameWorld.isReady()) {
            gameWorld.start();
        }

        bird.onClick();

        if (gameWorld.isGameOver() || gameWorld.isHighScore()) {
            // Reset all variables, go to GameState.READ
            gameWorld.restart();
        }

        // Return true to say we handled the touch.
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
