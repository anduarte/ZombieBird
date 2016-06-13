package com.kilobolt.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.kilobolt.gameworld.GameRenderer;
import com.kilobolt.gameworld.GameWorld;

/**
 * Created by andre on 13/06/2016.
 *
 * TODO Add comment about GameScreen class
 * Uses a GameWorld and GameRenderer classes so that the GameScreen doesn't have to do the updating and rendering
 *
 */
public class GameScreen implements Screen {

    /** Instance responsible for update the game objects */
    private GameWorld gameWorld;

    /** Instance responsible for rendering the game objects */
    private GameRenderer gameRenderer;

    /**
     * Construct of the game screen class
     */
    public GameScreen() {
        Gdx.app.log("GameScreen", "Attached");

        // Initialize helpers
        gameWorld = new GameWorld();
        gameRenderer = new GameRenderer(gameWorld); // Need to have  reference to the game world that it will be drawing
    }

    @Override
    /**
     * Called by the game loop every time rendering should be performed.
     * The float delta is the number of seconds that has passed since the last time that the render method was called.
     *
     * @param delta Number of seconds since the last time that render method was called
     */
    public void render(float delta) {
        // Sets a color to fill the screen with (RGB = 10, 15, 230), Opacity of 1 (100%)
        Gdx.gl.glClearColor(10/255.0f, 15/255.0f, 230/255.0f, 1f);

        // Fills the screen with the selected color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Print how many times that the render method would be called in one second.
        // This is equivalent to FPS.
        //Gdx.app.log("GameScreen FPS", (int) (1 / delta) + "");

        gameWorld.update(delta);
        gameRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void dispose() {

    }
}
