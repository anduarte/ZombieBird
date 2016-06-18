package com.kilobolt.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.kilobolt.gameobjects.Bird;
import com.kilobolt.zbhelpers.AssetLoader;

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

    // Draws images
    private SpriteBatch batcher;

    private int midPointY;
    private int gameHeight;

    /**
     * Construct GameRenderer
     *
     * @param gameWorld Reference to the GameWorld of the game
     */
    public GameRenderer(GameWorld gameWorld, int gameHeight, int midPointY) {
        this.gameWorld = gameWorld;
        camera = new OrthographicCamera();

        this.gameHeight = gameHeight;
        this.midPointY = midPointY;

        // Arguments
        // 1. If we want an orthographic camera
        // 2 and 3 the size of our game world
        camera.setToOrtho(true, 136, gameHeight);

        batcher = new SpriteBatch();
        // Tell to render in the coordinate system specified by the camera.
        batcher.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        // Tell to render in the coordinate system specified by the camera.
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    /**
     * Render the game objects
     *
     * @param runTime Determine which frame the bird animation should display
     */
    public void render(float runTime) {
        Gdx.app.log("GameRenderer", "render");

        // We will move these outside of the loop for performance later
        Bird bird = gameWorld.getBird();

        // 1. Draw a black background. This prevents flickering
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Begin shapeRenderer
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw background color
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        // Draw grass
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);

        // Draw dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);

        // End shapeRenderer
        shapeRenderer.end();

        // Begin SpriteBatcher
        batcher.begin();
        // Disable transparency
        // This is good for performance when drawing images that do not require
        // transparency
        // Draw background
        batcher.disableBlending();
        batcher.draw(AssetLoader.bg, 0, midPointY + 23, 136, 43);

        // The bird needs transparency, so we enable that again
        batcher.enableBlending();

        // Draw bird at its coordinates. Retrieve the Animation object from AssetLoader
        // Pass in the runTime variable to get the current frame.
        batcher.draw(AssetLoader.birdAnimation.getKeyFrame(runTime), bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight());

        // End SpriteBatch
        batcher.end();

//        // 2. We draw the filled rectangle
//        DEBUG
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);                                    // Tells shapeRenderer to begin drawing filled shapes
//        shapeRenderer.setColor(87 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);                     // Choose RGB color
//        shapeRenderer.rect(gameWorld.getRectangle().x, gameWorld.getRectangle().y,              // Draw the rectangle from the game world
//                gameWorld.getRectangle().getWidth(), gameWorld.getRectangle().getHeight());
//        shapeRenderer.end();                                                                    // Tells the shapeRenderer to finish rendering
//
//        // 3. We draw the rectangle's outline
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);                                      // Tells shapeRenderer to draw an outline of the following shape
//        shapeRenderer.setColor(255 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);
//        shapeRenderer.rect(gameWorld.getRectangle().x, gameWorld.getRectangle().y,
//                gameWorld.getRectangle().getWidth(), gameWorld.getRectangle().getHeight());
//        shapeRenderer.end();

    }

}
