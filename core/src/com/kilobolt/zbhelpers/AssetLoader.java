package com.kilobolt.zbhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by andre on 18/06/2016.
 */
public class AssetLoader {

    public static Texture texture;

    /*
     * Is a rectangle portion of the texture.
     */
    public static TextureRegion bg;
    public static TextureRegion grass;

    /*
     * Bird textures and animations
     */
    public static Animation birdAnimation;
    public static TextureRegion bird;
    public static TextureRegion birdDown;
    public static TextureRegion birdUp;

    /*
     * Skull Texture
     */
    public static TextureRegion skullUp;
    public static TextureRegion skullDown;
    public static TextureRegion bar;

    /*
     * Sounds
     */
    public static Sound dead;

    /**
     * Method that will load images
     */
    public static void load() {

        // Creates the texture object of the file where is ours texture for the game
        texture = new Texture(Gdx.files.internal("data/texture.png"));
        // This will make possible to stretch texture without become blurry, each pixel retain its shape
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        /*
         * Texture region
         * Texture where is our image
         * x, and y positions
         * Width and height of the image
         *
         * The flip method is used to flip the Y coordinate because
         * libGDX assume the Y Up by default and we are using the Y Down system
         *
         */

        // Get background texture from the sprite
        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        // Get grass texture from the sprite
        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        // Get birdDown texture from the sprite
        birdDown = new TextureRegion(texture, 136, 0, 17, 12);
        birdDown.flip(false, true);

        // Get bird texture from the sprite
        bird = new TextureRegion(texture, 153, 0, 17, 12);
        bird.flip(false, true);

        // Get birdUp texture from the sprite
        birdUp = new TextureRegion(texture, 170, 0, 17, 12);
        birdUp.flip(false, true);

        // Create the animation loop
        // We gave the Animation 3 frames
        // It will now change frames every 0.06 seconds (down, middle, up, middle, down ...)
        TextureRegion[] birds = {birdDown, bird, birdUp}; // creates an array of TextureRegions
        birdAnimation = new Animation(0.06f, birds); // Creates a new Animation in which each frame is 0.06 seconds long, using the above array
        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG); // Sets play mode to be ping pong, in which we will see a bounce

        // Get skull texture from the sprite
        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        // Create by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        // Get bar texture from the sprite
        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);

        // Initialize dead sound
        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
    }

    /**
     * To help the garbage collector to dispose texture
     */
    public static void dispose() {
        texture.dispose();
    }

}
