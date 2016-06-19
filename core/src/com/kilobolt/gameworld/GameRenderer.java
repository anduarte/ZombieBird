package com.kilobolt.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.kilobolt.gameobjects.Bird;
import com.kilobolt.gameobjects.Grass;
import com.kilobolt.gameobjects.Pipe;
import com.kilobolt.gameobjects.ScrollHandler;
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

    // Game Objects
    private Bird bird;
    private ScrollHandler scrollHandler;
    private Grass frontGrass;
    private Grass backGrass;
    private Pipe pipe1;
    private Pipe pipe2;
    private Pipe pipe3;

    // Game Assets
    private TextureRegion bg;
    private TextureRegion grass;
    private Animation birdAnimation;
    private TextureRegion birdMid;
    private TextureRegion birdDown;
    private TextureRegion birdUp;
    private TextureRegion skullUp;
    private TextureRegion skullDown;
    private TextureRegion bar;

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

        // Call helper methods to initialize instance variables
        initGameObjects();
        initAssets();
    }

    /**
     * Initialize game objects
     */
    private void initGameObjects() {
        bird = gameWorld.getBird();
        scrollHandler = gameWorld.getScrollHandler();
        frontGrass = scrollHandler.getFrontGrass();
        backGrass = scrollHandler.getBackGrass();
        pipe1 = scrollHandler.getPipe1();
        pipe2 = scrollHandler.getPipe2();
        pipe3 = scrollHandler.getPipe3();
    }

    /**
     * Initialize assets
     */
    private void initAssets() {
        bg = AssetLoader.bg;
        grass = AssetLoader.grass;
        birdAnimation = AssetLoader.birdAnimation;
        birdMid = AssetLoader.bird;
        birdDown = AssetLoader.birdDown;
        birdUp = AssetLoader.birdUp;
        skullUp = AssetLoader.skullUp;
        skullDown = AssetLoader.skullDown;
        bar = AssetLoader.bar;
    }

    /**
     * Render the game objects
     *
     * @param runTime Determine which frame the bird animation should display
     */
    public void render(float runTime) {
        // Gdx.app.log("GameRenderer", "render");

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
        batcher.draw(bg, 0, midPointY + 23, 136, 43);

        // Draw grass
        drawGrass();

        // Draw pipes
        drawPipes();

        // The bird needs transparency, so we enable that again
        batcher.enableBlending();

        // Draw skulls
        drawSkulls();

        // Draw bird at its coordinates. Retrieve the Animation object from AssetLoader
        // Pass in the runTime variable to get the current frame.
//        batcher.draw(AssetLoader.birdAnimation.getKeyFrame(runTime), bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight());

        if (bird.shouldntFlap()) {
            batcher.draw(birdMid, bird.getX(), bird.getY(),
                    bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
                    bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());

        } else {
            batcher.draw(birdAnimation.getKeyFrame(runTime), bird.getX(),
                    bird.getY(), bird.getWidth() / 2.0f,
                    bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(),
                    1, 1, bird.getRotation());
        }

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

    private void drawGrass() {
        // Draw the grass
        batcher.draw(grass, frontGrass.getX(), frontGrass.getY(),
                frontGrass.getWidth(), frontGrass.getHeight());
        batcher.draw(grass, backGrass.getX(), backGrass.getY(),
                backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawSkulls() {
        batcher.draw(skullUp, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

        batcher.draw(skullUp, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

        batcher.draw(skullUp, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
    }

    private void drawPipes() {
        batcher.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
                pipe1.getHeight());
        batcher.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
                pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

        batcher.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
                pipe2.getHeight());
        batcher.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
                pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

        batcher.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
                pipe3.getHeight());
        batcher.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
                pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));
    }
}
