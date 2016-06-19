package com.kilobolt.gameobjects;

/**
 * Created by andre on 19/06/2016.
 */
public class ScrollHandler {

    /* ScrollHandler will use the constants below to determine
     * how fast we need to scroll and also determine
     * the size of the gap between each pair of pipes
     */
    public static final int SCROLL_SPEED = -59;
    public static final int PIPE_GAP = 49;

    // ScrollHandler will create all five objects we need
    private Grass frontGrass;
    private Grass backGrass;
    private Pipe pipe1;
    private Pipe pipe2;
    private Pipe pipe3;

    /**
     * Constructor
     *
     * @param yPos Where the object will be created on the y position
     */
    public ScrollHandler(float yPos) {
        frontGrass = new Grass(0, yPos, 143, 11, SCROLL_SPEED);
        backGrass = new Grass(frontGrass.getTailX(), yPos, 143, 11, SCROLL_SPEED);

        pipe1 = new Pipe(210, 0, 22, 60, SCROLL_SPEED, yPos);
        pipe2 = new Pipe(pipe1.getTailX() + PIPE_GAP, 0, 22, 70, SCROLL_SPEED, yPos);
        pipe3 = new Pipe(pipe2.getTailX() + PIPE_GAP, 0, 22, 60, SCROLL_SPEED, yPos);
    }

    public void update(float delta) {
        // Update our objects
        frontGrass.update(delta);
        backGrass.update(delta);
        pipe1.update(delta);
        pipe2.update(delta);
        pipe3.update(delta);

        // Check if any of the pipes are scrolled left,
        // and reset accordingly
        if (pipe1.isScrolledLeft()) {
            pipe1.reset(pipe3.getTailX() + PIPE_GAP);
        } else if (pipe2.isScrolledLeft()) {
            pipe2.reset(pipe1.getTailX() + PIPE_GAP);
        } else if (pipe3.isScrolledLeft()) {
            pipe3.reset(pipe2.getTailX() + PIPE_GAP);
        }

        // Same with grass
        if (frontGrass.isScrolledLeft()) {
            frontGrass.reset(backGrass.getTailX());
        } else if (backGrass.isScrolledLeft()) {
            backGrass.reset(frontGrass.getTailX());
        }
    }

    public void stop() {
        frontGrass.stop();
        backGrass.stop();
        pipe1.stop();
        pipe2.stop();
        pipe3.stop();
    }

    /**
     * Check if the bird collides with the pipes
     *
     * @param bird The bird
     * @return True if any pipe hits the bird
     */
    public boolean collides(Bird bird) {
        return (pipe1.collides(bird) || pipe2.collides(bird) || pipe3.collides(bird));
    }

    /*
     * Class getters
     */
    public Grass getFrontGrass() {
        return frontGrass;
    }

    public Grass getBackGrass() {
        return backGrass;
    }

    public Pipe getPipe1() {
        return pipe1;
    }

    public Pipe getPipe2() {
        return pipe2;
    }

    public Pipe getPipe3() {
        return pipe3;
    }
}
