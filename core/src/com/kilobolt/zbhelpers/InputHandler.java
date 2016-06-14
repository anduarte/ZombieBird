package com.kilobolt.zbhelpers;

import com.badlogic.gdx.InputProcessor;
import com.kilobolt.gameobjects.Bird;

/**
 * Created by andre on 14/06/2016.
 *
 * Class that will react to various inputs
 */
public class InputHandler implements InputProcessor {

    /** Instance of the bird from the gameScreen */
    private Bird bird;

    /**
     * Constructor of the InputHandler
     * Access to the Bird reference of the game screen to make it react to inputs
     *
     * @param bird Reference of the Bird
     */
    public InputHandler(Bird bird) {
        this.bird = bird;
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
        bird.onClick();
        return true;        // Return true to say we handled the touch.
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
