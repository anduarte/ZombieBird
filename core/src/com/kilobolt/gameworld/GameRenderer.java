package com.kilobolt.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by andre on 13/06/2016.
 *
 * Helper class responsible for rendering the game objects
 */
public class GameRenderer {

    /** Reference to the game world that it will be drawing */
    private GameWorld gameWorld;
    private OrthographicCamera camera;

    // Draw shapes and lines to test the camera
    private ShapeRenderer shapeRenderer;

    /**
     * Construct GameRenderer
     *
     * @param gameWorld Reference to the GameWorld of the game
     */
    public GameRenderer(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        camera = new OrthographicCamera();

        // Arguments
        // 1. If we want an orthographic camera
        // 2 and 3 the size of our game world
        camera.setToOrtho(true, 136, 204);

        // ****** Test the camera ******
        shapeRenderer = new ShapeRenderer();

        // Tell to render in the coordinate system specified by the camera.
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    /**
     * Render the game objects
     */
    public void render() {
        Gdx.app.log("GameRenderer", "render");

        // 1. Draw a black background. This prevents flickering
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // 2. We draw the filled rectangle
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);                                    // Tells shapeRenderer to begin drawing filled shapes
        shapeRenderer.setColor(87 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);                     // Choose RGB color
        shapeRenderer.rect(gameWorld.getRectangle().x, gameWorld.getRectangle().y,              // Draw the rectangle from the game world
                gameWorld.getRectangle().getWidth(), gameWorld.getRectangle().getHeight());
        shapeRenderer.end();                                                                    // Tells the shapeRenderer to finish rendering

        // 3. We draw the rectangle's outline
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);                                      // Tells shapeRenderer to draw an outline of the following shape
        shapeRenderer.setColor(255 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);
        shapeRenderer.rect(gameWorld.getRectangle().x, gameWorld.getRectangle().y,
                gameWorld.getRectangle().getWidth(), gameWorld.getRectangle().getHeight());
        shapeRenderer.end();

        
    }

}
