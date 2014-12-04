package ca.csf.gameobjects;

import ca.csf.gameworld.GameRenderer;
import ca.csf.gameworld.GameWorld;

public class ScrollHandler {

    private Grass frontGrass;
    private Grass backGrass;


    private Box box1, box2, box3, box4, box5;

    private GameWorld gameWorld;

    private final int SCROLL_SPEED = 10;

    public ScrollHandler(GameWorld gameWorld){

        this.gameWorld = gameWorld;

        frontGrass = new Grass(0, GameRenderer.getHeight() - 32, 480, 32, SCROLL_SPEED);
        backGrass = new Grass(frontGrass.getTailX(), GameRenderer.getHeight() - 32, 480, 32, SCROLL_SPEED);

    }

    public void update(float delta) {
        frontGrass.update(delta);
        backGrass.update(delta);

    }



}
